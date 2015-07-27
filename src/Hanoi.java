import java.util.ArrayList;
import java.util.Stack;

public class Hanoi {

    private static final int NUM_TOWERS = 3;

    private int numSteps = 0;
    private ArrayList<Stack<Integer>> towers = null;

    public Hanoi(int size) {
        towers = new ArrayList<Stack<Integer>>();
        for (int i = 0; i < NUM_TOWERS; i++) {
            towers.add(new Stack<Integer>());
        }
        Stack<Integer> srcTower = towers.get(0);
        for (int i = size; i > 0; i--) {
            srcTower.push(i);
        }
    }

    public void solve() {
        this.numSteps = 0;
        move(0, 2, towers.get(0).size());
        System.out.println("Total steps: " + this.numSteps);
    }

    // Move num elements from src to dst tower
    private void move(int src, int dst, int num) {

        // Base / error case
        if (towers.get(src).size() < num || num == 0) {
            throw new Error();
        }

        int buff = 3 - src - dst; // Determine buffer tower (= 6 - (src+1) - (dst+1) - 1)

        move(src, buff, num - 1); // Move num-1 elements from src to buffer (using dst as buffer)

        this.numSteps++;
        System.out.println(src + "->" + dst);
        towers.get(dst).push(towers.get(src).pop()); // Move largest element from src to dst

        move(buff, dst, num - 1); // Move num-1 elements from buff to dst (using src as buffer)
    }
}
