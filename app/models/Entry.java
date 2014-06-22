
package models;

import java.util.Date;
import javax.persistence.*;
import play.db.ebean.*;

import play.data.format.*;
import play.data.Form;

import javax.validation.*;
import play.data.validation.Constraints.*;

/* Blog Entry Model */
@Entity
public class Entry extends Model {
    
    /* Task ID  Unique Identifier */
    @Id    
    public Long id; 
    /* 
     * Sequcence is automatically created by ebean
     */  

    /* Task Description */
    @Required
    public String post_title;   

    @Required
    public String post_content; 

    @Formats.DateTime(pattern="dd/MM/yyy")
    public Date creDate = new Date();

    /* Open to public Flag */
    public boolean isPublished;

    /** Setter and Getter are automatically generated from play.
      * However, if you want to create your own getters/setters 
      * (e.g. for validation checking) you can do so and play
      * will use the existing mathods.

      * Play interprests every ""public"" field in an Entity class
      * as a property to persist
      **/

    /* Form Validators */
    public String validate() {

        if(post_title == null) {
            return "Please enter a title and conent!";
        }

        if(post_content == null) {
            return "Please enter a conent!";
        }
        return null;
    }

}