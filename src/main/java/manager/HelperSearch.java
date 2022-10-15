package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperSearch extends HelperBase{
    public HelperSearch(WebDriver wd) {
        super(wd);
    }

    public void searchCurrentMonth(String city, String dateFrom, String dateTo) {
        typeCity(city);
        click(By.id("dates"));
        String[] from = dateFrom.split("/");
        click(By.xpath("//div[text()=' "+ from[1] +" ']"));
        String[] to = dateTo.split("/");
        click(By.xpath("//div[text()=' "+ to[1] +" ']"));

    }

    public void searchNextMonth(String city, String dateFrom, String dateTo) {
        typeCity(city);
        click(By.id("dates"));
        click(By.cssSelector("button[aria-label='Next month']"));
        String[] from = dateFrom.split("/");
        click(By.xpath("//div[text()=' "+ from[1] +" ']"));
        String[] to = dateTo.split("/");
        click(By.xpath("//div[text()=' "+ to[1] +" ']"));
    }

    public void searchCurrentMonth2(String city, String dataFrom, String dataTo) {
        typeCity(city);
        click(By.id("dates"));
        String[] from = dataFrom.split("/");
        String locator = String.format("//div[text()=' %s ']", from[1]);
        click(By.xpath(locator));
        String[] to = dataTo.split("/");
        String locator2 = String.format("//div[text()=' %s ']", to[1]);
        click(By.xpath(locator2));

    }

    private void typeCity(String city) {
        type(By.id("city"), city);
        click(By.cssSelector("div.pac-item"));
        pause(500);
    }

    public boolean isListOfCarsAppeared() {
        return wd.findElements(By.cssSelector(".search-results")).size() > 0;
    }


}
