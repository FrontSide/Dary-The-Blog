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
        return Application.blog();
    }

    /* ------ Show Blog Entries ------ */
    public static Result blog() {
        return ok(blog.render((List<Entry>) new EntryDAO().getAll()));
    }
}
