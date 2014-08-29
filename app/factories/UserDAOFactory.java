package factories;

import dao.*;
import models.*;
import play.db.ebean.Model.*;

public class UserDAOFactory implements DAOFactory {

    public DAO create() {    
        return new UserDAO().setFind(
                        new Finder<Long,User>(Long.class, User.class));    
    }

}
