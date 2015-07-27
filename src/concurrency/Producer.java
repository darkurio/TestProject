package concurrency;

public class Producer implements Runnable {

    private int id;
    private int maxProducts;
    private int currProducts;
    private SharedResource resource;

    public Producer(int id, int maxProducts, SharedResource resource) {
        this.id = id;
        this.maxProducts = maxProducts;
        this.currProducts = 0;
        this.resource = resource;
    }

    @Override
    public void run() {
        while (currProducts < maxProducts) {
            try {
                resource.push(currProducts);
                System.out.println(this.id + " pushed: " + currProducts);
                currProducts++;
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
