package models;

import play.db.ebean.*;
import play.Logger;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BlogEntity extends Model {

    final static Logger.ALogger logger = Logger.of(BlogEntity.class);
    
}
