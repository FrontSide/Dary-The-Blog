package controllers;

import play.*;
import play.mvc.*;

import services.*;
import models.*;

import views.html.*;
import play.data.Form;
import play.Logger;

public class Application extends Controller {

    final static Logger.ALogger logger = Logger.of(Application.class);

    /* ------ Show Home Page ------ */
    public static Result home(String operation) {
        if (!operation.trim().equals("new"))
            return Application.initShowBlog();
        return Application.initCreateNewEntry();
    }

    /* ------ Show Blog Entries --- */
    public static Result initShowBlog() {
        return ok(blog.render("Welcome to the Blog"));
    }

    /* ------ New Blog Entry ------ */
    public static Result initCreateNewEntry() {

        logger.debug("called initCreateNewEntry"); 
        
        //TODO
        Form<Entry> entryForm = Form.form(Entry.class);

        logger.debug("render newentry.html");
        return ok(newentry.render(entryForm));
    }

}
