package models;

import java.util.*;
import javax.persistence.*;

import play.data.format.*;
import play.Logger;

import dao.UserDAO;

import play.db.ebean.*;
import play.data.validation.Constraints.*;
import play.data.validation.ValidationError;

/* Apache Commons Codec Library for Hashing Password
   Dependency Maintained in build.sbt */
import org.apache.commons.codec.digest.*;

/**
  * User Model
  * Stores a User's information and its blogname
  * Blogname must be unique
  */
@Entity
@Table(name="blog_user")
public class User extends Model {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5432253295031618357L;

	final static Logger.ALogger logger = Logger.of(User.class);
    /**/

    @Id    
    public Long id; 

    @Required
    public String blogname;

    @Required
    public String password;

    /* Explicit Setter Method to Encrypt password
       as SHA1 Hash
    Play uses setter methods if they are existing automatically!
    We still set the password with 
    "user.pssword = xxx" to set the password and NOT the directly the Setter!
    */ 
    public void setPassword(String password) {
        this.password = User.hashPassword(password);
    }

    public String firstname;
    public String lastname;

    @Required
    @Email
    public String email;

    @Formats.DateTime(pattern="dd/MM/yyy")
    public Date registerDate = new Date();

    /* Password - Hash Method - usign Apache commons codec library */
    public static String hashPassword(String clear) {        
        logger.debug("Hash password!");
        return DigestUtils.sha1Hex(clear);
    }

    /* Validator for Registration */
    Map<String, List<ValidationError>> validationErrors = new HashMap<String, List<ValidationError>>();    
    public Map<String, List<ValidationError>> validate() {
        if (password.length() < 6) {
            logger.error("Password not Long enough!");
            List<ValidationError> passwordErrors = new ArrayList<ValidationError>();
            passwordErrors.add(new ValidationError("password", "Your Password must be at least 6 characters long"));
            validationErrors.put("password", passwordErrors);
        }

        logger.error("Validate Blogname \"" + this.blogname + "\" for availability");
        if (new UserDAO().getByBlogname(this.blogname) != null) {
            logger.error("Blogname aleardy exists");
            List<ValidationError> blogExistingErrors = new ArrayList<ValidationError>();
            blogExistingErrors.add(new ValidationError("blogname", "Sorry, the blog \"" + this.blogname + "\" already exists!"));
            validationErrors.put("blogname", blogExistingErrors);
        }    

        logger.error("Validate Email \"" + this.email + "\" for availability");
        if (new UserDAO().getByEmail(this.email) != null) {
            logger.error("Email aleardy exists");
            List<ValidationError> emailExistingErrors = new ArrayList<ValidationError>();
            emailExistingErrors.add(new ValidationError("email", "Sorry, a user with the email address \"" + this.email + "\" already exists!"));
            validationErrors.put("email", emailExistingErrors);
        }    

        return (validationErrors.size() > 0) ? validationErrors : null;
    }
       
}
