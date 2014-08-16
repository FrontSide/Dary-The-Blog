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
    private final static int MIN_PASSWORD_LENGTH = 6;

    /* Explicit Setter Method to Encrypt password
       as SHA1 Hash
    Play uses setter methods if they are existing automatically!
    We still set the password with 
    "user.pssword = xxx" to set the password and NOT the directly the Setter!
    */ 
    public void setPassword(String password) {
        /* Need to check password lenght here additionally,
            otherwise a too short or even empty password could be hashed 
            --> hash would then be detected as long enough by the validator */
        this.password = (password.length() < MIN_PASSWORD_LENGTH) ? 
            this.password = password : User.hashPassword(password);
    }

    public String firstname;
    public String lastname;

    @Required
    @Email
    public String email;

    @Formats.DateTime(pattern="dd/MM/yyy")
    public Date registerDate = new Date();

    @OneToOne
    @JoinColumn(name = "profile_picture_id")
    public Picture profilePicture;

    /* Unique Views the blog has had *
    //TODO


    /* Password - Hash Method - usign Apache commons codec library */
    public static String hashPassword(String clear) {        
        logger.debug("Hash password!");
        return DigestUtils.sha1Hex(clear);
    }

    /* Validator for Registration */
    private final Map<String, List<ValidationError>> validationErrors = 
        new HashMap<String, List<ValidationError>>();
        
    private final List<ValidationError> passwordValidationErrors = 
        new ArrayList<ValidationError>();
        
    private final List<ValidationError> blognameValidationErrors = 
        new ArrayList<ValidationError>();
        
    private final List<ValidationError> emailValidationErrors = 
        new ArrayList<ValidationError>();

    public Map<String, List<ValidationError>> validate() {
        if (password.length() < MIN_PASSWORD_LENGTH) {            
            logger.error("Password not Long enough!");
            
            final String FIELD = "password";
            final String MESSAGE = "Your Password must be at least " 
                    + "6 characters long";
            
            passwordValidationErrors.add(new ValidationError(FIELD, MESSAGE));
            validationErrors.put(FIELD, passwordValidationErrors);
        }

        logger.debug("Validate Blogname \"" + this.blogname 
                + "\" for availability");
                
        if (new UserDAO().getByBlogname(this.blogname) != null) {
            logger.error("Blogname aleardy exists");
            
            final String FIELD = "blogname";
            final String MESSAGE = "Sorry, the blog \"" + this.blogname 
                    + "\" already exists!";
            
            blognameValidationErrors.add(new ValidationError(FIELD, MESSAGE));
            validationErrors.put(FIELD, blognameValidationErrors);
        }

        logger.error("Validate Email \"" + this.email + "\" for availability");
        if (new UserDAO().getByEmail(this.email) != null) {
            logger.error("Email aleardy exists");
            
            final String FIELD = "email";
            final String MESSAGE = "Sorry, a user with the email address \"" 
                    + this.email + "\" already exists!";

            emailValidationErrors.add(new ValidationError(FIELD, MESSAGE));
            validationErrors.put(FIELD, emailValidationErrors);
        }

        return (validationErrors.size() > 0) ? validationErrors : null;
    }
       
}
