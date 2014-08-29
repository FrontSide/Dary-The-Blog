package factories;

import dao.*;
import models.*;
import play.db.ebean.Model.*;

public class PostDAOFactory implements DAOFactory {

    public DAO create() {    
        return new PostDAO().setFind(
                        new Finder<Long,Post>(Long.class, Post.class));
    }

}
