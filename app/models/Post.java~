
package models;

import java.util.Date;
import javax.persistence.*;

import play.data.format.*;
import play.data.validation.Constraints.*;

import play.db.ebean.*;

/**
  * Post Model
  * Stores a Post
  * related to a certain user 
  */
@Entity
public class Post extends Model {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -1403273957046905695L;

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
    public Date creDate = new Date();

    /* Open to public Flag */
    public boolean isPublished;

    /* Archived Flag (will not be displayed on the blog page if true, 
       just for version control) */
    public boolean isArchived;
    
    /* Featured Flag
     * These posts are represented as Buttons on the top of a blog page 
     */
    public boolean isFeatured;  
 
    /* Every newer version is assigned a rootPost i.e. 
       the post that this one is made of 
       only has a value if rootPost was edited */
    public Post rootPost;

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
   

}
