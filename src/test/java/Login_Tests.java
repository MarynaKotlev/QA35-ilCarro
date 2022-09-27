import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login_Tests extends TestBase {

    @BeforeMethod
    public void preCond() {
        if(app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @AfterMethod
    public void submitButtonClick() {
        app.getHelperUser().okButtonClick();
    }

    @Test
    public void loginSuccess() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("marinatest@gmail.com", "Mmarina_1234");
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().getMessage().contains("Logged in success"));
    }

    @Test
    public void loginSuccessModel() {
        User user = new User().setEmail("marinatest@gmail.com").setPassword("Mmarina_1234");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().getMessage().contains("Logged in success"));
    }

    @Test
    public void loginNegativeWrongEmailFormat() {
        User user = new User().setEmail("marinatestgmail.com").setPassword("Mmarina_1234");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        Assert.assertEquals(app.getHelperUser().errorMessage(), "It'snot look like email");
        Assert.assertFalse(app.getHelperUser().isYallaButtonActive());
        Assert.assertFalse(app.getHelperUser().isLogged());
    }

    @Test
    public void loginNegativeWrongEmailOrPassword() {
        User user = new User().setEmail("marinatest@gmailcom").setPassword("Mmarina_1234");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().getMessage().contains("Wrong email or password"));
        Assert.assertTrue(app.getHelperUser().getTitleMessage().contains("Authorization error"));
    }
}
