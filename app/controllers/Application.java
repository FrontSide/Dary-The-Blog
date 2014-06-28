package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;
import java.util.*;

import models.*;
import dao.*;
import views.html.*;
import play.Logger;

public class Application extends Controller {

    final static Logger.ALogger logger = Logger.of(Application.class);

    /* ------ Show Home Page ------ */
    public static Result home() {
        /* Redirect to Logn/Signup Form if User not logged in */
        return UserController.signup();
    }

    /* ------ Show Blog Posts ------ */
    public static Result show(String title) {
        return ok(blog.render((List<Post>) new PostDAO().getPubPostsFromBlog(title)));
    }

}
