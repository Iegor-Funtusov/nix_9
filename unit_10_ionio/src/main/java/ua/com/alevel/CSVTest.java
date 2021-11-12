package ua.com.alevel;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import javax.annotation.processing.FilerException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CSVTest {

    public void test() throws IOException {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setAge(10);
            user.setFirstName("setFirstName" + i);
            user.setLastName("setLastName" + i);
            user.setFullName(user.getFirstName() + " " + user.getLastName());
            users.add(user);
        }

        List<String[]> data = new ArrayList<>();

        String[] header = { "id", "age", "firstName", "lastName" };
        data.add(header);

        for (User user : users) {
            String[] strings = new String[4];
            strings[0] = user.getId();
            strings[1] = String.valueOf(user.getAge());
            strings[2] = user.getFirstName();
            strings[3] = user.getLastName();
            data.add(strings);
        }

        try(CSVWriter csvWriter = new CSVWriter(new FileWriter("users.csv"))) {
            csvWriter.writeAll(data);
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
        }

        try(CSVReader csvReader = new CSVReader(new FileReader("users.csv"))) {
            List<String[]> strings = csvReader.readAll();
            for (String[] string : strings) {
                for (String s : string) {
                    System.out.print(s + ", ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
        }
    }
}
