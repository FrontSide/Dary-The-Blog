package models;

import javax.persistence.*;

import javax.validation.*;
import play.data.validation.Constraints.*;

import play.db.ebean.*;

/**
  * Picture Model
  * Stores the Attributes (not the Picture itself) of a Picture
  * related to a certain user 
  */
@Entity
public class Picture extends Model {

       /**
        * 
        */
        private static final long serialVersionUID = -2040159760548658856L;

        @Id    
        public Long id; 

        @Required
        public User user;

}