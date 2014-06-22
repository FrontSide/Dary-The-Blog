package controllers;

import models.*;
import dao.*;
import views.html.*;

import play.*;
import play.mvc.*;
import play.Logger;
import play.data.Form;

public class NewEntry extends Controller {

    final static Logger.ALogger logger = Logger.of(NewEntry.class);

    /* ------ New Blog Entry ------ */
    public static Result create() {

        logger.debug("called create new entry"); 
        
        //TODO
        Form<Entry> entryForm = Form.form(Entry.class);

        logger.debug("render newentry.html");
        return ok(newentry.render(entryForm));
    }

    /* ------ Submit ------ */
    public static Result submit() {

        logger.debug("receive data from form");         
        Form<Entry> entryForm = Form.form(Entry.class);
        Entry entry = entryForm.bindFromRequest().get();        

        logger.debug("init persistence in Database");
        EntryDAO edao = new EntryDAO();
        edao.create(entry); 

        logger.debug("render blog view");
        return ok(blog.render("New Form Was Created"));
    }

}