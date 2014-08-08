package controllers;

import models.*;
import dao.*;
import controllers.*;
import views.html.*;

import play.*;
import play.mvc.*;

import play.Logger;
import play.data.Form;
import play.libs.Json;

public class NewPost extends Controller {

    final static Logger.ALogger logger = Logger.of(NewPost.class);

    /* ------ New Blog Post ------ */
    public static Result create() {

        logger.debug("called create new post"); 
        Form<Post> postForm = Form.form(Post.class);
        
        logger.debug("render newpost.html");
        return ok(newpost.render(postForm, new UserDAO().getUserbyBlogname(session("user")), false));
    }

    public static Result uploadPicture() {
        return PictureUpload.uploadPicture();
    }

    /* ------ Submit ------ */
    public static Result submit() {

        logger.debug("receive data from form");         
        Form<Post> postForm = Form.form(Post.class);
        Form<Post> filledForm = postForm.bindFromRequest();

        logger.debug("validate form");
        if (filledForm.hasErrors()) {
            return badRequest(newpost.render(filledForm, new UserDAO().getUserbyBlogname(session("user")), false));
        }

        logger.debug("Form is valid. Create 'Post' Object!");
        Post post = filledForm.get();   
        post.user = new UserDAO().getUserbyBlogname(session("user"));     

        logger.debug("init persistence in Database");
        new PostDAO().create(post); 

        //Success flash
        flash("success", "Nice! You just successfully wrote an article for your blog!");

        logger.debug("render blog view");
        return redirect("/blog/" + session("user"));
    }

}