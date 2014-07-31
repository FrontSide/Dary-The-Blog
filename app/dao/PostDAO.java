package dao;

import models.Post;
import dao.UserDAO;

import java.util.List;
import java.util.Date;

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
        logger.debug("Save Model");
        Ebean.save(model);
        logger.debug("Done");
    }

    public void deleteById(Long id) {
    }

    public Post getById(Long id) {
      logger.debug("Get Posts by id :: \"" + id + "\"");
      return Post.find.where().eq("id", id).findUnique();
    }

    public List<Post> getAll() {
      return null;
    }

    public List<Post> getPostsByBlogname(String blogname) {
      logger.debug("Get Posts from \"" + blogname + "\"");
      return Post.find.where().eq("user.blogname", blogname).findList();
    }

    public void markPostByIdAsArchived(Long id) {
      logger.debug("Mark Post with id \"" + id + "\" as archived!");
      Post model = Post.find.where().eq("id", id).findUnique();
      model.isArchived = true;
      update(model);
    }

    public void update(Post model) {
      logger.debug("Update Model");
      Ebean.update(model);
    }

}