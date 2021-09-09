package vids;

public class Vid1 {
    public static void main(String[] args) {
        Thread myThread = new Thread(() -> System.out.println("Hello from the new thread"));
        myThread.start();
        Thread.yield();
        System.out.println("Hello from outside the thread");
    }

}