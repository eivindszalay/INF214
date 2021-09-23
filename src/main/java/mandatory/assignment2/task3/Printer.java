package main.java.mandatory.assignment2.task3;

import main.java.mandatory.assignment2.task2.Sem;

public class Printer {

    Sem sem1 = new Sem(1);
    Sem sem2 = new Sem(0);
    Sem sem3 = new Sem(0);

    private Thread thread1 = new Thread(() -> {
        while (true) {
            try {
                sem1.acquire();
                System.out.println(1);
                System.out.println(2);
                sem1.release();
                sem2.release();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    private Thread thread2 = new Thread(() -> {
        while (true) {
            try {
                sem2.acquire();
                System.out.println(3);
                System.out.println(4);
                sem2.release();
                sem3.release();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    private Thread thread3 = new Thread(() -> {
        while (true) {
            try {
                sem3.acquire();
                System.out.println(5);
                System.out.println(6);
                sem3.release();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    public void print() throws InterruptedException {
        System.out.println(sem1);
        System.out.println(sem2);
        System.out.println(sem3);
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        System.out.println(sem1);
        System.out.println(sem2);
        System.out.println(sem3);
    }
}

