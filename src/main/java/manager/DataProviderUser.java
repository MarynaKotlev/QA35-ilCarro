package manager;
import models.User;
import org.asynchttpclient.ClientStats;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {

    @DataProvider
    public Iterator<Object[]> datalogin(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"marinatest@gmail.com", "Mmarina_1234"});
        list.add(new Object[]{"marinatest@gmail.com", "Mmarina_1234"});
        list.add(new Object[]{"marinatest@gmail.com", "Mmarina_1234"});


        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> dataModelUser() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User().setEmail("marinatest@gmail.com").setPassword("Mmarina_1234")});
        list.add(new Object[]{new User().setEmail("marinatest@gmail.com").setPassword("Mmarina_1234")});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> regDataValid() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/DataUser.csv")));
        String line = reader.readLine();
        while (line!=null) {
            String[] split = line.split(",");
            list.add(new Object[]{new User().setName(split[0]).setLastName(split[1]).setEmail(split[2]).setPassword(split[3])});
            line = reader.readLine();
        }
        return list.iterator();
    }
}
/*
    public Iterator<Object[]> loginData() {
        List<Object[]> list = new ArrayList<>();
        return list.iterator();
    }
 */
