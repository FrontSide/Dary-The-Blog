package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;
import java.util.*;

import models.*;
import dao.*;
import views.html.*;
import views.html.error.*;
import play.Logger;

public class Application extends Controller {

    final static Logger.ALogger logger = Logger.of(Application.class);

    /* ------ Show Home Page ------ */
    /* TODO:: Rename to home() and create new login() method!! */
    public static Result login() {
        /* If user is logged in. Send user to own blog */
        if (session("user") != null) {
            return show(session("user"));
        }
        /* Redirect to Logn/Signup Form */
        return UserController.signup();
    }

    public static Result logout() {
        /* Delete UserLog Entry from DB */
        /* For security reasons a user is logged out from EVERY session she's logged in */ 
        new UserLogDAO().deleteByBlogname(session("user"));
        /* Destroy User session and Return to Login-Page */
        session().clear();

        //Success flash
        flash("info", "You are now logged out from dary. See you soon!");

        return UserController.signup();
    }

    @Security.Authenticated(Secured.class)
    public static Result newPost() {
        return NewPost.create();
    }

    @Security.Authenticated(Secured.class)
    public static Result editPost(Long id) {
        return EditPost.edit(id);
    }


    /* ------ Show Blog Posts ------ */
    public static Result show(String title) {        
        List<Post> public_posts = (List<Post>) new PostDAO().getPubPostsByBlogname(title);
            if (public_posts.size() == 0) {
                return notFound(no_posts_found.render(new UserDAO().getUserbyBlogname(session("user"))));
            }            
        return ok(blog.render(public_posts, new UserDAO().getUserbyBlogname(session("user"))));
    }

}
