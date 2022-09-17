package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperUser extends HelperBase {
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public boolean isLogged() {
        List<WebElement> list = wd.findElements(By.cssSelector("div.header a:nth-child(5)"));
        return list.size() > 0;
    }

    public void logout() {
        wd.findElement(By.cssSelector("div.header a:nth-child(5)")).click();

    }

    public void openLoginForm() {
        WebElement loginTab = wd.findElement(By.cssSelector("div.header a:last-child"));
        loginTab.click();

    }

    public void openRegistrationForm() {
        WebElement registrationTab = wd.findElement(By.cssSelector("a[href='registration']"));
        registrationTab.click();
    }

    public void fillRegistrationForm(String name, String lastName, String email, String password) {
        WebElement inputName = wd.findElement(By.cssSelector("#name"));
        inputName.click();
        inputName.clear();
        inputName.sendKeys(name);

        WebElement inputLastName = wd.findElement(By.cssSelector("#lastName"));
        inputLastName.click();
        inputLastName.clear();
        inputLastName.sendKeys(lastName);

        WebElement inputEmail = wd.findElement(By.cssSelector("#email"));
        inputEmail.click();
        inputEmail.clear();
        inputEmail.sendKeys(email);

        WebElement inputPassword = wd.findElement(By.cssSelector("#password"));
        inputPassword.click();
        inputPassword.clear();
        inputPassword.sendKeys(password);

        WebElement checkbox = wd.findElement(By.cssSelector(".checkbox-label.terms-label"));
        checkbox.click();
    }

    public void submitRegistration() {
        wd.findElement(By.cssSelector("button[type='submit']")).click();
        wd.findElement(By.cssSelector("button.positive-button.ng-star-inserted")).click();

    }


    public void fillLoginForm(String email, String password) {
        WebElement inputEmail = wd.findElement(By.cssSelector("#email"));
        inputEmail.click();
        inputEmail.clear();
        inputEmail.sendKeys(email);

        WebElement inputPassword = wd.findElement(By.cssSelector("#password"));
        inputPassword.click();
        inputPassword.clear();
        inputPassword.sendKeys(password);

    }

    public void submitLogin() {
        WebElement loginButton = wd.findElement(By.cssSelector("button[type$='submit']"));
        loginButton.click();
        wd.findElement(By.cssSelector("button.positive-button.ng-star-inserted")).click();
    }
}
