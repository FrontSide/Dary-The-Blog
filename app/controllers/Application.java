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
    public static Result login() {
        /* Redirect to Logn/Signup Form */
        return UserController.signup();
    }

    public static Result logout() {
        /* Destroy User session and Return to Login-Page */
        session().clear();
        return UserController.signup();
    }

    @Security.Authenticated(Secured.class)
    public static Result newPost() {
        return NewPost.create();
    }

    /* ------ Show Blog Posts ------ */
    public static Result show(String title) {
        List<Post> public_posts = (List<Post>) new PostDAO().getPubPostsByBlogname(title);
            if (public_posts.size() == 0) {
                return notFound(no_posts_found.render());
            }
        return ok(blog.render(public_posts, new UserDAO().getUserbyBlogname(session("user"))));
    }

}
