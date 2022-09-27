package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HelperBase {
    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void type(By locator, String text){
        if(text != null) {
            WebElement element = wd.findElement(locator);
            element.click();
            element.clear();
            element.sendKeys(text); }
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public void submit() {
        WebElement loginButton = wd.findElement(By.cssSelector("button[type$='submit']"));
        loginButton.click();
    }

    public void waitUntil(int seconds, By locator) {
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(locator)));

    }

    public boolean isElementPresent(By locator){
        return wd.findElements(locator).size() > 0;
    }
}
