import manager.DataProviderCar;
import models.Car;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewCarTests extends TestBase{

    @BeforeMethod
    public void preCondition() {
        if(!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().setEmail("marinatest@gmail.com").setPassword("Mmarina_1234"));
        }
    }

    @Test (dataProvider = "carValidData", dataProviderClass = DataProviderCar.class)
    public void addCarSuccessDP(Car car){
        logger.info("Test starts with name ---> addCarSuccess");
        app.helperCar().openCarForm();
        app.helperCar().fillCarForm(car);
        logger.info("User add car: " + car);
        app.helperCar().attachPhoto("C:\\Users\\UserONE\\Desktop\\car.jpg");
        logger.info("User attaches photo");
        app.helperCar().submit();
        Assert.assertTrue(app.helperCar().getMessage().contains("added successful"));
        Assert.assertEquals(app.getHelperUser().getTitleMessage(), "Car added");
        logger.info("Assert passed");
        app.helperCar().finishAddingCar();
        }

    @Test
    public void addCarSuccess(){
        logger.info("Test starts with name ---> addCarSuccess");
        Random random = new Random();
        int i = random.nextInt(1000)+100;
        Car car = Car.builder()
                .location("Haifa, Israel")
                .make("BMW")
                .model("X5")
                .year("2020")
                .engine("2.5")
                .fuel("Diesel")
                .gear("MT")
                .wD("RWD")
                .doors("4")
                .seats("5")
                .car_class("Sedan")
                .fuelConsumption("11")
                .carRegistrationNumber("11-000-"+i)
                .price("75")
                .distanceIncluded("100")
                .feature("type of features")
                .about("Lorem Ipsum")
                .build();

        app.helperCar().openCarForm();
        app.helperCar().fillCarForm(car);
        logger.info("User add car: " + car);
        app.helperCar().attachPhoto("C:\\Users\\UserONE\\Desktop\\car.jpg");
        logger.info("User attaches photo");
        app.helperCar().submit();
        Assert.assertTrue(app.helperCar().getMessage().contains("added successful"));
        Assert.assertEquals(app.getHelperUser().getTitleMessage(), "Car added");
        logger.info("Assert passed");
        app.helperCar().finishAddingCar();
    }

    }

