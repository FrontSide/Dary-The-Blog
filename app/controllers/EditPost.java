package controllers;

import models.*;
import dao.*;
import factories.*;
import views.html.*;
import views.html.error.*;

import play.mvc.*;
import play.i18n.Messages;

import play.Logger;
import play.data.Form;

public class EditPost extends Controller {

    final static Logger.ALogger logger = Logger.of(EditPost.class);
    
    private static final UserDAO userDAO = (UserDAO) new UserDAOFactory().create();
    private static final PostDAO postDAO = (PostDAO) new PostDAOFactory().create();

    /* ------ New Blog Post ------ */
    public static Result edit(Long id) {

        Post post = postDAO.getById(id);
        User loggedUser = userDAO.getByBlogname(session("user"));
        
        /* chek if this post has already been deleted/archived 
            or ID doesn't exist'*/
        if (post == null || post.isArchived) return notFound(
                                                    e404.render(loggedUser));
               
        /* TODO: Refactoring */
        /* Check if the Post with the given ID actually belogs 
         * to the logged in User
         * !!! This check should be outsourced to its own security class !!!
         */
        if (!post.user.equals(loggedUser)) {
            logger.error("User " + loggedUser.blogname 
                            + " is not authorized to edit this Post!");
            return Application.noAllow();
        }

        logger.debug("called edit post"); 
        Form<Post> postForm = Form.form(Post.class).fill(post);
        
        logger.debug("render newpost.html");
        return ok(newpost.render(postForm, loggedUser, true));
    }
    
    /* ------ Submit ------ */
    public static Result submit() {

        logger.debug("receive data from form");         
        Form<Post> postForm = Form.form(Post.class);
        Form<Post> filledForm = postForm.bindFromRequest();

        logger.debug("validate form");
        if (filledForm.hasErrors()) {
            return badRequest(newpost.render(filledForm, 
            		userDAO.getByBlogname(session("user")), true));
        }

        logger.debug("Form is valid. Update Post!");

        /* Set OLD post as archived!
           This does not create a new Entry yet */ 
        Long oldId = filledForm.get().id;
        postDAO.markByIdAsArchived(oldId);   

        /* Bind Form and Create new Post with new ID */
        Post post = filledForm.get();   
        post.rootPost = postDAO.getById(oldId);
        
        post.id = null; //Remove old ID so new one will be generated
        post.user = userDAO.getByBlogname(session("user"));

        logger.debug("init persistence in Database");
        postDAO.create(post); 

        //Success flash        
        flash("success", Messages.get("FLASH_EDIT_SUCCESS"));

        logger.debug("render blog view");
        return redirect("/blog/" + session("user"));

    }

}
