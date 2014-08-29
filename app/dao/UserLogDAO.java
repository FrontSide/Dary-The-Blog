package dao;

import models.UserLog;

import java.util.List;
import java.lang.UnsupportedOperationException;

import play.Logger;

import play.db.ebean.Model.*;
import com.avaje.ebean.Ebean; 

/** Currently unused **/
public class UserLogDAO extends DAOImpl<UserLog> {
    
    public void deleteByBlogname(String blogname) {
        for (UserLog ul : getByBlogname(blogname)) {
            Ebean.delete(ul);
        }
    }

    public List<UserLog> getByBlogname(String blogname) {
        logger.debug("get UserLog by blogname::" + blogname);
        return this.find.where().eq("user.blogname", blogname).findList();
    }

}
