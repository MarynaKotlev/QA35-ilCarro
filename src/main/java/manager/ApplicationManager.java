package manager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class ApplicationManager {
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    WebDriver wd;
    HelperUser helperUser;
    HelperCar helperCar;
    HelperSearch helperSearch;

    public void init() {
        WebDriverListener listener = new ListenerWD();

        wd = new ChromeDriver();
        logger.info("Chrome Driver was opened");
        wd = new EventFiringDecorator<>(listener).decorate(wd);

        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wd.navigate().to("https://ilcarro-1578153671498.web.app/search");
        logger.info("Current URL is ---> " + wd.getCurrentUrl());

        helperUser = new HelperUser(wd);
        helperCar = new HelperCar(wd);
        helperSearch = new HelperSearch(wd);
    }

    public void stop() {
        wd.quit();
    }

    public HelperUser getHelperUser() {
        return helperUser;
    }

    public HelperCar helperCar() { return helperCar;}
    public HelperSearch helperSearch() { return helperSearch;}
}
