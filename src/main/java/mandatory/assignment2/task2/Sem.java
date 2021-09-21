package mandatory.assignment2.task2;

public class Sem {

    private int permits;

    public Sem(int permits) {
        this.permits = permits;
    }

    public synchronized void acquire() throws InterruptedException {
        if (permits > 0){
            // TODO: Your solution goes here.
        } else {
            // TODO: Your solution goes here.
        }
    }

    public synchronized void release() {
        // TODO: Your solution goes here.
    }
}

