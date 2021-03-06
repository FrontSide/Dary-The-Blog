package models;

import java.util.Date;
import javax.persistence.*;

import play.db.ebean.Model;
import play.data.format.*;
import play.data.validation.Constraints.*;

/**
  * UserLog Model
  * Stores a currently logged-in User
  * and its uuid (relating to cookie) 
  */
@Entity
public class UserLog extends BlogEntity {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -1460235900980899245L;

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
    
    /* Uuid is generated out of IP-Address and User-ID */
    // currently UNUSED
    public UserLog generateUuid(String ipAddress)  {
      String joinedIp = ipAddress.replaceAll("[\\.:]","");
      //Get Last 7 digits of UNIX Timestamp
      String strTime = Long.toString(this.loginDate.getTime());
      String strTimeL7 = strTime.substring(strTime.length()-8, strTime.length()-1);

      this.uuid =  Long.parseLong(
                      new StringBuilder()
                        .append(joinedIp)
                        .append(strTimeL7)
                        .append(this.user.id).toString());
      return this; 
    }

}
