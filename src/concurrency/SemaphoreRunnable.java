package concurrency;

public class SemaphoreRunnable implements Runnable {

    private static int MAX_RUNS = 5;
    private Semaphore semaphore;
    private int runs = 0;
    private int id;

    public SemaphoreRunnable(Semaphore semaphore, int id) {
        this.semaphore = semaphore;
        this.id = id;
    }

    public void run() {
        try {
            while (this.runs < SemaphoreRunnable.MAX_RUNS) {
                try {
                semaphore.acquire();
                semaphore.doStuff(this.id, this.runs);
                this.runs++;
                } finally {
                    semaphore.release();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
