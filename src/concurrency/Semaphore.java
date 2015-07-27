package concurrency;

public class Semaphore {

    private static int MAX_CONCURRENT_USERS = 3;

    private int available;

    public Semaphore() {
        this.available = MAX_CONCURRENT_USERS;
    }

    public synchronized void acquire() throws InterruptedException {
        // While no resources are available, wait before acquiring
        while (available == 0) {
            this.wait();
        }
        available--;
        System.out.println("Available: " + available);
        this.notify();
    }

    public void doStuff(int id, int runs) throws InterruptedException {
        System.out.println("Doing stuff: ID=" + id + " : RUNS=" + runs);
        Thread.sleep(1000);
    }

    public synchronized void release() throws InterruptedException {
        // While max available users is hit, wait before allowing more
        // When using semaphore as a lock, typically this should never block
        // as every release() should have a corresponding acquire()
        while (available == MAX_CONCURRENT_USERS) {
            this.wait();
        }
        available++;
        this.notify();
    }
}
