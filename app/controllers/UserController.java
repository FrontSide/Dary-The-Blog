package controllers;

import views.html.*;
import dao.*;
import models.User;
import models.UserLog;

import java.util.Date;

import play.*;
import play.mvc.*;
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
        return ok(register.render(Form.form(Login.class), Form.form(User.class), null));
    }

    /* ------ Check/Submit Login Form ------ */
    public static Result submitLogin() {

        logger.debug("receive data from form");         
        Form<Login> loginForm = Form.form(Login.class);
        Form<Login> filledForm = loginForm.bindFromRequest();

        // Validate Form
        logger.debug("validate form");
        if (filledForm.hasErrors()) {
            return badRequest(register.render(filledForm, Form.form(User.class), null));
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
        flash("success", "Hi. You successfully logged in to your blog \"" + userLog.user.blogname + "\"");

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
            return badRequest(register.render(Form.form(Login.class), filledForm, null));

        User user = filledForm.get(); 

        logger.debug("init persistence in Database");
        UserDAO edao = new UserDAO();
        edao.create(user); 

        //Success flash
        flash("success", "Great! You successfully signed up to dary and now your own blog called \"" + user.blogname + "\"!");

        logger.debug("render view");
        return Application.login();

    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result checkBlognameAvailable(String blogname) {
        if (new UserDAO().getUserbyBlogname(blogname) == null)
            return ok(Json.newObject().put("available", true));
        return ok(Json.newObject().put("available", false));        
    }

    /* Login Form Class 
       Not sure whether it belongs here but found it in the Play Doc
       --> http://www.playframework.com/documentation/2.1.0/JavaGuide4 */
    public static class Login {
        @Required public String name;
        @Required public String password;

        /* Check if user with this credentials exists in DB and return it */
        public User findUser() {
            return new UserDAO().getUserByLogin(this.name, this.password);
        }

        public String validate() {
            logger.debug("Login class validator");
            if (findUser() == null)
                return "Wrong Blogname/Mail - Password combination";
            return null;
        }

    }
}