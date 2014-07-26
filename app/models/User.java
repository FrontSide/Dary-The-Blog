package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.data.format.*;
import play.Logger;

import dao.UserDAO;

import javax.validation.*;
import play.data.validation.Constraints.*;
import play.data.validation.ValidationError;

/**
  * User Model
  * Stores a User's information and its blogname
  * Blogname must be unique
  */
@Entity
@Table(name="blog_user")
public class User extends Model {

    @Id    
    public Long id; 

    @Required
    public String blogname;

    @Required
    public String password;

    public String firstname;
    public String lastname;

    @Required
    @Email
    public String email;

    @Formats.DateTime(pattern="dd/MM/yyy")
    public Date registerDate;

    final static Logger.ALogger logger = Logger.of(User.class);

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
        if (new UserDAO().getUserbyBlogname(this.blogname) != null) {
            logger.error("Blogname aleardy exists");
            List<ValidationError> blogExistingErrors = new ArrayList<ValidationError>();
            blogExistingErrors.add(new ValidationError("blogname", "Sorry, the blog \"" + this.blogname + "\" already exists!"));
            validationErrors.put("blogname", blogExistingErrors);
        }    

        logger.error("Validate Email \"" + this.email + "\" for availability");
        if (new UserDAO().getUserbyEmail(this.email) != null) {
            logger.error("Email aleardy exists");
            List<ValidationError> emailExistingErrors = new ArrayList<ValidationError>();
            emailExistingErrors.add(new ValidationError("email", "Sorry, a user with the email address \"" + this.email + "\" already exists!"));
            validationErrors.put("email", emailExistingErrors);
        }    

        return (validationErrors.size() > 0) ? validationErrors : null;
    }
   
    public static Finder<Long,User> find = 
      new Finder<Long,User>(Long.class, User.class);

}