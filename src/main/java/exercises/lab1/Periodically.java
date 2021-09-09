package exercises.lab1;

import java.util.Scanner;
import java.util.concurrent.Callable;

public class Periodically<V> {

    private static class ExampleFunction implements Callable {
        @Override
        public Object call() throws Exception {
            System.out.println("I am a new thread! You need to kill the JVM to quit this program.");
            return null;
        }
    }

    private static Object periodically(Callable callable, int duration) throws IllegalArgumentException, InterruptedException {

        if (duration <=0 || duration >= 10000) {
            throw new IllegalArgumentException("Bad duration given.") ;
        }

        while (true) {
            Thread thread = new Thread(() -> {
                try {
                    callable.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            thread.start();
            thread.join();

            Thread.sleep(duration);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give sleep duration:");
        int duration = scanner.nextInt();
        Object obj = periodically(new ExampleFunction(), duration);
    }
}
