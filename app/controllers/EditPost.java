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
        return ok(newpost.render(postForm, loggedUser));
    }
    
    /* ------ Submit ------ */
    public static Result submit() {

        return ok();
    }

}