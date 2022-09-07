package org.example;

import org.example.implementations.MyArrayList;
import org.example.implementations.MyLinkedList;
import org.example.implementations.MyLinkedQueue;
import org.example.implementations.MyLinkedStack;
import org.example.interfaces.Stack;

import java.util.*;

public class CollectionTest {

    //Printing of collections is based on overridden toString()
    public static void main(String[] args) {

        testList(new MyArrayList<>());
        System.out.println("#".repeat(60));

        testList(new MyLinkedList<>());
        System.out.println("#".repeat(60));

        testQueue(new MyLinkedQueue<>());
        System.out.println("#".repeat(60));

        testStack(new MyLinkedStack<>());
    }


    private static void testList(List<Integer> integers) {
        System.out.println("List: " + integers.getClass());
        printSize(integers);

        populateList(1, 10, integers);
        printSize(integers);
        System.out.println(integers);

        List<Integer> list = new ArrayList<>();
        for (int i = 8; i >= 0; i -= 2) list.add(i);

        System.out.println("Remove by index:");
        for (Integer index : list) {
            integers.remove(index);
            System.out.println(integers);
        }

        System.out.println("Get by index:");
        StringJoiner stringJoiner = new StringJoiner("; ", "{", "}");
        for (int i = integers.size() - 1; i >= 0; i--) {
            stringJoiner.add(integers.get(i).toString());
        }
        System.out.println(stringJoiner);

        clear(integers);

        Integer integer = 100;
        populateList(0, 7, integers);
        integers.add(integer);
        populateList(8, 16, integers);
        System.out.println(integers);
        System.out.println("Removing object: " + integer);
        integers.remove(integer);
        System.out.println(integers);
    }


    private static void testQueue(Queue<Integer> queue) {
        System.out.println("Queue: " + queue.getClass());
        printSize(queue);
        printPeek(queue.peek());
        populateList(1, 30, queue);
        System.out.println(queue);
        printPeek(queue.peek());
        printSize(queue);
        System.out.println("Removing (poll):\n ");
        for (int i = 1; i < 6; i++) {
            System.out.print(queue.poll() + ", ");
        }
        System.out.println();
        System.out.println(queue);
        printPeek(queue.peek());
        System.out.print("Add:\n ");
        populateList(100, 105, queue);
        System.out.println("\n" + queue);
        printPeek(queue.peek());
        clear(queue);
    }


    private static void testStack(Stack<Integer> stack) {
        System.out.println("Stack: " + stack.getClass());
        printSize(stack);
        printPeek(stack.peek());
        populateStack(1, 30, stack);
        System.out.println(stack);
        printPeek(stack.peek());
        printSize(stack);
        System.out.print("Removing (pop):\n ");
        for (int i = 1; i < 6; i++) {
            System.out.print(stack.pop() + ", ");
        }
        System.out.println();
        System.out.println(stack);
        printPeek(stack.peek());
        populateStack(100, 105, stack);
        System.out.println(stack);
        printPeek(stack.peek());
        clear(stack);
    }

    private static void clear(Collection<Integer> integers) {
        System.out.println("Clearing");
        integers.clear();
        System.out.println(integers);
    }

    private static void populateList(int from, int to, Collection<Integer> collection) {
        System.out.println("Adding: ");
        for (int i = from; i < to; i++) {
            collection.add(i);
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    private static void populateStack(int from, int to, Stack<Integer> stack) {
        System.out.println("Adding: ");
        for (int i = from; i < to; i++) {
            System.out.print(i + ", ");
            stack.push(i);
        }
        System.out.println();
    }

    private static <T> void printPeek(T item) {
        System.out.println("Peek: " + item);
    }

    private static void printSize(Collection<Integer> collection) {
        System.out.println("Size: " + collection.size());
    }


}
