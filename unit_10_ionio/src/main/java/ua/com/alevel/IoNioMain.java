package ua.com.alevel;

import com.google.gson.Gson;
import ua.com.alevel.io.IOCrudFileService;
import ua.com.alevel.nio.NioCrudFileService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IoNioMain {

    private static final String FILE = "file.txt";
    private static final String FILE_HIDDEN = ".file.txt";
    private static final String FILE_NEW = "new_file.txt";
    private static final String FOLDER = "folder";
    private static final String FOLDERS = "folder/folder1/folder2/folder3";

    public static void main(String[] args) throws IOException {

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

        UserItems items = new UserItems();
        items.setCount(users.size());
        items.setItems(users);

        Container<UserItems> container = new Container<>();
        container.setData(items);

        Gson gson = new Gson();
        String json = gson.toJson(container);
        System.out.println("json = " + json);

        FileWriter fileWriter = new FileWriter("users.json");
        fileWriter.write(json);
        fileWriter.flush();



//        SerialTest serialTest = new SerialTest();
//        serialTest.serial();
//        serialTest.deserial();
//        ReadWriteTest readWriteTest = new ReadWriteTest();
//        readWriteTest.test();
//        CrudFileService crudFileService = new NioCrudFileService();
//        crudFileService.create(FILE_HIDDEN, false);
//        crudFileService.create(FILE, false);
//        crudFileService.update(FILE, FILE_NEW, false);
//        crudFileService.create(FOLDER, true);
//        crudFileService.create(FOLDERS, true);
//        crudFileService.delete(FOLDER);
    }
}
