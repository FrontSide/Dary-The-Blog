package controllers;

import play.mvc.*;
import play.i18n.Messages;
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
    public static Result home() {
        
	/* If user is logged in. Send user to own blog */
        if (session("user") != null) {
            return showBlog(session("user"));
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
        flash("info", Messages.get("FLASH_LOGOUT_SUCCESS"));

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
    public static Result showBlog(String title) {
            
        /* TODO: UserDAO Factory */        
        User loggedUser = new UserDAO().getByBlogname(session("user"));
    
        User blogOwner = new UserDAO().getByBlogname(title);        
        if (blogOwner == null) return notFound(e404.render(loggedUser));
        
        List<Post> posts = (List<Post>) new PostDAO().getAllByBlogname(title);
            
        return ok(blog.render(title, posts, loggedUser));
    }
    
    public static Result showAbout() {
        return ok(about.render(
                        new UserDAO().getByBlogname(session("user"))));
    }
    
    /* 403 forbidden redirect */
    public static Result noAllow() {
        return forbidden(e403.render(
                        new UserDAO().getByBlogname(session("user"))));
    }

}
