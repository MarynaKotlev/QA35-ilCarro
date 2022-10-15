import manager.ApplicationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class TestBase {
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    static ApplicationManager app = new ApplicationManager();

    @BeforeMethod
    public void getTestName(Method m){
        logger.info("START TEST with name: " + m.getName());
    }

    @BeforeSuite
    public void setUp() {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        //app.stop();
    }

    @AfterMethod
    public void logout() {
        if(app.getHelperUser().isLogged()) { app.getHelperUser().logout();}
    }








}
