package dao;

import models.Comment;
import models.User;
import models.Post;
import java.util.List;

import play.Logger;

import play.db.ebean.Model.*;
import com.avaje.ebean.Ebean; 

public class CommentDAO implements DAO<Comment> {

    final Logger.ALogger logger = Logger.of(this.getClass());
  
    public static Finder<Long,Comment> find = 
        new Finder<Long,Comment>(Long.class, Comment.class);     

    public void create(Comment model) {
        logger.debug("Save Model");
        Ebean.save(model);
    }

    public void deleteById(Long id) {
        return;
    }

    public Comment getById(Long id) {
      return CommentDAO.find.where().eq("id", id).findUnique();
    }

    public List<Comment> getAll() {
      return null;
    }

    /* Fetch all Comments from a U */
    public List<Comment> getAllFromUser(User model) {
      logger.debug("Get Comments from user " + model.blogname);
      return CommentDAO.find.where()
                        .eq("post.user", model)
                        .eq("isDeleted", false)
                        .orderBy("creDate desc").findList();
    }
    
    public void markAsDeleted(Comment model) {
        logger.debug("Mark Comment " + model.id + " as deleted!");
        model.isDeleted = true;
        this.update(model);
    }

    public void update(Comment model) {
      logger.debug("Update Model");
      Ebean.update(model);
    }

}
