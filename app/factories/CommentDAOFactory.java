package factories;

import dao.*;
import models.*;
import play.db.ebean.Model.*;

public class CommentDAOFactory implements DAOFactory {

    public DAO create() {    
        return new CommentDAO().setFind(
                        new Finder<Long,Comment>(Long.class, Comment.class));
    }

}
