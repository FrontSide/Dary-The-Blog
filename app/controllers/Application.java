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
    public static Result home(String operation) {
        if (!operation.trim().equals("new"))
            return Application.blog();
        return NewEntry.create();
    }

    /* ------ Show Blog Entries ------ */
    public static Result blog() {
        return ok(blog.render((List<Entry>) new EntryDAO().getAll()));
    }

    /* ------ ERROR PAGES ------ */
    /* TODO */
    /* Internal Server Error 500 */
    public static Result onError(RequestHeader req, Throwable t) {
        return internalServerError(errorpage.render(500));
    }
    /* Internal Server Error 404 */
    public static Result onActionNotFound(RequestHeader req) {
        return internalServerError(errorpage.render(404));
    }

}
