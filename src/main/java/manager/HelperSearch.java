package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperSearch extends HelperBase{
    public HelperSearch(WebDriver wd) {
        super(wd);
    }

    public void searchCurrentMonth(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clearTextboxDates();
        click(By.id("dates"));
        String[] from = dateFrom.split("/");
        click(By.xpath("//div[text()=' "+ from[1] +" ']"));
        String[] to = dateTo.split("/");
        click(By.xpath("//div[text()=' "+ to[1] +" ']"));

    }

    public void searchInPast(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clearTextboxDates();
        click(By.id("dates"));
        click(By.cssSelector(".cdk-overlay-container"));
        String date = dateFrom + " - " + dateTo;
        wd.findElement(By.id("dates")).sendKeys(date);
    }

    public void searchNextMonth(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clearTextboxDates();
        click(By.id("dates"));
        click(By.cssSelector("button[aria-label='Next month']"));
        String[] from = dateFrom.split("/");
        click(By.xpath("//div[text()=' "+ from[1] +" ']"));
        String[] to = dateTo.split("/");
        click(By.xpath("//div[text()=' "+ to[1] +" ']"));
    }

    public void searchCurrentMonth2(String city, String dataFrom, String dataTo) {
        typeCity(city);
        clearTextboxDates();
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
        pause(500);
        click(By.cssSelector("div.pac-item"));
        pause(500);
    }

    public boolean isListOfCarsAppeared() {
        return wd.findElements(By.cssSelector(".search-results")).size() > 0;
    }


    public void selectAnyPeriod(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clearTextboxDates();
        click(By.id("dates"));
        LocalDate now = LocalDate.now();
        logger.info("year --> "+ now.getYear());
        logger.info("day --> "+ now.getDayOfMonth());
        logger.info("month --> "+ now.getMonthValue());


       /* String[] from = dateFrom.split("/");
        int diffYear = Integer.parseInt(from[2]) - now.getYear(); */

        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyy"));

        int diffYear;
        int diffMonth;
        diffYear = from.getYear() - now.getYear();
        if(diffYear == 0) {
            diffMonth = from.getMonthValue() - now.getMonthValue();
        } else {
            diffMonth = 12 - now.getMonthValue() + from.getMonthValue();
        }

        clickNextMonth(diffMonth);
        String locator = String.format("//div[text()=' %s ']", from.getDayOfMonth());
        click(By.xpath(locator));

        //********************

        diffYear = to.getYear() - from.getYear();
        if(diffYear == 0) {
            diffMonth = to.getMonthValue() - from.getMonthValue();
        } else {
            diffMonth = 12 - from.getMonthValue() + to.getMonthValue();
        }
        clickNextMonth(diffMonth);
        locator = String.format("//div[text()=' %s ']", to.getDayOfMonth());
        click(By.xpath(locator));
    }

    private void clickNextMonth(int count) {
        for(int i = 0; i < count; i++) {
            click(By.cssSelector("button[aria-label='Next month']"));
        }
    }


    public boolean isErrorMessageDisplayed() {
        WebElement errorM = wd.findElement(By.cssSelector("div[class*='error']"));
        if (errorM.getText().contains("You can't pick date before today")) return true;
        return false;
    }

    public void afterSearch() {
        click(By.cssSelector("img[alt='logo']"));
    }
    public void clearTextboxDates() {
        WebElement element = wd.findElement(By.id("dates"));
        String osName = System.getProperty("os.name");
        if(osName.startsWith("Windows")) {
            logger.info("OS is ---> " + osName);
            // ctrl + A
            element.sendKeys(Keys.CONTROL, "a");
        } else {
            logger.info("OS is ---> " + osName);
            // command + A
            element.sendKeys(Keys.COMMAND, "a");
        }
        element.sendKeys(Keys.DELETE);
    }


}
