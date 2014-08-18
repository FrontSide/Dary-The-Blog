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
        List<Post> publicPosts = (List<Post>) new PostDAO().getAllByBlogname(title);
        List<Long> featuredPostsId = Post.getFeaturedPosts(publicPosts);
            if ((publicPosts.size() == 0) && (!title.equals(session("user")))) {
                return notFound(no_posts_found.render(new UserDAO().getByBlogname(session("user"))));
            }                
        return ok(blog.render(title, publicPosts, featuredPostsId, 
                new UserDAO().getByBlogname(session("user"))));
    }

}
