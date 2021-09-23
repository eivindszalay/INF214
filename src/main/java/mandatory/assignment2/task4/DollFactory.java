package main.java.mandatory.assignment2.task4;//////////////////////////////////////////

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;

class DollFactory {
    List<Doll> dolls;
    private CyclicBarrier stageA, stageB, stageC;

    private void execution(int dollsNumber) throws InterruptedException {
        stageA = new CyclicBarrier(dollsNumber);
        stageB = new CyclicBarrier(dollsNumber);

        // ---------> TODO: stageC = new CyclicBarrier(...); <---------
        //The number of parties waiting for this barrier is 1 more than the other ones
        //since the "main thread" also needs to wait.
        stageC = new CyclicBarrier(dollsNumber+1);
        dolls = new ArrayList<>(dollsNumber);
        for (int i = 0; i < dollsNumber; i++) {
            Process task = new Process(i);

            // ---------> TODO: Your solution goes here <---------
            Thread dollMakingThread = new Thread(task);
            dollMakingThread.start();
        }

        try {
            stageC.await();
            System.out.println("Packaging process D");

            // ---------> TODO: print results <--------
            System.out.printf("%d/%d dolls met the quality requirements.%n", dolls.size(), dollsNumber);
        }
        catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DollFactory dcb = new DollFactory();
        dcb.execution(100); // Let's produce 100 dolls
    }


    // nested class Process
    class Process implements Runnable {
        int id;

        public Process(int id) {
            this.id = id;
        }

        public void run() {

            // ---------> TODO: Your solution goes here <---------
            // Stage A
            Doll doll = this.assembly();
            try {
                stageA.await();
            }
            catch (InterruptedException ex) {
                return;
            }
            catch (BrokenBarrierException ex)  {
                return;
            }

            // Stage B
            this.painting(doll);
            try {
                stageB.await();
            }
            catch (InterruptedException ex) {
                return;
            }
            catch (BrokenBarrierException ex)  {
                return;
            }

            // Stage C
            this.qualityControl(doll);

            try {
                stageC.await();
            }
            catch (InterruptedException ex) {
                return;
            }
            catch (BrokenBarrierException ex)  {
                return;
            }

        }

        void painting(Doll d) {
            d.setPainted();
        }

        Doll assembly() {
            Random r = new Random();
            return new Doll(id, r.nextInt(4) + 7);
        }

        void qualityControl(Doll d) {
            if (d.getQualityScore() >= 9) {
                d.setPerfect();
                dolls.add(d);
            }
        }
    }
}