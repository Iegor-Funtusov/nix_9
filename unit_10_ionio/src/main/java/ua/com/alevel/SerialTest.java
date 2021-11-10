package ua.com.alevel;

import java.io.*;
import java.util.UUID;

public class SerialTest {

    private User user;

    public SerialTest() {
        user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setAge(10);
        user.setFirstName("setFirstName");
        user.setLastName("setLastName");
        user.setFullName("setFirstName setLastName");
    }

    public void serial() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("user.out"));
            objectOutputStream.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserial() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("user.out"));
            Object o = objectInputStream.readObject();
            user = (User) o;
            System.out.println("user = " + user);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
