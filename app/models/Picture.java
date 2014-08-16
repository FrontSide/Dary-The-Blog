package models;

import javax.persistence.*;

import javax.validation.*;
import play.data.validation.Constraints.*;

import play.db.ebean.*;

/**
  * Picture Model
  * Stores the Attributes (not the Picture itself) of a Picture
  * related to a certain user 
  *
  * THIS MODEL IS CURRENTLY NOT REALLY USED FOR ANYTHING 
  *
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
        @OneToOne
        @JoinColumn(name = "user_id")
        public User user;

}
