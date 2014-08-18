package controllers;

import play.mvc.*;
import jsmessages.JsMessages;
import play.Logger;

public class JsMessageGetter extends Controller {

    final static Logger.ALogger logger = Logger.of(JsMessageGetter.class);    
        
    /* For i18n Strings in JS */
    /* Github: https://github.com/julienrf/play-jsmessages */
    final static jsmessages.JsMessages messages = 
                jsmessages.JsMessages.create(play.Play.application());

    /* Returns the localized string by request from JS */
    /* Called in templ.scala.html */ 
    public static Result jsMessages() {
        return ok(messages.generate("window.Messages"));
    }
}
