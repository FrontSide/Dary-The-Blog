package dao;

import models.*;
import java.util.List;

import play.Logger;

import play.db.ebean.Model.*;
import com.avaje.ebean.Ebean; 

public class PostDAO extends DAOImpl<Post> {

    /** Create logger for this class
      * Play uses Logback as Logging Engine
      * Main Logger could also be accessed just by typing
      * e.g. Logger.debug("Lorem ipsum")
      * However every class should have its own logger
      *
      * Logging level can be set in application.conf
      */ 
    final Logger.ALogger logger = Logger.of(this.getClass());

    /* Fetch all Posts from a Blog except the archived ones */
    public List<Post> getAllByBlogname(String blogname) {
      logger.debug("Get not-Archived Posts from \"" + blogname + "\"");
      return this.find.where().eq("user.blogname", blogname).eq("isArchived",false).orderBy("creDate desc").findList();
    }
    
    public void markByIdAsArchived(Long id) {
        logger.debug("Mark Post with id \"" + id + "\" as archived!");
        markAsArchived(this.find.where().eq("id", id).findUnique());
    }
    
    public void markAsArchived(Post model) {
        model.isArchived = true;
        update(model);
    }

    @Override
    public void update(Post model) {
        Ebean.update(model);
    }

}
