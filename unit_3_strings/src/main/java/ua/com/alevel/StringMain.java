package ua.com.alevel;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StringMain {

    private static final String name1 = "name1";
    private static final String name2 = "name2";

    public static void main(String[] args) {

        String s = "test";
        System.out.println((int) 'u');
        System.out.println(s.contains("u"));
        System.out.println(s.indexOf(117));
        System.out.println(s.indexOf("u", 3));

        System.out.println(s.substring(1, 3));

        String ss = new String("test");
        ss = "test";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT FROM ");
        stringBuilder.append(User.class.getSimpleName());
        stringBuilder.append(" where ");

        String q = "SELECT FROM ";
        q += User.class.getSimpleName();
        q += " where ";



//        int sum = 0;
//        for (int i = 0; i < 100; i++) {
//            sum += i;
//        }


//        int a = 20;
//        int res1 = a * 2;
//        int res2 = a << 1;
//
//        System.out.println("res1 = " + res1);
//        System.out.println("res2 = " + res2);


//        for (int j = 0; j < 100; j++) {
//            long start = System.currentTimeMillis();
//            List<Integer> integers = new ArrayList<>();
//            for (int i = 0; i < 1_000_000; i++) {
//                integers.add(i);
//            }
//            long end = System.currentTimeMillis() - start;
//            System.out.println("end = " + end);
//        }


//        String u = "\n";
//        System.out.println("u = " + !u.isEmpty());
//        System.out.println("u = " + !u.isBlank());
//
//        System.out.println("u = " + StringUtils.isNotEmpty(u));
//        System.out.println("u = " + StringUtils.isNotBlank(u));
//
//
//
//
//        User user1 = new User();
//        user1.setName(name1);
//
//        User user2 = user1;
//        user2.setName(name2);
//
//        System.out.println("user1 = " + user1);
//
//        List<User> users = new ArrayList<>();
//        for (int i = 0; i < 1000; i++) {
//            User user = new User();
//            Random random = new Random();
//            int r = random.nextInt(2);
//            if (r == 0) {
//                user.setName(name1);
//            } else {
//                user.setName(name2);
//            }
//            users.add(user);
//        }
//
//        long count = users.stream().filter(user -> user.getName().equals(name1)).count();
//        System.out.println("count = " + count);
    }
}
