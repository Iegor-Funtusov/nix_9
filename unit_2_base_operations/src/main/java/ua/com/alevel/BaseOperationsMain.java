package ua.com.alevel;

public class BaseOperationsMain {

    public final static String TEST_CODE = "";

    private int _code = 10;
    private int code = 10;

    public static void main(String[] args) {
        main2("", "", "");
    }

    public static void main1(String args[]) {
        String[] strings = {"", "", ""};
        main(strings);
        main(new String[]{});
    }

    public static void main2(String ... args) {

    }

    public void test_test() { }

    public void TestTest() { }
}
