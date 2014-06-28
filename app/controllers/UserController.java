package controllers;

import views.html.*;
import dao.*;
import models.User;

import play.*;
import play.mvc.*;
import play.Logger;
import play.data.Form;
import play.data.validation.Constraints.*;

public class UserController extends Controller {

    final static Logger.ALogger logger = Logger.of(UserController.class);

    /* ------ New Blog Post ------ */
    public static Result signup() {
        logger.debug("called create new user"); 
        logger.debug("render register.html");
        return ok(register.render(Form.form(Login.class), Form.form(User.class)));
    }

    /* ------ Check/Submit Login Form ------ */
    public static Result submitLogin() {

        logger.debug("receive data from form");         
        Form<Login> loginForm = Form.form(Login.class);
        Form<Login> filledForm = loginForm.bindFromRequest();

        // Validate Form
        logger.debug("validate form");
        if (filledForm.hasErrors()) {
            return badRequest(register.render(filledForm, Form.form(User.class)));
        }

        Login login = filledForm.get(); 
              
        logger.debug("login correct");

        logger.debug("render view");
        return redirect("/new");

    }

    /* ------ Check/Submit Register Form ------ */
    public static Result submitRegister() {

        logger.debug("receive data from form");
        Form<User> signupForm = Form.form(User.class);
        Form<User> filledForm = signupForm.bindFromRequest();

        // Validate Form
        logger.debug("validate form");
        if (filledForm.hasErrors())
            return badRequest(register.render(Form.form(Login.class), filledForm));

        User user = filledForm.get(); 

        logger.debug("init persistence in Database");
        UserDAO edao = new UserDAO();
        edao.create(user); 

        logger.debug("render view");
        return redirect("/new");

    }

    /* Login Form Class 
       Not sure whether it belongs here but found it in the Play Doc
       --> http://www.playframework.com/documentation/2.1.0/JavaGuide4 */
    public static class Login {
        @Required public String name;
        @Required public String password;

        public String validate() {
            logger.debug("Login class validator");
            if (new UserDAO().getUserByLogin(name, password) == null) {
                logger.debug("credentials wrong");
                return "Wrong Blogname/Mail - Password combination";
            }
            return null;
        }

    }
}