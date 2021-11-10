package ua.com.alevel;

import java.io.*;
import java.util.stream.Stream;

public class ReadWriteTest {

    public void test() throws IOException {
        Reader reader;
        Writer writer;
        InputStream inputStream;
        OutputStream outputStream;

//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader bufferedReader = new BufferedReader(new FileReader("new_file.txt"));
        String text;
        while (bufferedReader.ready()) {
            text = bufferedReader.readLine();
            System.out.println("text = " + text);
        }

        Stream<String> lines = bufferedReader.lines();
        lines.forEach(System.out::println);
//        System.out.println("lines = " + lines.count());

        FileWriter fileWriter = new FileWriter("new_file.txt", true);
        fileWriter.write("\n");
        fileWriter.write("bla bla");
        fileWriter.flush();
    }
}
