package ua.com.alevel;

import java.util.*;

public class MapTest {

    private Map<String, Integer> hashMap = new HashMap<>();
    private Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
    private Map<String, Integer> treeMap = new TreeMap<>();

    public void test() {
        for (int i = 0; i < 10; i++) {
            String id = UUID.randomUUID().toString();
            hashMap.put(id, i);
        }

        hashMap.forEach((k, v) -> {
            System.out.println("k = " + k);
            System.out.println("v = " + v);
        });

        for (int i = 0; i < 10; i++) {
            String id = UUID.randomUUID().toString();
            linkedHashMap.put(id, i);
        }

        System.out.println();

        linkedHashMap.forEach((k, v) -> {
            System.out.println("k = " + k);
            System.out.println("v = " + v);
        });

        System.out.println();

        treeMap.put("10", 10);

        for (int i = 0; i < 10; i++) {
            treeMap.put(String.valueOf(i), i);
        }

        treeMap.forEach((k, v) -> {
            System.out.println("k = " + k);
            System.out.println("v = " + v);
        });
    }
}
