package dao;

import models.*;
import java.util.List;

import play.Logger;

import play.db.ebean.Model.*;
import com.avaje.ebean.Ebean; 

public class CommentDAO extends DAOImpl<Comment> {

    final Logger.ALogger logger = Logger.of(this.getClass());

    /* Fetch all Comments from a U */
    public List<Comment> getAllFromUser(User u) {
      logger.debug("Get Comments from user " + u.blogname);
      return this.find.where()
                        .eq("post.user", u)
                        .eq("isDeleted", false)
                        .orderBy("creDate desc").findList();
    }
    
    public void markAsDeleted(Comment c) {
        logger.debug("Mark Comment " + c.id + " as deleted!");
        c.isDeleted = true;
        this.update(c);
    }
    
    @Override
    public void update(Comment model) {
        Ebean.update(model);
    }
    
}
