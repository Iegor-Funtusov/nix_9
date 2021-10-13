package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ProgramRun {

    public static void run(List<TaskHelper> taskHelpers) {
        preview();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String event;
        try {
            while ((event = reader.readLine()) != null) {
                for (int i = 0; i < taskHelpers.size(); i++) {
                    if (Integer.parseInt(event) == i) {
                        taskHelpers.get(i).run(reader);
                    }
                }

//                switch (event) {
//                    case "1" : {
//                        new Task1().run(reader);
//                    } break;
//                    case "2" : {
//                        new Task2().run();
//                    } break;
//                    case "0" : {
//                        System.exit(0);
//                    } break;
//                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void preview() {
        System.out.println("if you need first task, please select 1");
        System.out.println("if you need second task, please select 2");
        System.out.println("if you need exit task, please select 0");
        System.out.println("Select you event:");
        System.out.println();
    }
}
