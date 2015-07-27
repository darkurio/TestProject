package concurrency;

public class Consumer implements Runnable {

    private int id;
    private SharedResource resource;

    public Consumer(int id, SharedResource resource) {
        this.id = id;
        this.resource = resource;
    }

    public void run() {
        while (true) {
            try {
                int s = resource.pull();
                System.out.println(this.id + " pulled: " + s);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
