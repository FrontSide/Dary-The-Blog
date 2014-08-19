
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
public class Comment extends Model {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -1403273957046905695L;
	
	final static Logger.ALogger logger = Logger.of(Post.class);
	/**/

    @Id    
    public Long id;
    
    /* Post comment is related to */
    @OneToOne
    @Required
    @JoinColumn(name = "post_id")
    public Post post;
    
    @Required
    @Lob
    public String content; 

    /* User who has written comment */
    @OneToOne
    @Required
    @JoinColumn(name = "user_id")
    public User user;
    
    /* Is deleted Flag */
    public boolean isDeleted = false;

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

}
