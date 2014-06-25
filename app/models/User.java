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

    public String firstname;
    public String lastname;

    @Formats.DateTime(pattern="dd/MM/yyy")
    public Date registerDate;


}