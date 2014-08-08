package models;

import javax.persistence.*;
import play.db.ebean.*;

import javax.validation.*;
import play.data.validation.Constraints.*;

/**
  * Picture Model
  * Stores the Attributes (not the Picture itself) of a Picture
  * related to a certain user 
  */
@Entity
public class Picture extends Model {

    @Id    
    public Long id; 

    @Required
    public User user;

}