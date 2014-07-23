
package models;

import java.util.Date;
import javax.persistence.*;
import play.db.ebean.*;

import play.data.format.*;
import play.data.Form;

import javax.validation.*;
import play.data.validation.Constraints.*;

/**
  * Post Model
  * Stores a Post
  * related to a certain user 
  */
@Entity
public class Post extends Model {
    
    /* Task ID  Unique Identifier */
    @Id    
    public Long id; 
    /* 
     * Sequcence is automatically created by ebean
     */  

    /* Task Description */
    @Required
    public String title;   

    /* Type Lob makes a Blob saved as "text" in the psql-db */
    @Required
    @Lob
    public String content; 

    @OneToOne
    @JoinColumn(name = "user_id")
    public User user;

    @Formats.DateTime(pattern="dd/MM/yyy")
    public Date creDate;

    /* Open to public Flag */
    public boolean isPublished;

    /* Blog this Post Belongs to */

    /** Setter and Getter are automatically generated from play.
      * However, if you want to create your own getters/setters 
      * you can do so and play
      * will use the existing methods.

      * Play interprests every ""public"" field in an Entity class
      * as a property to persist
      **/

    /* Form Validators */
    /* With this method additional (to the annotation validators)
       validators can be defined */
    public String validate() {
      //None
      return null;
    }

    /* The find helps executing SELECT statemebts via Ebean
       See how it's used in the PostDAO */
    public static Finder<Long,Post> find = 
      new Finder<Long,Post>(Long.class, Post.class);

}