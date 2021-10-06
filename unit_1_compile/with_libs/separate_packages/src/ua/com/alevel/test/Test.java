package ua.com.alevel.test;

import org.apache.commons.lang3.StringUtils;

public class Test {

    private static final String TEXT = "Test.run";

    public void run() {
        System.out.println(TEXT);
        System.out.println(StringUtils.upperCase(TEXT));
    }
}