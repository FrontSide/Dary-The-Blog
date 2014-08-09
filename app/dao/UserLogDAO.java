package dao;

import models.UserLog;

import java.util.List;
import java.lang.UnsupportedOperationException;

import play.Logger;

import play.db.ebean.Model.*;
import com.avaje.ebean.Ebean; 

public class UserLogDAO implements DAO<UserLog> {

    final Logger.ALogger logger = Logger.of(this.getClass());
    
    /**/
    public static Finder<Long,UserLog> find = 
      new Finder<Long,UserLog>(Long.class, UserLog.class);
     
    public void create(UserLog model) {
        Ebean.save(model);
    }

    public void deleteById(Long id) {
    }

    public void deleteByBlogname(String blogname) {
        for (UserLog ul : getByBlogname(blogname)) {
            Ebean.delete(ul);
        }
    }

    public List<UserLog> getByBlogname(String blogname) {
        logger.debug("get UserLog by blogname::" + blogname);
        return UserLogDAO.find.where().eq("user.blogname", blogname).findList();
    }

    public UserLog getById(Long id) {
        return UserLogDAO.find.where().eq("uuid", id).findUnique();
    }

    public List<UserLog> getAll() {
        logger.error("getAll() is not available on UserLogDAO");
        throw new UnsupportedOperationException("Method cannot be called on this object!");
    }

}
