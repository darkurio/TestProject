package concurrency;

import java.util.ArrayList;

public class SharedResource {

    private static int MAX_SIZE = 10;
    private ArrayList<Integer> resource;

    public SharedResource() {
        resource = new ArrayList<Integer>();
    }

    public synchronized int pull() throws InterruptedException {
        while (resource.size() == 0) {
            wait();
        }
        int s = resource.get(resource.size() - 1);
        resource.remove(resource.size() - 1);
        notify();
        return s;
    }

    public synchronized void push(int s) throws InterruptedException {
        while (resource.size() == MAX_SIZE) {
            wait();
        }
        resource.add(s);
        notify();
    }
}
