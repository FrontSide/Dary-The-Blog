import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.libs.F.*;
import play.Logger;

import models.*;
import dao.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import static org.fluentlenium.core.filter.FilterConstructor.*;

public class IntegrationTest {

    final static Logger.ALogger logger = Logger.of(IntegrationTest.class);

    /**
     * start server
     * set up test data
     */    
    @Before
    public void setup() {

        logger.debug("Start Up Server");
        running(testServer(9000), new Runnable() {
            public void run() {            
            
        logger.debug("Done");

        logger.debug("Persist Test-Data...");
        EntryDAO dao = new EntryDAO();

        logger.debug("Entry 1");
        Entry e1 = new Entry();
        e1.post_title = "First Post";
        e1.post_content = "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut a tellus vulputate, pharetra urna eget, adipiscing lacus. Phasellus egestas erat vel justo malesuada, sit amet imperdiet sapien mattis. Nam sit amet nisi vel ligula accumsan auctor blandit sed quam. Cras fringilla tempus diam, ut viverra nulla posuere luctus. Aliquam erat volutpat. Donec sodales interdum posuere. Quisque sodales nulla urna, sed cursus neque faucibus sed. Sed quis lectus aliquet, vehicula lectus ut, vulputate dolor. Integer metus mi, venenatis vitae sapien quis, rhoncus tempor mauris. Fusce eget arcu varius, vestibulum lorem id, ultrices nunc. Etiam mollis eros at libero varius tincidunt. Morbi volutpat mi tortor, a aliquet risus hendrerit vitae. Interdum et malesuada fames ac ante ipsum primis in faucibus. Vivamus porttitor metus sit amet felis ultrices, ac dapibus urna gravida. Duis sed mattis diam. </p><pre><code> public interface Model&lt;T&gt; {}</code></pre><p><strong>Bye. Bye. </strong></p>";
        e1.isPublished = true;
        dao.create(e1);

        logger.debug("Entry 2");
        Entry e2 = new Entry();
        e2.post_title = "Second Post";
        e2.post_content = "<p><strong>Important !</strong>- this- that- etc...</p><p>&quot;<em>This is a Quote</em>&quot;</p><h3>Look at this guy</h3><p><img alt='enter image description here' title='enter image title here' src='http://media1.giphy.com/media/7kpQyA3qn2bVC/giphy.gif'/></p><p>Nunc quis metus nisi. Pellentesque egestas aliquam lobortis. Ut volutpat accumsan nibh nec pretium. In consectetur vitae justo quis commodo. Vestibulum eget massa dapibus, iaculis augue id, sagittis metus. Pellentesque ultrices elementum tincidunt. Curabitur a tristique sapien, eget sagittis lorem. </p>";
        e2.isPublished = true;
        dao.create(e2);

        logger.debug("Setup Finished.");

        }
        });

    }

    /**
     * add your integration test here
     * in this example we just check if the welcome page is being shown
     */
    
    @Test
    public void runInBrowser() {        
    }

}
