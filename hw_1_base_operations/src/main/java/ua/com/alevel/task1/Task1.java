package ua.com.alevel.task1;

import ua.com.alevel.TaskHelper;

import java.io.BufferedReader;
import java.io.IOException;

public class Task1 implements TaskHelper {

    public void run(BufferedReader reader) throws IOException {
        System.out.println("Task1.run");
        String text = reader.readLine();
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            System.out.println("i = " + chars[i]);
            System.out.println("chars = " + (int) chars[i]);
        }
        System.out.println("text = " + text);
    }
}
