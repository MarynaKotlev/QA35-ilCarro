import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Registration_Tests extends TestBase{
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
    public void registrationSuccess() {
        int i = (int) (System.currentTimeMillis()/1000)%3600;
        User user = new User().setName("Test1").setLastName("Tester1").setEmail("tester"+i+"@mail.com").setPassword("Tester_1234");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().getMessage().contains("You are logged in success"));
        Assert.assertEquals(app.getHelperUser().getTitleMessage(), "Registered");
    }

    @Test
    public void registrationFailWrongEmailFormat() {
        User user = new User().setName("Test1").setLastName("Tester1").setEmail("testerForFailmail.com").setPassword("Tester_1234");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        Assert.assertFalse(app.getHelperUser().isYallaButtonActive());
        Assert.assertTrue(app.getHelperUser().errorMessage().contains("Wrong email format"));
    }

    @Test
    public void registrationFailWrongPasswordFormat() {
        User user = new User().setName("Test1").setLastName("Tester1").setEmail("test44@mail.com").setPassword("d");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        Assert.assertFalse(app.getHelperUser().isYallaButtonActive());
        Assert.assertTrue(app.getHelperUser().errorMessage().contains("Password must contain minimum 8 symbols"));
    }

    @Test
    public void registrationFailMissedNameField() {
        User user = new User().setName("").setLastName("Tester1").setEmail("test44@mail.com").setPassword("Tester_1234");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        Assert.assertFalse(app.getHelperUser().isYallaButtonActive());
        Assert.assertTrue(app.getHelperUser().errorMessage().contains("Name is required"));
    }

    @Test
    public void registrationFailMissedLastnameField() {
        User user = new User().setName("Test").setLastName("").setEmail("test44@mail.com").setPassword("Tester_1234");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        Assert.assertFalse(app.getHelperUser().isYallaButtonActive());
        Assert.assertEquals(app.getHelperUser().errorMessage(), "Last name is required");
    }

    @Test
    public void registrationFailUserExists() {
        User user = new User().setName("Test1").setLastName("Tester1").setEmail("marinatest@gmail.com").setPassword("Tester_1234");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().getMessage().contains("User with this email already exists"));
        Assert.assertEquals(app.getHelperUser().getTitleMessage(), "Registration error");
    }


}
