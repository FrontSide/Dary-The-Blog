package dao;

import models.User;
import models.UserLog;

import java.util.List;
import java.lang.UnsupportedOperationException;

import play.Logger;
import play.mvc.Http;
import play.mvc.Http.RequestHeader;
import play.mvc.Http.RequestHeader.*;

import com.avaje.ebean.Ebean; 
import com.avaje.ebean.Expr;

public class UserLogDAO implements DAO<UserLog> {

    final Logger.ALogger logger = Logger.of(this.getClass());
     
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
        return UserLog.find.where().eq("user.blogname", blogname).findList();
    }

    public UserLog getById(Long id) {
        return UserLog.find.where().eq("uuid", id).findUnique();
    }

    public List<UserLog> getAll() {
        logger.error("getAll() is not available on UserLogDAO");
        throw new UnsupportedOperationException("Method cannot be called on this object!");
    }

}