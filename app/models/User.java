package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.data.format.*;

import javax.validation.*;
import play.data.validation.Constraints.*;
import play.data.validation.ValidationError;

/* User Model */
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

    /* Validator for Registration */
    Map<String, List<ValidationError>> validationErrors = new HashMap<String, List<ValidationError>>();    
    public Map<String, List<ValidationError>> validate() {
        if (password.length() < 6) {
            List<ValidationError> passwordErrors = new ArrayList<ValidationError>();
            passwordErrors.add(new ValidationError("password", "Your Password must be at least 6 characters long"));
            validationErrors.put("password", passwordErrors);
        }        
        return (validationErrors.size() > 0) ? validationErrors : null;
    }
   
    public static Finder<Long,User> find = 
      new Finder<Long,User>(Long.class, User.class);

}