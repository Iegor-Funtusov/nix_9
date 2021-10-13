package ua.com.alevel;

import ua.com.alevel.task1.Task1;
import ua.com.alevel.task2.Task2;

import java.util.ArrayList;
import java.util.List;

public class BaseOperationsMain {

    public static void main(String[] args) {
        System.out.println("BaseOperationsMain.main");
        List<TaskHelper> taskHelpers = new ArrayList<>();
        taskHelpers.add(new Task1());
        taskHelpers.add(new Task2());
        ProgramRun.run(taskHelpers);
    }
}
