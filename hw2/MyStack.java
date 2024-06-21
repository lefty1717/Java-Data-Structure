package hw2;

public class MyStack {
    private static int top = -1;
    private static final int MAX_SIZE = 100;
    public static String[] stackArray = new String[MAX_SIZE];

    public static void push(String value) {
        if (!isFull()) {
            stackArray[++top] = value;
        }
        else {
            System.out.println("Stack is full. Cannot push: " + value);
        }
    }

    public static void pop() {
        if (!isEmpty()) {
            top--;
        } 
        else {
            System.out.println("Stack is empty. Cannot pop.");
        }
    }

    public static void peek(String[] args) {
        if ( !isEmpty() ) {
            System.out.println("Top element is: " + stackArray[top]);
        }
        else {
            System.out.println("Stack is empty. Cannot peek.");
        }
    }

    public static boolean isEmpty() {
        return (top == -1);
    }

    public static boolean isFull() {
        return (top == MAX_SIZE - 1);
    }
}
