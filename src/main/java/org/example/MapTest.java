package org.example;

import org.example.implementations.MyHashMap;

import java.util.Map;

public class MapTest {

    public static final String DELIMITER = "#".repeat(30);

    public static void main(String[] args) {
        testMap(new MyHashMap<>(10));
    }

    public static void testMap(MyHashMap<String, Integer> map) {
        printData(map);
        System.out.println("Adding elements");
        for (int i = 0; i < 10; i++) map.put(String.valueOf(i), i);
        printData(map);

        System.out.println("Adding more (array should enlarge)");
        for (int i = 10; i < 21; i++) map.put(String.valueOf(i), i);
        printData(map);

        System.out.println("Replacing elements");
        for (int i = 0; i < 21; i++) map.put(String.valueOf(i), i + 1);
        printData(map);

        System.out.println("Removing elements");
        for (int i = 0; i < 11; i++) {
            System.out.print(i + ", ");
            map.remove(String.valueOf(i));
        }
        System.out.println();
        printData(map);

        System.out.println("Getting elements");
        for (int i = 10; i <= 21; i++) {
            System.out.print(i + "=" + map.get(String.valueOf(i)) + "\t");
        }
        System.out.println();
        printData(map);

        System.out.println("Decreasing capacity");
        map.shrink();
        printData(map);

        System.out.println("Clear");
        map.clear();
        printData(map);

        System.out.println("Single key");
        for (int i = 0; i < 1000; i++) {
            map.put("a", i);
        }
        printData(map);

        System.out.println("Null key");
        map.put(null, 3);
        printData(map);

        System.out.println("Many elements");
        for (int i = 0; i < 10000; i++) {
            map.put(String.valueOf(i), i);
        }
        printSize(map);
    }

    private static void printSize(Map<?, ?> map) {
        System.out.println("Size: " + map.size());
    }

    private static void printData(MyHashMap<?, ?> map) {
        printSize(map);
        System.out.println("Nodes: " + map);
        System.out.println("Array: " + map.toArrayString());
        System.out.println(DELIMITER);
    }
}
