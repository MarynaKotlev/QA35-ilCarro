package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HelperUser extends HelperBase {
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//a[text()=' Logout ']"));
    }

    public void logout() {
        wd.findElement(By.xpath("//a[text()=' Logout ']")).click();

    }

    public void openLoginForm() {
        WebElement loginTab = wd.findElement(By.cssSelector("div.header a:last-child"));
        loginTab.click();

    }

    public void openRegistrationForm() {
        click(By.cssSelector("a[href='registration']"));
    }

    public void fillRegistrationForm(String name, String lastName, String email, String password) {
        type(By.cssSelector("#name"), email);
        type(By.cssSelector("#lastName"), lastName);
        type(By.cssSelector("#email"), email);
        type(By.cssSelector("#password"), password);

        click(By.cssSelector(".checkbox-label.terms-label"));
    }

    public void fillRegistrationForm(User user) {
        type(By.cssSelector("#name"), user.getName());
        type(By.cssSelector("#lastName"), user.getLastName());
        type(By.cssSelector("#email"), user.getEmail());
        type(By.cssSelector("#password"), user.getPassword());
    }


    public void fillLoginForm(String email, String password) {
        type(By.cssSelector("#email"), email);
        type(By.cssSelector("#password"), password);
    }

    public void fillLoginForm(User user) {
        type(By.cssSelector("#email"), user.getEmail());
        type(By.cssSelector("#password"), user.getPassword());
    }

    public boolean isYallaButtonActive() {
        return wd.findElement(By.cssSelector("button[type='submit']")).isEnabled();
    }

    public String errorMessage() {
        return wd.findElement(By.cssSelector(".error")).getText();
    }


    public String getMessage() {
        waitUntil(5, By.cssSelector("div.dialog-container"));

        return wd.findElement(By.cssSelector("h2.message")).getText();
    }

    public String getTitleMessage() {
        waitUntil(5, By.cssSelector("div.dialog-container"));

        return wd.findElement(By.cssSelector("div.dialog-container>h1.title")).getText();
    }

    public void okButtonClick() {
        if (isElementPresent(By.cssSelector("button.positive-button.ng-star-inserted")))
            click(By.cssSelector("button.positive-button.ng-star-inserted"));
    }

    public void checkPolicy() {
        WebElement label = wd.findElement(By.cssSelector("label[for='terms-of-use']"));
        Rectangle rect = label.getRect();
        int width = rect.getWidth();
        int height = rect.getHeight();
        int x = rect.getX();
        int y = rect.getY();
        int xOffSet = width/2;

        Actions actions = new Actions(wd);
        actions.moveToElement(label, -xOffSet, 0).click().release().perform();
    }
}
