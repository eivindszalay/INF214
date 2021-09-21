package mandatory.assignment2.task2;

public class Sem {

    private int permits;
    private int accessLimit;


    public Sem(int permits) {
        this.permits = permits;
    }

    public Sem(int permits, int accesses) {
        this.permits = permits;
    }

    public synchronized void acquire() throws InterruptedException {
        if (permits > 0){
            // TODO: Your solution goes here.
            permits--;

        } else {
            // TODO: Your solution goes here.
            throw new InterruptedException("The semaphore was not available.");
            }
    }


    public synchronized void release() {
        // TODO: Your solution goes here.
        permits++;
    }
}

