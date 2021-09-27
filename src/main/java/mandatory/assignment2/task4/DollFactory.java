package mandatory.assignment2.task4;

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

        //The number of parties waiting for this barrier is 1 more than the other ones
        //since the "main thread" also needs to wait.
        stageC = new CyclicBarrier(dollsNumber+1);
        dolls = new ArrayList<>(dollsNumber);
        for (int i = 0; i < dollsNumber; i++) {
            Process task = new Process(i);

            Thread dollMakingThread = new Thread(task);
            dollMakingThread.start();
        }

        try {
            stageC.await(); // the main process waits for all the doll building processes to finish execution.
            System.out.println("Packaging process D");
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

            // Stage A
            Doll doll = this.assembly();
            try {
                stageA.await(); // every process signals to the others that it has completed stage A and waits for all other processes to complete.
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
                stageB.await(); // every process signals to the others that it has completed stage B and waits for all other processes to complete.
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
                stageC.await(); // every process signals to the main process that it has completed stage C.
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