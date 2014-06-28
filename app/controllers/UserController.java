package controllers;

import views.html.*;
import dao.*;
import models.User;

import play.*;
import play.mvc.*;
import play.Logger;
import play.data.Form;

public class UserController extends Controller {

    final static Logger.ALogger logger = Logger.of(UserController.class);

    /* ------ New Blog Post ------ */
    public static Result signup() {

        logger.debug("called create new user"); 
        
        Form<User> userForm = Form.form(User.class);

        logger.debug("render register.html");
        return ok(register.render(userForm, userForm));

    }

    /* ------ Check/Submit Login Form ------ */
    public static Result submitLogin() {

        logger.debug("receive data from form");         
        Form<User> loginForm = Form.form(User.class);
        Form<User> filledForm = loginForm.bindFromRequest();

        // Validate Form
        logger.debug("validate form");
        if (filledForm.hasErrors()) {
            return badRequest(register.render(filledForm, Form.form(User.class)));
        }

        User user = filledForm.get(); 

        // Validate Login Credentials
        if (new UserDAO().getUserByLogin(user.blogname, user.password) == null) {
            logger.debug("login incorrect");
            return badRequest(register.render(filledForm, Form.form(User.class)));
        }
              
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
        if (filledForm.hasErrors()) {
            return badRequest(register.render(Form.form(User.class), filledForm));
        }

        User user = filledForm.get(); 

        logger.debug("init persistence in Database");
        UserDAO edao = new UserDAO();
        edao.create(user); 

        logger.debug("render view");
        return redirect("/new");

    }

}