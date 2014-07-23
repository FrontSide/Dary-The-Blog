package dao;

import models.Post;
import dao.UserDAO;

import java.util.List;
import play.Logger;

import com.avaje.ebean.Ebean; 
import play.db.ebean.Model;

public class PostDAO implements DAO<Post> {

    /** Create logger for this class
      * Play uses Logback as Logging Engine
      * Main Logger could also be accessed just by typing
      * e.g. Logger.debug("Lorem ipsum")
      * However every class should have its own logger
      *
      * Logging level can be set in application.conf
      */ 
    final Logger.ALogger logger = Logger.of(this.getClass());
     

    /** Ebean is play's default O/R Mapper  
      * it automatically creates DDL Files to create tables 
      * if evolution is enabled. (see ebean.properties)
      */
    public void create(Post model) {

        //TODO: Check typesafety

        logger.debug("Save Model");
        Ebean.save(model);
        logger.debug("Done");
    }

    public void deleteById(Long id) {

    }

    public Post getById(Long id) {
        return null;
    }

    public List<Post> getAll() {
      return null;
    }

    public List<Post> getPubPostsByBlogname(String blogname) {
      logger.debug("Get Posts from \"" + blogname + "\"");
      return Post.find.where().eq("isPublished", true).eq("user.blogname", blogname).findList();
    }

}