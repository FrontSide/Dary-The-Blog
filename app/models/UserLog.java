package models;

import java.util.Date;
import javax.persistence.*;
import play.db.ebean.*;

import play.data.format.*;
import play.data.Form;

import javax.validation.*;
import play.data.validation.Constraints.*;

/**
  * UserLog Model
  * Stores a currently logged-in User
  * and its uuid (relating to cookie) 
  */
@Entity
//Add unique constraint for userid
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "user_id"))
public class UserLog extends Model {
    
    /* User UUID as saved in Cookie */
    @Id    
    public Long uuid;

    //Unique constrain for user set in table annotation
    @OneToOne
    @JoinColumn(name = "user_id")
    @Required
    public User user;

    @Required
    @Formats.DateTime(pattern="dd/MM/yyy HH:ii:ss")
    public Date loginDate;

    /**/
    public static Finder<Long,UserLog> find = 
      new Finder<Long,UserLog>(Long.class, UserLog.class);


    /* Uuid is generated out of IP-Address and User-ID */
    // currently UNUSED
    public UserLog generateUuid(String ipAddress)  {
      String joinedIp = ipAddress.replaceAll("[\\.:]","");
      Long time = this.loginDate.getTime(); //Date/Time as Milliseconds (UNIX)
      this.uuid =  Long.parseLong(
                      new StringBuilder()
                        .append(this.user.id)
                        .append(joinedIp).toString());
      return this; 
    }

}