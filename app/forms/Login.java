package forms;

import models.User;
import play.data.validation.Constraints.Required;
import dao.UserDAO;

import play.Logger;

/* 	Login Form Class 
	Not sure whether it belongs here but found it in the Play Doc
	--> http://www.playframework.com/documentation/2.1.0/JavaGuide4 */
public class Login {
	
	final static Logger.ALogger logger = Logger.of(Login.class);

	@Required public String name;
    @Required public String password;

    /* Check if user with this credentials exists in DB and return it 
       Hashing before password! */ 
    public User findUser() {
        return new UserDAO().getByLogin(this.name, 
        		User.hashPassword(this.password));
    }

    public String validate() {
        logger.debug("Login class validator");
        if (findUser() == null)
            return "Wrong Blogname/Mail - Password combination";
        return null;
    }
	
}