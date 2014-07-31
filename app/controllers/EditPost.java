package controllers;

import models.*;
import dao.*;
import views.html.*;

import play.*;
import play.mvc.*;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.*;

import java.io.File;

import play.Logger;
import play.data.Form;
import play.libs.Json;

public class EditPost extends Controller {

    final static Logger.ALogger logger = Logger.of(EditPost.class);

    /* ------ New Blog Post ------ */
    public static Result edit(Long id) {

        Post post = new PostDAO().getById(id);
        User loggedUser = new UserDAO().getUserbyBlogname(session("user"));

        /* Check if the Post with the given ID actually belogs to the logged in User
           This check should be outsourced to its own security class */
        if (!post.user.equals(loggedUser)) {
            logger.error("You are not authorized to edit this Post!");
            return redirect(routes.Application.login()); 
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
            return badRequest(newpost.render(filledForm, new UserDAO().getUserbyBlogname(session("user")), true));
        }

        logger.debug("Form is valid. Update Post!");

        /* Set OLD post as archived!
           This does not create a new Entry yet */ 
        Long oldId = filledForm.get().id;
        new PostDAO().markPostByIdAsArchived(oldId);   

        /* Bind Form and Create new Post with new ID */
        Post post = filledForm.get();   
        post.rootPost = new PostDAO().getById(oldId);
        post.id = null; //Remove old ID so new one will be generated
        post.user = new UserDAO().getUserbyBlogname(session("user"));

        logger.debug("init persistence in Database");
        new PostDAO().create(post); 

        //Success flash
        flash("success", "OK! You successfully edited an Article!");

        logger.debug("render blog view");
        return redirect("/blog/" + session("user"));

    }

}