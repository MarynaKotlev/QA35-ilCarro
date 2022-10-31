import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Search_Tests extends TestBase{

    @BeforeMethod (alwaysRun = true)
    public void afterSearch() {
      app.helperSearch().afterSearch();
    }

    @Test
    public void searchCurrentMonthSuccess() {
        app.helperSearch().searchCurrentMonth("Tel Aviv", "10/27/2022", "10/30/2022");
        app.helperSearch().submit();
        Assert.assertTrue(app.helperSearch().isListOfCarsAppeared());
    }

    @Test(groups = {"smoke"})
    public void searchNextMonthSuccess() {
        app.helperSearch().searchNextMonth("Haifa", "11/25/2022", "11/30/2022");
        app.helperSearch().submit();
        Assert.assertTrue(app.helperSearch().isListOfCarsAppeared());
    }

    @Test
    public void searchAnyPeriodSuccess() {
        app.helperSearch().selectAnyPeriod("Jerusalem", "02/20/2023", "04/10/2023");
        app.helperSearch().submit();
        Assert.assertTrue(app.helperSearch().isListOfCarsAppeared());
    }

    @Test
    public void searchInPastNegative() {
        app.helperSearch().searchInPast("Rehovot Israel", "10/10/2022", "10/30/2022");
        app.helperSearch().submitNoWait();
        Assert.assertFalse(app.getHelperUser().isYallaButtonActive());
        Assert.assertTrue(app.helperSearch().isErrorMessageDisplayed());
    }
}
