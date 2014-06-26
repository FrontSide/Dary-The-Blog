package controllers;

import models.*;
import dao.*;
import views.html.*;

import play.*;
import play.mvc.*;
import play.Logger;
import play.data.Form;

public class NewPost extends Controller {

    final static Logger.ALogger logger = Logger.of(NewPost.class);

    /* ------ New Blog Post ------ */
    public static Result create() {

        logger.debug("called create new post"); 
        
        Form<Post> postForm = Form.form(Post.class);

        // TODO Get currently logged user

        logger.debug("render newpost.html");
        return ok(newpost.render(postForm));
    }

    /* ------ Submit ------ */
    public static Result submit() {

        logger.debug("receive data from form");         
        Form<Post> postForm = Form.form(Post.class);
        Form<Post> filledForm = postForm.bindFromRequest();

        logger.debug("validate form");
        if (filledForm.hasErrors()) {
            return badRequest(newpost.render(filledForm));
        }

        Post post = filledForm.get();        

        logger.debug("init persistence in Database");
        PostDAO edao = new PostDAO();
        edao.create(post); 

        logger.debug("render blog view");
        return redirect("/");
    }

}