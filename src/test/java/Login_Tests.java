import manager.DataProviderUser;
import models.User;
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



    @Test (dataProvider = "datalogin", dataProviderClass = DataProviderUser.class)
    public void loginSuccess(String email, String password) {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(email, password);
        logger.info("User logins with email: " + email + ", and password: " + password);
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().getMessage().contains("Logged in success"));
        logger.info("Assert passed");
    }

    @Test
    public void loginSuccessModel() {
        User user = new User().setEmail("marinatest@gmail.com").setPassword("Mmarina_1234");
        logger.info("User logins " + user.toString());
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().getMessage().contains("Logged in success"));
        logger.info("Assert passed");
    }

    @Test (dataProvider = "dataModelUser", dataProviderClass = DataProviderUser.class)
    public void loginSuccessModelDP(User user) {
        logger.info("User logins " + user.toString());
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().getMessage().contains("Logged in success"));
        logger.info("Assert passed");
    }

    @Test
    public void loginNegativeWrongEmailFormat() {
        User user = new User().setEmail("marinatestgmail.com").setPassword("Mmarina_1234");
        logger.info("User logins with email [marinatestgmail.com] and password [Mmarina_1234]");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        Assert.assertEquals(app.getHelperUser().errorMessage(), "It'snot look like email");
        logger.info("User gets error message [It'snot look like email]");
        Assert.assertFalse(app.getHelperUser().isYallaButtonActive());
        Assert.assertFalse(app.getHelperUser().isLogged());
        logger.info("Assert passed");
    }

    @Test
    public void loginNegativeWrongEmailOrPassword() {
        User user = new User().setEmail("marinatest@gmailcom").setPassword("Mmarina_1234");
        logger.info("User logins " + user.toString());
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().getMessage().contains("Wrong email or password"));
        logger.info("User gets error message [Wrong email or password]");
        Assert.assertTrue(app.getHelperUser().getTitleMessage().contains("Authorization error"));
        logger.info("Assert passed");
    }
}
