package controllers;

import views.html.*;
import dao.*;
import models.User;
import models.UserLog;
import forms.Login;

import java.util.Date;

import play.mvc.*;
import play.i18n.Messages;
import play.Logger;
import play.data.Form;
import play.data.validation.Constraints.*;
import play.libs.Json;
/* Useful Doc for Json 
   http://www.playframework.com/documentation/2.3.x/api/java/play/libs/Json.html
   */

public class UserController extends Controller {

    final static Logger.ALogger logger = Logger.of(UserController.class);

    /* ------ New Blog Post ------ */
    public static Result signup() {
        logger.debug("called create new user"); 
        logger.debug("render register.html");
        return ok(register.render(Form.form(Login.class), 
        		Form.form(User.class), null));
    }

    /* ------ Check/Submit Login Form ------ */
    public static Result submitLogin() {

        logger.debug("receive data from form");         
        Form<Login> loginForm = Form.form(Login.class);
        Form<Login> filledForm = loginForm.bindFromRequest();

        // Validate Form
        logger.debug("validate form");
        if (filledForm.hasErrors()) {
            return badRequest(register.render(filledForm, 
            		Form.form(User.class), null));
        }

        Login login = filledForm.get();               
        logger.debug("login correct");

        logger.debug("create userLog Entry with uuid");
        UserLog userLog = new UserLog();
        userLog.user = login.findUser();
        userLog.loginDate = new Date();
        // currently UNUSED 
        userLog.generateUuid(request().remoteAddress());        
        new UserLogDAO().create(userLog);
        
        //Set Session
        session().clear();
        session("user", userLog.user.blogname);

        //Success flash        
        flash("success", Messages.get("FLASH_LOGIN_SUCCESS") + " \"" 
        			+ userLog.user.blogname + "\"");

        return redirect("/blog/"+userLog.user.blogname);

    }

    /* ------ Check/Submit Register Form ------ */
    public static Result submitRegister() {

        logger.debug("receive data from form");
        Form<User> signupForm = Form.form(User.class);
        Form<User> filledForm = signupForm.bindFromRequest();

        // Validate Form
        logger.debug("validate form");
        if (filledForm.hasErrors())
            return badRequest(register.render(
            		Form.form(Login.class), filledForm, null));

        User user = filledForm.get(); 

        logger.debug("init persistence in Database");
        new UserDAO().create(user); 

        //Success flash       
        flash("success",  Messages.get("FLASH_SIGNUP_SUCCESS") 
                                + " \"" + user.blogname + "\"!");

        logger.debug("render view");
        return Application.home();

    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result checkBlognameAvailable(String blogname) {
        if (new UserDAO().getByBlogname(blogname) == null)
            return ok(Json.newObject().put("available", true));
        return ok(Json.newObject().put("available", false));        
    }
   
}
