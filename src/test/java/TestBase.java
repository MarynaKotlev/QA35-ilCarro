import manager.ApplicationManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {

    static ApplicationManager app = new ApplicationManager();

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
