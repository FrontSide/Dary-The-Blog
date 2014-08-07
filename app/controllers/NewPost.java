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

public class NewPost extends Controller {

    final static Logger.ALogger logger = Logger.of(NewPost.class);

    /* ------ New Blog Post ------ */
    public static Result create() {

        logger.debug("called create new post"); 
        Form<Post> postForm = Form.form(Post.class);
        
        logger.debug("render newpost.html");
        return ok(newpost.render(postForm, new UserDAO().getUserbyBlogname(session("user")), false));
    }

    /* ------ Upload Picture Submit ------ */
    public static Result uploadPicture() {

        /* TODO
           Save Picture (generate Filename)
           Store Picture info in DB
           Add Picture to Article when uploaded
           Several checks (as appropriate)
        */

        logger.debug("upload picture");
        
        MultipartFormData body = request().body().asMultipartFormData();
        FilePart picture = body.getFile("picture_raw");
        if (picture != null) {
            logger.debug("data found in request");
            String fileName = picture.getFilename();
            String contentType = picture.getContentType(); 
            File file = picture.getFile();

            logger.debug("Path of uploaded Picture :: " + file.getAbsolutePath());
            return ok(Json.newObject().put("success", true));
        } else {
            logger.error("no data found in request");
            return ok(Json.newObject().put("success", false));
        } 

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