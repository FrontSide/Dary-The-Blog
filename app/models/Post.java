
package models;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

import play.Logger;
import play.data.format.*;
import play.data.validation.Constraints.*;

import play.db.ebean.*;

import java.text.SimpleDateFormat;

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
	
	final static Logger.ALogger logger = Logger.of(Post.class);
	/**/

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

    /* User who has written post */
    @OneToOne
    @JoinColumn(name = "user_id")
    public User user;

    @Formats.DateTime(pattern="dd/MM/yyy")
    public Date creDate = new Date();
    
    /* Getter for creDate to show readable Date on website
       This is not an 'actual getter' but rather something like a 
       proxy to convert the Date into a readable String */
    public String getCreDate() {    
        logger.debug("reformatting date...");
        SimpleDateFormat df = new SimpleDateFormat("dd. MMMM yyy HH:mm");
        return df.format(this.creDate);
    }
        
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
    @OneToOne
    @JoinColumn(name = "root_post_id")
    public Post rootPost;
    
    /* Checks if a comment belongs to this Post or to its root post 
        RECURSIVE untill no rootPost encountered
        invoced in blog.scala.html */
    public boolean belongsToComment(Comment c) {
        if (c.post.equals(this)) return true;
        if (rootPost == null)  return false;
        if (rootPost.belongsToComment(c)) return true;
        return false;
    }
    
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
    
    /* Get All featured Posts out of a lsit of posts */
    public static List<Long> getFeaturedPosts(List<Post> posts) {
        List<Long> featuredPostsId = new ArrayList<Long>();
        for (Post p : posts) {
            if (p.isFeatured && p.isPublished) featuredPostsId.add(p.id);
        }
        return featuredPostsId;
    }
   

}
