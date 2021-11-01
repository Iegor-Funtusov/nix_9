package ua.com.alevel;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import static ua.com.alevel.Const.CAPACITY;

public class SetTest {

    private Set<User> hashSet = new HashSet<>();
    private Set<Integer> linkedHashSet = new LinkedHashSet<>();
    private Set<User> treeSet = new TreeSet<>();

    public void test() {
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(0L);
            user.setAge(10);
            user.setName("test");
            hashSet.add(user);
            System.out.println("user = " + user.hashCode());
        }

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(0L);
            user.setAge(i);
            user.setName("test");
            treeSet.add(user);
            System.out.println("user = " + user.hashCode());
        }

        System.out.println("treeSet = " + treeSet);
    }

    private void add() {
        System.out.println("SetTest.add");
        long start = System.currentTimeMillis();
        for (int i = 0; i < CAPACITY; i++) {
//            hashSet.add(i);
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("hashSet add end: " + end);

        start = System.currentTimeMillis();
        for (int i = 0; i < CAPACITY; i++) {
            linkedHashSet.add(i);
        }
        end = System.currentTimeMillis() - start;
        System.out.println("linkedHashSet add end: " + end);

        start = System.currentTimeMillis();
        for (int i = 0; i < CAPACITY; i++) {
//            treeSet.add(i);
        }
        end = System.currentTimeMillis() - start;
        System.out.println("treeSet add end: " + end);
    }
}
