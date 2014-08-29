package dao;

import models.*;

import java.util.List;
import play.Logger;

import play.db.ebean.Model.*;
import com.avaje.ebean.Ebean; 
import com.avaje.ebean.Expr;

/* TODO: User DAO Factory !! */

public class UserDAO extends DAOImpl<User> {

    final Logger.ALogger logger = Logger.of(this.getClass());
    
    /**
      * Checks is User has entered a valid blogname or email address and
      * the korresponding valid password
      */
    public User getByLogin(String loginname, String password) {      
      return this.find.where().or(
                  Expr.eq("blogname", loginname),
                  Expr.eq("email", loginname)).eq("password", password).findUnique();
    }

    public User getByBlogname(String blogname) {
        logger.debug("get user by blogname :: " + blogname);
        return this.find.where().eq("blogname", blogname).findUnique();
    }

    public User getByEmail(String email) {
      return this.find.where().eq("email", email).findUnique();
    }
    
    /* Update user's profile Picture */
    public void updateProfilePicture(User user, Picture picture) {
        logger.debug("update profile picture");
        user.profilePicture = picture;
        update(user);
    }
    
    @Override
    public void update(User model) {
        Ebean.update(model);
    }

}
