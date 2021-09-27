package mandatory.assignment2.task2;

public class Sem {

    private int permits;

    public Sem(int permits) {
        this.permits = permits;
    }

    public synchronized void acquire() throws InterruptedException {

        boolean executed = false;

        while (!executed) {
            if (permits > 0){
                permits--;
                executed = true;
            } else {
                this.wait();
            }
        }
    }

    @Override
    public String toString() {
        return "Sem{" +
                "permits=" + permits +
                '}';
    }

    public synchronized void release() {
        permits++;
        this.notify();
    }
}