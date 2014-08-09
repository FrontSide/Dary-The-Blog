package dao;

import models.Picture;

import java.util.List;
import play.Logger;

import javax.persistence.*;
import play.db.ebean.Model.*;
import com.avaje.ebean.Ebean; 
import com.avaje.ebean.Expr;

public class PictureDAO implements DAO<Picture> {

    final Logger.ALogger logger = Logger.of(this.getClass());
    
    public static Finder<Long,Picture> find = 
      new Finder<Long,Picture>(Long.class, Picture.class);
     
    public void create(Picture model) {
        logger.debug("Save Model");
        Ebean.save(model);
        logger.debug("Done");
    }

    public void deleteById(Long id) {

    }

    public Picture getById(Long id) {
        return null;
    }

    public List<Picture> getAll() {
        return null;
    }
    
}