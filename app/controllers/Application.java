package controllers;

import play.*;
import play.mvc.*;

import services.*;

import views.html.*;
import play.Logger;

public class Application extends Controller {

    final static Logger.ALogger logger = Logger.of(Application.class);

    /* ------ Show Home Page ------ */
    public static Result home(String operation) {
        if (!operation.trim().equals("new"))
            return Application.blog();
        return NewEntry.create();
    }

    /* ------ Show Blog Entries --- */
    public static Result blog() {
        return ok(blog.render("Welcome to the Blog"));
    }

}
