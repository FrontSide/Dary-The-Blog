package dao;

import models.User;

import java.util.List;
import play.Logger;

import com.avaje.ebean.Ebean; 
import com.avaje.ebean.Expr;

import play.db.ebean.Model;

public class UserDAO implements DAO<User> {

    final Logger.ALogger logger = Logger.of(this.getClass());
     
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

    public User getUserByLogin(String loginname, String password) {      
      return User.find.where().or(
                  Expr.eq("blogname", loginname),
                  Expr.eq("email", loginname)).eq("password", password).findUnique();
    }

    public User getUserbyBlogname(String blogname) {
      return User.find.where().eq("blogname", blogname).findUnique();
    }

}