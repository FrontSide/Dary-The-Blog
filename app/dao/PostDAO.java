package dao;

import models.Post;
import java.util.List;

import play.Logger;

import play.db.ebean.Model.*;
import com.avaje.ebean.Ebean; 

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

    /* The find helps executing SELECT statemebts via Ebean
       See how it's used in the PostDAO */
    public static Finder<Long,Post> find = 
    new Finder<Long,Post>(Long.class, Post.class);     

    /** Ebean is play's default O/R Mapper  
      * it automatically creates DDL Files to create tables 
      * if evolution is enabled. (see ebean.properties)
      */
    public void create(Post model) {
        logger.debug("Save Model");
        Ebean.save(model);
        logger.debug("Done");
    }

    public void deleteById(Long id) {
    }

    public Post getById(Long id) {
      logger.debug("Get Posts by id :: \"" + id + "\"");
      return PostDAO.find.where().eq("id", id).findUnique();
    }

    public List<Post> getAll() {
      return null;
    }

    /* Fetch all Posts from a Blog except the archived ones */
    public List<Post> getAllByBlogname(String blogname) {
      logger.debug("Get not-Archived Posts from \"" + blogname + "\"");
      return PostDAO.find.where().eq("user.blogname", blogname).eq("isArchived",false).orderBy("creDate desc").findList();
    }
    
    public void markByIdAsArchived(Long id) {
      logger.debug("Mark Post with id \"" + id + "\" as archived!");
      Post model = PostDAO.find.where().eq("id", id).findUnique();
      model.isArchived = true;
      update(model);
    }

    public void update(Post model) {
      logger.debug("Update Model");
      Ebean.update(model);
    }

}
