package forms;

import models.User;
import play.data.validation.Constraints.Required;
import dao.UserDAO;
import factories.UserDAOFactory;

import play.Logger;
import play.i18n.Messages;

/* 	Login Form Class 
	Not sure whether it belongs here but found it in the Play Doc
	--> http://www.playframework.com/documentation/2.1.0/JavaGuide4 */
public class Login {
	
	private final static Logger.ALogger logger = Logger.of(Login.class);
    private final static UserDAO userDAO = (UserDAO) new UserDAOFactory().create();

	@Required public String name;
    @Required public String password;

    /* Check if user with this credentials exists in DB and return it 
       Hashing before password! */ 
    public User findUser() {
        return userDAO.getByLogin(this.name, User.hashPassword(this.password));
    }

    public String validate() {
        logger.debug("Login class validator");
        if (findUser() == null)
            return Messages.get("VALID_LOGIN_FAIL");
        return null;
    }
	
}
