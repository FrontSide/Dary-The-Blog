package models;

import java.util.Date;
import javax.persistence.*;
import play.db.ebean.*;
import play.data.format.*;

import javax.validation.*;
import play.data.validation.Constraints.*;

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
    public String email;

    @Formats.DateTime(pattern="dd/MM/yyy")
    public Date registerDate;

    public static Finder<Long,User> find = 
      new Finder<Long,User>(Long.class, User.class);

}