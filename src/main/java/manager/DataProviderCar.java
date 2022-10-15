package manager;
import models.Car;
import org.testng.annotations.DataProvider;
import java.io.*;
import java.util.*;

public class DataProviderCar {

    @DataProvider
    public Iterator<Object[]> carValidData() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/car.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[]{
                    Car.builder()
                            .location(split[0])
                            .make(split[1])
                            .model(split[2])
                            .year(split[3])
                            .engine(split[4])
                            .fuel(split[5])
                            .gear(split[6])
                            .wD(split[7])
                            .doors(split[8])
                            .seats(split[9])
                            .car_class(split[10])
                            .fuelConsumption(split[11])
                            .carRegistrationNumber(split[12])
                            .price(split[13])
                            .distanceIncluded(split[14])
                            .feature(split[15])
                            .about(split[16])
                            .build()
            });
            line = reader.readLine();

        }

        return list.iterator();
    }
}
