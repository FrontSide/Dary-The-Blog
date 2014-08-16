package dao;

import models.User;
import models.Picture;

import java.util.List;
import play.Logger;

import play.db.ebean.Model.*;
import com.avaje.ebean.Ebean; 
import com.avaje.ebean.Expr;

/* TODO: User DAO Factory !! */

public class UserDAO implements DAO<User> {

    final Logger.ALogger logger = Logger.of(this.getClass());
    
    public static Finder<Long,User> find = 
      new Finder<Long,User>(Long.class, User.class);
     
    public void create(User model) {
        logger.debug("Save Model");
        Ebean.save(model);
        logger.debug("Done");
    }

    public void deleteById(Long id) {

    }

    public User getById(Long id) {
        return null;
    }

    public List<User> getAll() {
      return null;
    }

    /**
      * Checks is User has entered a valid blogname or email address and
      * the korresponding valid password
      */
    public User getByLogin(String loginname, String password) {      
      return UserDAO.find.where().or(
                  Expr.eq("blogname", loginname),
                  Expr.eq("email", loginname)).eq("password", password).findUnique();
    }

    public User getByBlogname(String blogname) {
        logger.debug("get user by blogname :: " + blogname);
        return UserDAO.find.where().eq("blogname", blogname).findUnique();
    }

    public User getByEmail(String email) {
      return UserDAO.find.where().eq("email", email).findUnique();
    }
    
    /* Update user's profile Picture */
    public void updateProfilePicture(User user, Picture picture) {
        logger.debug("update profile picture");
        user.profilePicture = picture;
        update(user);
    }
    
    public void update(User model) {
      logger.debug("Update Model");
      Ebean.update(model);
    }

}
