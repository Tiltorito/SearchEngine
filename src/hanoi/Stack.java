package hanoi;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Created by harry on 15/02/2018.
 */

public class Stack<T> implements Cloneable {
    private ArrayList<T> stack = new ArrayList<>();

    public T pop() {
        return stack.remove(stack.size() - 1);
    }

    public void push(T item) {
        stack.add(item);
    }

    public T peek() {
        return stack.get(stack.size() - 1);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }

    @Override
    public Object clone() {
        Stack<T> obj = new Stack<>();
        obj.stack = (ArrayList<T>) stack.clone();
        return obj;
    }

    @Override
    public boolean equals(Object obj) {
        Stack<T> object = (Stack<T>) obj;

        if(stack.equals(object.stack)) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}
