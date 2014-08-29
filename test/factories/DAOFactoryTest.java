import static org.fest.assertions.Assertions.*;
import org.junit.*;

import play.Logger;
import play.test.WithApplication;
import play.test.Helpers;
import play.test.Helpers.*;

import factories.*;
import models.*;
import dao.*;

public class DAOFactoryTest extends WithApplication {

    final static Logger.ALogger logger = Logger.of(DAOFactoryTest.class);
         
    @Before
    public void setup() {
        
    }
            
    @Test
    public void generateUserDAO() {
        Helpers.running(Helpers.fakeApplication(Helpers.inMemoryDatabase("test")), new Runnable() {
            public void run() {
                DAO dao = new UserDAOFactory().create();        
                Assert.assertTrue(dao instanceof UserDAO);        
            }
        });
    }

    
}
