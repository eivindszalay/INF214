package exercises.lab1;

import java.util.concurrent.Callable;

public class Parallel<V,E> {

    private static class Double implements Callable{
        int number;
        public Double(int n) {
            this.number = n;
        }

        public Object call() throws Exception {
            return number*2;
        }
    }

    private static class Triple implements Callable {
        int number;
        public Triple(int n) {
            this.number = n;
        }

        public Object call() throws Exception {
            return number*3;
        }
    }


    public class Tuple<V,E> {
        public V first;
        public E second;
        public Tuple (V first, E second) {
            this.first = first;
            this.second = second;
        }
    }

    /*
    * This function takes in two lambda expressions or method references that returns an int.
    * Returns an array with the two results.
    * */
    public static int[] parallel(Callable clbl1, Callable clbl2) throws InterruptedException {

        int[] tuple = new int[2];

        Thread t1 = new Thread() {
            public void run() {
                try {
                    int dbl = (int) clbl1.call();
                    tuple[0] = (int) clbl1.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                try {
                    tuple[1] = (int) clbl2.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        return tuple;
    }





    public static void main(String[] args) throws InterruptedException {
        int n = 5;
        int m = 5;
        int[] tuple = parallel(new Double(n), new Triple(m));
        System.out.println("Tuple: [" + tuple[0] + "," + tuple[1] + "]");
    }
}
