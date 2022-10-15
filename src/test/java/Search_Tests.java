import org.testng.Assert;
import org.testng.annotations.Test;

public class Search_Tests extends TestBase{

    @Test
    public void searchCurrentMonthSuccess() {
        app.helperSearch().searchCurrentMonth("Tel Aviv", "10/25/2022", "10/30/2022");
        app.helperSearch().submit();
        Assert.assertTrue(app.helperSearch().isListOfCarsAppeared());
    }

    @Test
    public void searchNextMonthSuccess() {
        app.helperSearch().searchNextMonth("Tel Aviv", "11/25/2022", "11/30/2022");
        app.helperSearch().submit();
        Assert.assertTrue(app.helperSearch().isListOfCarsAppeared());
    }
}
