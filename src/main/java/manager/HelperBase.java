package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class HelperBase {
    Logger logger= LoggerFactory.getLogger(HelperBase.class);
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
        new WebDriverWait(wd, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(wd.findElement(By.cssSelector("button[type$='submit']"))));
        wd.findElement(By.cssSelector("button[type$='submit']")).click();
    }
    public void submitNoWait() {
        wd.findElement(By.cssSelector("button[type$='submit']")).click();
    }

    public void waitUntil(int seconds, By locator) {
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(locator)));

    }

    public boolean isElementPresent(By locator){
        return wd.findElements(locator).size() > 0;
    }

    public String getMessage() {
        waitUntil(5, By.cssSelector("div.dialog-container"));

        return wd.findElement(By.cssSelector("h2.message")).getText();
    }

    public String getTitleMessage() {
        waitUntil(5, By.cssSelector("div.dialog-container"));

        return wd.findElement(By.cssSelector("div.dialog-container>h1.title")).getText();
    }
}
