import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login_Tests extends TestBase {

    @BeforeMethod
    public void preCond() {
        if(app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @Test
    public void loginSuccess() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("marinatest@gmail.com", "Mmarina_1234");
        app.getHelperUser().submitLogin();
    }
}
