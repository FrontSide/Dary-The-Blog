package controllers;

import models.*;
import dao.*;
import factories.*;
import views.html.*;

import play.mvc.*;
import play.i18n.Messages;

import play.Logger;
import play.data.Form;

public class NewPost extends Controller {

    final static Logger.ALogger logger = Logger.of(NewPost.class);
    
    private static final UserDAO userDAO = (UserDAO) new UserDAOFactory().create();
    private static final PostDAO postDAO = (PostDAO) new PostDAOFactory().create();

    /* ------ New Blog Post ------ */
    public static Result create() {

        logger.debug("called create new post"); 
        Form<Post> postForm = Form.form(Post.class);
        
        logger.debug("render newpost.html");
                
        return ok(newpost.render(postForm, 
                        userDAO.getByBlogname(session("user")), false));
    }

    /* ------ Submit ------ */
    public static Result submit() {

        logger.debug("receive data from form");         
        Form<Post> postForm = Form.form(Post.class);
        Form<Post> filledForm = postForm.bindFromRequest();

        logger.debug("validate form");
        if (filledForm.hasErrors()) {
            return badRequest(newpost.render(filledForm, userDAO.getByBlogname(session("user")), false));
        }

        logger.debug("Form is valid. Create 'Post' Object!");
        Post post = filledForm.get();   
        post.user = userDAO.getByBlogname(session("user"));     

        logger.debug("init persistence in Database");
        postDAO.create(post); 

        //Success flash        
        flash("success", Messages.get("FLASH_NEW_ARTICLE_SUCCESS"));

        logger.debug("render blog view");
        return redirect("/blog/" + session("user"));
    }

}
