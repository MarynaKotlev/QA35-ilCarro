import manager.DataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Registration_Tests extends TestBase{
    @BeforeMethod (alwaysRun = true)
    public void preCond() {
        if(app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }
    @AfterMethod (alwaysRun = true)
    public void submitButtonClick() {
       app.getHelperUser().okButtonClick();
    }

    @Test (dataProvider = "regDataValid", dataProviderClass = DataProviderUser.class, enabled = false)
    public void registrationSuccessDP(User user) {
        logger.info("Test starts with name ---> registrationSuccessDP");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        logger.info("User registers with data: " + user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().getMessage().contains("You are logged in success"));
        Assert.assertEquals(app.getHelperUser().getTitleMessage(), "Registered");
        logger.info("Assert passed");
    }

    @Test(groups = {"smoke"})
    public void registrationSuccess() {
        logger.info("Test starts with name ---> registrationSuccess");
        int i = (int) (System.currentTimeMillis()/1000)%3600;
        User user = new User().setName("Test1").setLastName("Tester1").setEmail("tester"+i+"@mail.com").setPassword("Tester_1234");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        logger.info("User registers with data: " + user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().getMessage().contains("You are logged in success"));
        Assert.assertEquals(app.getHelperUser().getTitleMessage(), "Registered");
        logger.info("Assert passed");
    }

    @Test
    public void registrationFailWrongEmailFormat() {
        logger.info("Test starts with name ---> registrationFailWrongEmailFormat");
        User user = new User().setName("Test1").setLastName("Tester1").setEmail("testerForFailmail.com").setPassword("Tester_1234");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        logger.info("User registers with data: " + user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submitNoWait();
        Assert.assertFalse(app.getHelperUser().isYallaButtonActive());
        Assert.assertTrue(app.getHelperUser().errorMessage().contains("Wrong email format"));
        logger.info("Assert passed");
    }

    @Test
    public void registrationFailWrongPasswordFormat() {
        logger.info("Test starts with name ---> registrationFailWrongPasswordFormat");
        User user = new User().setName("Test1").setLastName("Tester1").setEmail("test44@mail.com").setPassword("d");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        logger.info("User registers with data: " + user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submitNoWait();
        Assert.assertFalse(app.getHelperUser().isYallaButtonActive());
        Assert.assertTrue(app.getHelperUser().errorMessage().contains("Password must contain minimum 8 symbols"));
        logger.info("Assert passed");
    }

    @Test (groups = {"sanity"})
    public void registrationFailMissedNameField() {
        logger.info("Test starts with name ---> registrationFailMissedNameField");
        User user = new User().setName("").setLastName("Tester1").setEmail("test44@mail.com").setPassword("Tester_1234");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        logger.info("User registers with data: " + user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submitNoWait();
        Assert.assertFalse(app.getHelperUser().isYallaButtonActive());
        Assert.assertTrue(app.getHelperUser().errorMessage().contains("Name is required"));
        logger.info("Assert passed");
    }

    @Test
    public void registrationFailMissedLastnameField() {
        logger.info("Test starts with name ---> registrationFailMissedNameField");
        User user = new User().setName("Test").setLastName("").setEmail("test44@mail.com").setPassword("Tester_1234");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        logger.info("User registers with data: " + user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submitNoWait();
        Assert.assertFalse(app.getHelperUser().isYallaButtonActive());
        Assert.assertEquals(app.getHelperUser().errorMessage(), "Last name is required");
        logger.info("Assert passed");
    }

    @Test
    public void registrationFailUserExists() {
        logger.info("Test starts with name ---> registrationFailUserExists");
        User user = new User().setName("Test1").setLastName("Tester1").setEmail("marinatest@gmail.com").setPassword("Tester_1234");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        logger.info("User registers with data: " + user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submitNoWait();
        Assert.assertTrue(app.getHelperUser().getMessage().contains("User with this email already exists"));
        Assert.assertEquals(app.getHelperUser().getTitleMessage(), "Registration error");
        logger.info("Assert passed");
    }


}
