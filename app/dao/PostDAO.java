package dao;

import models.*;
import java.util.List;

import play.db.ebean.Model.*;
import com.avaje.ebean.Ebean; 

public class PostDAO extends DAOImpl<Post> {

    /* Fetch all Posts from a Blog except the archived ones */
    public List<Post> getAllByBlogname(String blogname) {
      logger.debug("Get not-Archived Posts from \"" + blogname + "\"");
      return this.find.where().eq("user.blogname", blogname)
                                .eq("isArchived",false)
                                .orderBy("creDate desc").findList();
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
