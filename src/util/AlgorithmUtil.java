package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

public class AlgorithmUtil {
    // Given a sorted, but possibly rotated, array, determine whether a value is
    // within a given range
    private static boolean isInRange(int arr[], int start, int end, int value) {
        if (start > end) {
            return false;
        }
        /*
         * Case 1: Start and end without hitting an inflection point Value needs to be in [start,
         * end] to return true Case 2: Start to end contains inflection point Both start and end
         * value must be <= value Case 3: Start to end contains inflection point Both start and end
         * value must be >= value
         */
        if ((arr[start] <= value && arr[end] >= value && arr[start] <= arr[end])
                || (arr[start] <= value && arr[end] <= value && arr[start] >= arr[end])
                || (arr[start] >= value && arr[end] >= value && arr[start] >= arr[end])) {
            return true;
        }
        return false;
    }

    // Find index of element in a sorted, but possibly rotated, array
    // Ex. [1, 2, 3, 4, 5] => [4, 5, 1, 2, 3]
    public static int find(int arr[], int start, int end, int value) {
        if (start > end) {
            return -1;
        }

        int mid = (start + end) / 2;

        if (arr[mid] == value) {
            return mid;
        }

        if (isInRange(arr, start, mid - 1, value)) {
            return find(arr, start, mid - 1, value);
        } else if (isInRange(arr, mid + 1, end, value)) {
            return find(arr, mid + 1, end, value);
        }

        return -1;
    }

    public static int factorial(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    // Count number of paths from point (y, x) in a rectangle of size (xSize, ySize)
    // given path can only go right or down. Uses binomial formula nCr
    public static int countPathsBinomial(int y, int x, int xSize, int ySize) {
        int horz = xSize - x;
        int vert = ySize - y;
        return factorial(horz + vert) / (factorial(horz) * factorial(vert));
    }

    // Count number of paths from point (y, x) in a rectangle of size (xSize, ySize)
    // given path can only go right or down.
    // Use dynamic programming: cache result of a given path starting at (y, x)
    // and check before recursing it.
    public static int countPaths(int y, int x, int xSize, int ySize, int[][] cache) {
        if (y > ySize || x > xSize) {
            return 0;
        }
        if (cache[y][x] > -1) {
            return cache[y][x];
        }
        if (x == xSize - 1 && y == ySize || y == ySize - 1 && x == xSize) {
            return 1;
        }
        cache[y][x] = countPaths(y + 1, x, xSize, ySize, cache) + countPaths(y, x + 1, xSize, ySize, cache);
        return cache[y][x];
    }

    private int seed = 0;

    private class Node {
        public int id;
        public ArrayList<Node> adj;

        public Node() {
            this.id = ++seed;
        }
    }

    public static void printShortestPath(Node a, Node b) {

        // Maps from node to prev node
        HashMap<Node, Node> path = new HashMap<Node, Node>();

        boolean result = findShortestPath(a, b, path);
        if (!result) {
            System.out.println("No path exists");
        }

        // Construct stack by putting together path in reverse order
        Stack<Node> stack = new Stack<Node>();
        stack.push(b);
        Node curr = b;
        while (path.containsKey(curr)) {
            curr = path.get(curr);
            stack.push(curr);
        }

        // Pop off stack to get forward order of path
        while (!stack.isEmpty()) {
            System.out.println(stack.pop().id);
        }
    }

    public static boolean findShortestPath(Node a, Node b, HashMap<Node, Node> path) {

        Node prev = null;
        if (a == b) {
            prev = a;
        }

        // Starting node has no prev (unless start equals end)
        path.put(a, prev);

        LinkedList<Node> q = new LinkedList<Node>();
        q.add(a);

        while (!q.isEmpty()) {
            Node r = q.removeFirst();
            for (Node c : r.adj) {
                // Use path datastructure to track whether nodes are visited
                if (!path.containsKey(c)) {
                    path.put(c, r);
                }
            }
        }

        if (!path.containsKey(b)) {
            return false;
        }

        return true;
    }

    public static class PathNode {
        public int id;
        public PathNode right;
        public PathNode down;
        public boolean blocked;

        public PathNode(int id, PathNode right, PathNode down, boolean blocked) {
            this.id = id;
            this.right = right;
            this.down = down;
            this.blocked = blocked;
        }

        public PathNode(int id, PathNode right, PathNode down) {
            this(id, right, down, false);
        }
    }

    // Count number of paths from root node to target node assuming at every node, path can only
    // go right or down.
    public static int countPaths(PathNode root, PathNode target) {
        if (root == null || root.blocked) {
            return 0;
        }
        if (root.id == target.id) {
            return 1;
        }
        return AlgorithmUtil.countPaths(root.right, target) + AlgorithmUtil.countPaths(root.down, target);
    }

    public static void rotateMatrix(String matrix[][], int size) {

        // Loop through first half of rows (if odd, ignore middle row - this
        // will be the very middle 1 element in matrix)
        for (int y = 0; y < size / 2; y++) {

            // Loop through elements in given layer (start at layer number and
            // go until size - layer number - 1
            // The -1 is because we ignore the last element in the row as it is
            // also the first element in the next part of the layer)
            for (int x = y; x < size - 1 - y; x++) {

                String temp = matrix[y][x]; // temp <- top

                // System.out.println(matrix[y][x] + " <- " +
                // matrix[size-1-x][y]); // top <- left
                matrix[y][x] = matrix[size - 1 - x][y];

                // System.out.println(matrix[size-1-x][y] + " <- " +
                // matrix[size-1-y][size-1-x]); // left <- bottom
                matrix[size - 1 - x][y] = matrix[size - 1 - y][size - 1 - x];

                // System.out.println(matrix[size-1-y][size-1-x] + " <- " +
                // matrix[x][size-1-y]); // bottom <- right
                matrix[size - 1 - y][size - 1 - x] = matrix[x][size - 1 - y];

                // System.out.println(matrix[x][size-1-y] + " <- " + temp); //
                // right <- top
                matrix[x][size - 1 - y] = temp;

                // System.out.println();
            }
        }
    }

    public static String[][] generateMatrix(int size) {
        String[][] matrix = new String[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                matrix[y][x] = x + "," + y;
            }
        }
        return matrix;
    }

    public static char[][] getPermutations(char[] a) {

        // Base Case: length is 0
        int permutationLength = a.length;
        if (permutationLength == 0) {
            return new char[0][0];
        }

        int numPermutations = AlgorithmUtil.factorial(permutationLength);
        char[][] results = new char[numPermutations][permutationLength];

        // Base Case: length is 1
        if (permutationLength == 1) {
            results[0] = a;
        }

        // Recursive Case
        for (int i = 0; i < permutationLength; i++) {
            // Construct sub-input which is an array containing elements other
            // than the current element in input array
            char[] subInput = new char[permutationLength - 1];
            for (int j = 0, k = 0; j < permutationLength; j++) {
                if (j != i) {
                    subInput[k] = a[j];
                    k++;
                }
            }

            // Get permutations of sub-input
            char[][] subResults = getPermutations(subInput);

            // Loop through all sub results and add into results with first
            // element being the current element in input array
            int numSubResults = subResults.length;
            for (int m = 0; m < numSubResults; m++) {
                results[i * numSubResults + m][0] = a[i];
                // for (int o = 0, p = 1; o < subResults[m].length; o++, p++) {
                // results[i * subResults.length + m][p] = subResults[m][o];
                // }
                System.arraycopy(subResults[m], 0, results[i * numSubResults + m], 1, subResults[m].length);
            }
        }

        return results;
    }

    public static class PermData {
        public int count;
        HashMap<String, ArrayList<String>> cache;

        public PermData() {
            this.count = 0;
            this.cache = new HashMap<String, ArrayList<String>>();
        }
    }

    public static ArrayList<String> getPermutations(String input, PermData data) {

        data.count++;
        System.out.println("Looking at: " + input);
        if (data.cache.containsKey(input)) {
            System.out.println("Cached");
            return data.cache.get(input);
        }

        // Base Cases
        ArrayList<String> results = new ArrayList<String>(AlgorithmUtil.factorial(input.length()));
        // if (input.length() == 0) { Not actually necessary
        // return results;
        // }
        if (input.length() == 1) {
            results.add(input);
            return results;
        }

        // Recursive Case
        // Loop through each character and append with rest of characters in input string
        for (int i = 0; i < input.length(); i++) {

            char prefix = input.charAt(i);
            String subset = input.substring(0, i)
                    + ((i + 1 >= input.length()) ? "" : input.substring(i + 1, input.length()));

            // Recurse over subset
            ArrayList<String> subsetResults = AlgorithmUtil.getPermutations(subset, data);

            // For each result in subset, append with current character from input string
            Iterator<String> itr = subsetResults.iterator();
            while (itr.hasNext()) {
                results.add(prefix + itr.next());
            }
        }

        data.cache.put(input, results);
        return results;
    }

    public static int lcm(int a, int b) {
        if (a <= 0 || b <= 0) {
            return -1;
        }
        int n1 = a, n2 = b;
        while (n1 != n2) {
            if (n1 < n2) {
                n1 += a;
            }
            if (n2 < n1) {
                n2 += b;
            }
        }
        return n1;
    }

    public static int convertBase(int n, int bSrc, int bDst) {
        int result = 0;

        for (int i = 0; n > 0; i++) {
            if (((n - Math.pow(bDst, i)) % Math.pow(bDst, i + 1)) == 0) {
                n -= Math.pow(bDst, i);
                result += Math.pow(bSrc, i);
            }
        }

        return result;
    }

    public static class GridNode<T> {
        private GridNode<T> down = null;
        private GridNode<T> right = null;
        private T data = null;
        private int cost = 0;
        public boolean visited = false;

        public GridNode(T data, GridNode<T> down, GridNode<T> right) {
            this.data = data;
            this.down = down;
            this.right = right;
        }
    }

    public static <T> GridNode<T> convertToNodeMatrix(T[][] origMatrix, int yStart, int xStart) {

        if (origMatrix == null || origMatrix.length == 0 || origMatrix[0].length == 0 || origMatrix.length <= yStart
                || origMatrix[0].length <= xStart || origMatrix[yStart][xStart] == null) {
            return null;
        }

        GridNode<T> headDown = convertToNodeMatrix(origMatrix, yStart + 1, xStart);
        GridNode<T> headRight = convertToNodeMatrix(origMatrix, yStart, xStart + 1);
        return new GridNode<T>(origMatrix[yStart][xStart], headDown, headRight);
    }

    public static <T> int countPathToTarget(GridNode<T> head, T target) {

        GridNode<T> curr = head;
        LinkedList<GridNode<T>> q = new LinkedList<GridNode<T>>();

        while (curr != null) {
            if (curr.data.equals(target)) {
                return curr.cost;
            }
            if (curr.down != null) {
                curr.down.cost = curr.cost + 1;
                q.add(curr.down);
            }
            if (curr.right != null) {
                curr.right.cost = curr.cost + 1;
                q.add(curr.right);
            }
            if (q.size() == 0) {
                return -1;
            } else {
                curr = q.remove();
            }
        }

        return -1;
    }

    // Bottom-up DP to determine how many coins are needed to make a given sum
    // Return array of ints specifying min # coins to create sum where sum = array index
    public static int[] minCoins(int sum, int[] coins) {

        if (sum == 0 || coins.length == 0) {
            return null;
        }

        int[] mins = new int[sum + 1];
        mins[0] = 0;
        for (int i = 1; i <= sum; i++) {
            mins[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= sum; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i && mins[i - coins[j]] + 1 < mins[i]) {
                    mins[i] = mins[i - coins[j]] + 1;
                }
            }
        }

        return mins;
    }

    // Return matrix of ints specifying coins needed to create set of min coins needed
    // to create sum where sum = array index
    public static int[][] minCoins2(int sum, int[] coins) {

        if (sum == 0 || coins.length == 0) {
            return null;
        }

        int[][] mins = new int[sum + 1][0];
        mins[0] = new int[0];

        for (int i = 1; i <= sum; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i && (mins[i].length == 0 || (mins[i - coins[j]].length + 1) < mins[i].length)) {
                    mins[i] = new int[mins[i - coins[j]].length + 1];
                    System.arraycopy(mins[i - coins[j]], 0, mins[i], 0, mins[i - coins[j]].length);
                    mins[i][mins[i - coins[j]].length] = coins[j];
                }
            }
        }

        return mins;
    }

    // Top-down DP with memoization to calculate min coins needed to make a given sum
    public static int minCoins3(int sum, int[] coins, int[] cache) {
        if (sum == 0) {
            return 0;
        }
        if (cache[sum] > 0) {
            return cache[sum];
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            if (sum - coins[i] >= 0) {
                int result = minCoins3(sum - coins[i], coins, cache) + 1;
                if (result < min) {
                    min = result;
                }
            }
        }
        cache[sum] = min;
        return min;
    }

    // P(At least 2 ppl share same BD) = 1 - P(All diff BD)
    // P(All diff BD) = prod(1 to n) of (365 - i)/365
    //
    // P(At least 2 ppl share same BD) >= p
    // 1 - P(All diff BD) >= p
    // P(All diff BD) <= 1 - p
    public static int numPplForSameBD(double p) {

        double targetPAllDiffBD = 1 - p;
        double currPAllDiffBD = 1;
        int i = 0;
        for (; currPAllDiffBD > targetPAllDiffBD; i++) {
            currPAllDiffBD *= ((365 - i) / (double) 365);
        }

        return i;
    }

    public static class AVLNode {

        private int key = 0;
        private int height = 0;
        private AVLNode left = null;
        private AVLNode right = null;
        private AVLNode parent = null;

        public AVLNode(int key) {
            this.key = key;
        }
    }

    public static void rotateLeft(AVLNode origRoot) {

        // === Difference from rotateRight ===
        AVLNode newRoot = origRoot.right;
        // Original root's right (previously new root) becomes new root's left
        origRoot.right = newRoot.left;
        // New root's left becomes original root
        newRoot.left = origRoot;

        // If original root is not root of tree
        if (origRoot.parent != null) {
            // Update new root's parent's link to it based on
            // whether original root is left or right child
            if (origRoot.parent.left.key == origRoot.key) {
                origRoot.parent.left = newRoot;
            } else {
                origRoot.parent.right = newRoot;
            }
        }
        // Update new root's parent to be original root's parent
        newRoot.parent = origRoot.parent;
        // Update original root's parent to be new root
        origRoot.parent = newRoot;

        // Update heights of original and new root
        newRoot.height -= 1;
        origRoot.height += 1;
    }

    public static void rotateRight(AVLNode origRoot) {

        // === Difference from rotateLeft ===
        AVLNode newRoot = origRoot.left;
        // Original root's left (previously new root) becomes new root's right
        origRoot.left = newRoot.right;
        // New root's right becomes original root
        newRoot.right = origRoot;

        // If original root is not root of tree
        if (origRoot.parent != null) {
            // Update new root's parent's link to it based on
            // whether original root is left or right child
            if (origRoot.parent.left.key == origRoot.key) {
                origRoot.parent.left = newRoot;
            } else {
                origRoot.parent.right = newRoot;
            }
        }
        // Update new root's parent to be original root's parent
        newRoot.parent = origRoot.parent;
        // Update original root's parent to be new root
        origRoot.parent = newRoot;

        // Update heights of original and new root
        newRoot.height -= 1;
        origRoot.height += 1;
    }

    public static class BFVertex {
        public int id = 0;
        public BFVertex prev = null;
        public int cost = Integer.MAX_VALUE;

        public BFVertex(int id) {
            this.id = id;
        }
    }

    public static class BFEdge {
        public BFVertex src = null;
        public BFVertex dst = null;
        public int cost = 0;

        public BFEdge(BFVertex src, BFVertex dst, int cost) {
            this.src = src;
            this.dst = dst;
            this.cost = cost;
        }
    }

    // O(VE)
    // Note: On dense graph, |E| can be |V|^2 (on sparse graph, we |E| approx >= |V|)
    // So can potentially be O(V^3)
    public static final boolean bellmanFord(BFVertex[] vertices, BFEdge[] edges, BFVertex src) {

        // Initialize cost of all vertices to MAX and prev to null (but already done intrinsically)
        // Initialize cost of starting vertex to 0
        src.cost = 0;

        // |V| - 1 passes: a simple path between k vertices has at most k - 1 edges
        // So require k - 1 passes to be sure costs converge to minimum
        for (int i = 1; i < vertices.length - 1; i++) {

            boolean hasUpdate = false;

            // Relax every edge, updating dst cost and prev as needed
            for (int j = 0; j < edges.length; j++) {
                if (edges[j].src.cost + edges[j].cost < edges[j].dst.cost) {
                    edges[j].dst.cost = edges[j].src.cost + edges[j].cost;
                    edges[j].dst.prev = edges[j].src;
                    hasUpdate = true;
                }
            }

            // If no vertex cost was updated, all edges have already converged to their min
            // and we can short-circuit out of function
            if (!hasUpdate) {
                return true;
            }
        }

        // Loop through every edge one last time to check for negative cycles. Each vertex should
        // already have its min cost by now so if any goes lower, we have a neg cycle
        for (int j = 0; j < edges.length; j++) {
            if (edges[j].src.cost + edges[j].cost < edges[j].dst.cost) {
                return false;
            }
        }

        return true;
    }

    public static class DijkVertexComparator implements Comparator<DijkVertex> {

        @Override
        public int compare(DijkVertex o1, DijkVertex o2) {
            int res = o1.cost - o2.cost;
            if (res > 0) {
                return 1;
            } else if (res == 0) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    public static final class DijkVertex {
        public int id = 0;
        public DijkVertex prev = null;
        public DijkEdge[] edges = null;
        public int cost = Integer.MAX_VALUE;
    }

    public static final class DijkEdge {
        public DijkVertex src = null;
        public DijkVertex dst = null;
        public int cost = 0;
    }

    // O(V) + O(V * V + E) = O(V^2 + E) = O(V^2) if queue is un-ordered linked list
    // O(V * logV) + O(V * logV + E * logV) = O((V + E)logV)
    public static final void dijkstra(DijkVertex[] vertices, DijkVertex src) {

        // Initialize vertices prev and cost (intrinsically done already except for source)
        src.cost = 0;

        // Setup priority queue maintaining min cost vertices at the start of queue
        PriorityQueue<DijkVertex> q = new PriorityQueue<DijkVertex>(vertices.length, new DijkVertexComparator());
        q.addAll(Arrays.asList(vertices));

        // Go through queue removing min vertex each iteration
        while (!q.isEmpty()) {
            DijkVertex v = q.remove();

            // If cost of next node is MAX, it is not connected to source
            // Due to priority queue, all nodes after next node are also not connected to source
            // so can just return
            if (v.cost == Integer.MAX_VALUE) {
                return;
            }

            // For each edge leaving vertex, relax
            for (int i = 0; i < v.edges.length; i++) {
                if (v.cost + v.edges[i].cost < v.edges[i].dst.cost) {
                    v.edges[i].dst.cost = v.cost + v.edges[i].cost;
                    v.edges[i].dst.prev = v;

                    // Remove and add updated vertex to queue to place it in correct location
                    // after being updated
                    q.remove(v.edges[i].dst);
                    q.add(v.edges[i].dst);
                }
            }
        }
    }

    public static class FWVertex {
        public int id = 0;
    }

    public static class FWEdge {
        public int src = 0;
        public int dst = 0;
        public int cost = 0;
    }

    // O(V^3) (better than BF or Dijk being run on every vertex as this is a tight bound)
    public static final void floydWarshall(FWVertex[] vertices, FWEdge[] edges, int[][] dist, int[][] next) {

        // Setup output (matrix of distances and next pointers)
        dist = new int[vertices.length][vertices.length];
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist[0].length; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        next = new int[vertices.length][vertices.length];
        for (int i = 0; i < next.length; i++) {
            for (int j = 0; j < next[0].length; j++) {
                next[i][j] = -1;
            }
        }

        // Initialize distances from node to itself to 0
        for (int i = 0; i < vertices.length; i++) {
            dist[i][i] = 0;
        }
        // Initialize direct edge distances from vertex i to j = w(i, j)
        for (int i = 0; i < edges.length; i++) {
            dist[edges[i].src][edges[i].dst] = edges[i].cost;
        }

        // For all vertices k, for all pairs of vertices (i, j), see if distance from i to k
        // plus k to j < distance from i to j. If so, update it and the next pointer from i to k.
        for (int k = 0; k < vertices.length; k++) {
            for (int i = 0; i < vertices.length; i++) {
                for (int j = 0; j < vertices.length; k++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = k;
                    }
                }
            }
        }
    }

    public static final String printPathFW(int src, int dst, int[][] dist, int[][] next) {
        // No path exists between src and dst
        if (dist[src][dst] == Integer.MAX_VALUE) {
            return "No path exists between vertex " + src + " and " + dst;
        }
        return src + " " + printIntermediatePathFW(src, dst, next) + " " + dst;
    }

    private static final String printIntermediatePathFW(int src, int dst, int[][] next) {
        // Direct path exists between src to dst
        if (next[src][dst] == -1) {
            return "";
        } else {
            // Indirect path exists between src and dst
            return printIntermediatePathFW(src, next[src][dst], next) + " " + next[src][dst] + " "
                    + printIntermediatePathFW(next[src][dst], dst, next);
        }
    }

    private static final int heapLeft(int i) {
        return i << 2; // 2 * i
    }

    private static final int heapRight(int i) {
        return i << 2 + 1; // 2 * i + 1
    }

    private static final int heapParent(int i) {
        return i >> 2; // floor(i/2)
    }

    public static final void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static final void maxHeapify(int[] arr, int heapSize, int i) {
        int l = heapLeft(i);
        int r = heapRight(i);

        // Get index of largest index of: arr[i], left child of arr[i], right child of arr[i]
        int largest = i;
        if (l <= heapSize && arr[l] > arr[largest]) {
            largest = l;
        }
        if (r <= heapSize && arr[r] > arr[largest]) {
            largest = r;
        }
        // If largest index is not i, then swap value of largest of the three with arr[i]
        // and recurse over original location of value being swapped (heap property may be invalid
        // for swapped location - ie. arr[largest] - even though arr[i] now respects heap property)
        if (largest != i) {
            swap(arr, largest, i);
            maxHeapify(arr, heapSize, largest);
        }
    }

    // For some reason this works: don't need to recurse - probably due to bottom-up property
    // of optimal solutions
    public static final int getMaxRevenueTopDownNonRecursive(int[] prices, int n) {
        if (n == 0) {
            return 0;
        }
        int maxPrice = Integer.MIN_VALUE;
        for (int i = 0; i <= n; i++) {
            System.out.println("currMax=" + maxPrice + " prices[" + i + "]=" + prices[i] + " prices[" + (n - i) + "]="
                    + prices[n - i]);
            maxPrice = Math.max(maxPrice, prices[i] + prices[n - i]);
        }
        return maxPrice;
    }

    // Running time is O(n^2). Loop from i = 1 to n, and recurse each time. Each recurse will
    // recurse i times (recurse over i is linear due to memoization as max revenue of each i is
    // solved only once). Total = n(n+1)/2.
    // In the NON-memoized case, each recurse loops from j = 1 to i, and each loop itself recurses
    // further. Result is exponential in n: O(2^n). Considers all combinations of cuts: 2^(n-1).
    public static final int getMaxRevenueTopDown(int[] prices, int[] cache, int[] prev, int n) {
        if (n == 0) {
            return 0;
        }
        if (cache[n] != Integer.MIN_VALUE) {
            return cache[n];
        }
        int maxPrice = Integer.MIN_VALUE;
        // Loop through all combinations of cuts by cutting into pieces: length i and all
        // combinations of cuts of length n-i.
        // Get max price over all combinations.
        for (int i = 1; i <= n; i++) {
            int candidateMaxPrice = prices[i] + getMaxRevenueTopDown(prices, cache, prev, n - i);
            if (candidateMaxPrice > maxPrice) {
                maxPrice = candidateMaxPrice;
                // prev[n] stores the optimal first cut for length n
                prev[n] = i;
            }
        }
        cache[n] = maxPrice;
        return maxPrice;
    }

    // O(n^2). Loop from i = 1 to n, and for each i, loop over 1 to i. Sum = n(n+1)/2.
    public static final int getMaxRevenueBottomUp(int[] prices, int[] cache, int[] prev, int n) {
        if (n == 0) {
            return 0;
        }
        // Set revenue of length of 0 to 0 as this is necessary to satisfy bottom-up property
        cache[0] = 0;
        prev[0] = 0;

        // For all lengths j starting from 1 up to n
        for (int j = 1; j <= n; j++) {
            // Loop through all combinations of cuts where first cut is at length i and subsequent
            // cuts are all combinations of cuts of length j-i.
            // Bottom-up property: that cache[i] is set before attempting to resolve max revenue of
            // n for all i < n.
            int maxPrice = Integer.MIN_VALUE;
            for (int i = 1; i <= j; i++) {
                // Because of bottom-up property, can directly reference cache knowing it is set
                // instead of recursing
                int candidateMaxPrice = prices[i] + cache[j - i];
                if (candidateMaxPrice > maxPrice) {
                    maxPrice = candidateMaxPrice;
                    // prev[n] stores the optimal first cut for length n
                    prev[n] = i;
                }
            }
            cache[j] = maxPrice;
        }

        // Return max revenue for length n. Other lengths also are set in cache as solving n
        // required solving all i < n.
        return cache[n];
    }

    public static final void incrementBinaryCounter(int[] counter) {
        // Find left-most bit that is not a 1 and set all bits to the right of such a bit to 0
        int i = 0;
        while (i < counter.length && counter[i] == 1) {
            counter[i++] = 0;
        }
        // Eventually, will reach bit that is a 0 (set it to 1),
        // or overflow out of binary counter (do nothing)
        if (i < counter.length) {
            counter[i] = 1;
        }
    }

    public static final ArrayList<Integer> longestIncreasingContiguousSubsequence(int[] list) {
        ArrayList<Integer> longest = new ArrayList<Integer>();
        ArrayList<Integer> current = null;
        for (int i = 0; i < list.length; i++) {
            int currentElement = list[i];
            if (i == 0 || currentElement < list[i - 1]) {
                current = new ArrayList<Integer>();
            }
            current.add(currentElement);
            if (current.size() > longest.size()) {
                longest = current;
            }
        }
        return longest;
    }

    private static final int findLongestSubsequenceEndingAtI(ArrayList<ArrayList<Integer>> cache, int i, int[] list,
            int currentLongestSubsequenceIndex) {
        // Loop through longest subsequence cache and find longest one element i can be added to
        int element = list[i];
        ArrayList<Integer> longestSubsequence = cache.get(0);
        for (int j = 0; j < i; j++) {
            ArrayList<Integer> subsequence = cache.get(j);
            if (subsequence.size() > longestSubsequence.size() && element > subsequence.get(subsequence.size() - 1)) {
                longestSubsequence = subsequence;
            }
        }
        // Make copy of longest subsequence, add element i to it, and set to cache
        ArrayList<Integer> longestSubsequenceEndingAtI = new ArrayList<Integer>();
        longestSubsequenceEndingAtI.addAll(longestSubsequence);
        longestSubsequenceEndingAtI.add(element);
        cache.set(i, longestSubsequenceEndingAtI);
        return (longestSubsequenceEndingAtI.size() > cache.get(currentLongestSubsequenceIndex).size()) ? i
                : currentLongestSubsequenceIndex;
    }

    public static final ArrayList<Integer> longestIncreasingSubsequence(int[] list) {
        // Loop over array and construct longest subsequences ending at and including element i.
        // These results are stored in a cache from i to longest subsequence.
        ArrayList<ArrayList<Integer>> cache = new ArrayList<ArrayList<Integer>>(list.length);
        for (int i = 0; i < list.length; i++) {
            cache.add(new ArrayList<Integer>());
        }
        // Keep track of the current max subsequence on each iteration
        int longestSubsequenceIndex = 0;
        for (int i = 0; i < list.length; i++) {
            longestSubsequenceIndex = findLongestSubsequenceEndingAtI(cache, i, list, 0);
        }
        // Then loop over results to find longest subsequence - Not necessary since max is
        // continuously updated
        // ArrayList<Integer> longestSubsequence = new ArrayList<Integer>();
        // for (ArrayList<Integer> subsequence : cache) {
        // if (subsequence.size() > longestSubsequence.size()) {
        // longestSubsequence = subsequence;
        // }
        // }
        return cache.get(longestSubsequenceIndex);
    }

    public static int largestContiguousSum(int[] a) {
        if (a.length == 0) {
            return 0;
        }
        if (a.length == 1) {
            return a[0];
        }
        int maxSum = 0;
        int[] dp = new int[a.length];
        dp[0] = a[0];
        for (int i = 1; i < a.length; i++) {
            dp[i] = Math.max(a[i], dp[i - 1] + a[i]);
            if (dp[i] > maxSum) {
                maxSum = dp[i];
            }
        }
        return maxSum;
    }

    // Count number of ways to make change in {25,10,5,1} given n
    public static final int makeChange(int n, int denom) {
        int nextDenom = 0;
        switch (denom) {
        case 25:
            nextDenom = 10;
            break;
        case 10:
            nextDenom = 5;
            break;
        case 5:
            nextDenom = 1;
            break;
        case 1:
            return 1;
        }

        int count = 0;
        for (int i = 0; i * denom <= n; i++) {
            count += makeChange(n - i * denom, nextDenom);
        }
        return count;
    }

    // Same as makeChange, but iterates over choices from smallest to largest
    public static final int makeChange2(int n, int[] choices, int choiceIndex) {
        if (n == 0) { // Base case: found a set of choices that add to original n
            return 1;
        }
        if (choiceIndex > choices.length - 1) {
            return 0;
        }
        int count = 0;
        // For each choice, take all multiples that fit in n and recurse on remainder
        // but only look at choices that have not been seen yet
        for (int i = 0; i * choices[choiceIndex] <= n; i++) {
            count += makeChange2(n - i * choices[choiceIndex], choices, choiceIndex + 1);
        }
        return count;
    }

    // Read binary string MS to LS digit
    // Convert each to base 10 by adding 1 to result
    public static int convertBinaryStringToInt(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            result <<= 1; // Shift existing result left by one digit
            if (s.charAt(i) == '1') {
                result += 1; // Add 1 to current LS digit of result
            }
        }
        return result;
    }

    // Read base 10 number LS to MS digit and convert each digit to binary via %2
    // Reverse string to order MS to LS
    public static String convertIntToBinaryString(int n) {
        StringBuffer sb = new StringBuffer();
        if (n == 0) {
            return "0";
        }
        while (n > 0) {
            sb.append(n % 2); // Get LS digit of current number
            n >>= 1; // Shift current number right by one digit
        }
        return sb.reverse().toString();
    };

    // Convert Excel codes to integer (1-based)
    // Ex. "A" = 1, "AA" = "27" (1x26^1 + 1x26^0)
    public static int convertExcelCodeToInt(String s) {
        int result = 0;
        // Read from most to least significant digit in Excel code
        for (int i = 0; i < s.length(); i++) {
            // Shift all existing result digits left by one digit (does not shift for first
            // iteration since result = 0)
            result *= 26;
            // Place most significant Excel code's value into result's least significant digit(s)
            result += s.charAt(i) - 'A' + 1;
        }
        return result;
    }

    // Convert integer to Excel code (1-based)
    // Ex. 1 = "A", 53 = "BA" (2x26^1 + 1x26^0)
    public static String convertIntToExcelCode(int n) {
        StringBuffer sb = new StringBuffer();
        while (n > 0) {
            // Fetch least significant digit and convert to character code
            int cCode = n % 26 + 65 - 1; // "A" starts from 65
            // Append code to results
            sb.append((char) cCode);
            // Shift integer right by one digit (base 26)
            n /= 26;
        }
        // Results were constructed from least to most significant codes, so need to reverse
        return sb.reverse().toString();
    }

    /*
     * DP problem calculating max outcome for game where players alternate turns picking from start
     * or end of line of bags of coins. Solution assumes you pick first. Non-memoized case is
     * O(2^n). Memoized case is O(n^2) as we are building an n*n results table.
     */

    public static int maxCounter = 0;

    public static int max(int[] a, int s, int e) {
        if (s > e || s < 0 || e < 0 || s >= a.length || e >= a.length) {
            return 0;
        }
        maxCounter++;
        if (s == e) {
            return a[s];
        }
        int c1 = a[s] + max(a, s + 2, e);
        int c2 = a[s] + max(a, s + 1, e - 1);
        int c3 = a[e] + max(a, s, e - 2);
        int c4 = a[e] + max(a, s + 1, e - 1);

        return Math.max(Math.max(c1, c2), Math.max(c3, c4));
    }

    public static int maxCounterMemoize = 0;

    public static int maxMemoize(int[] a, int s, int e, int[][] cache) {
        if (s > e || s < 0 || e < 0 || s >= a.length || e >= a.length) {
            return 0;
        }
        maxCounterMemoize++;
        if (cache[s][e] >= 0) {
            return cache[s][e];
        }
        int result = 0;
        if (s == e) {
            result = a[s];
        } else {
            int c1 = a[s] + maxMemoize(a, s + 2, e, cache);
            int c2 = a[s] + maxMemoize(a, s + 1, e - 1, cache);
            int c3 = a[e] + maxMemoize(a, s, e - 2, cache);
            int c4 = a[e] + maxMemoize(a, s + 1, e - 1, cache);
            result = Math.max(Math.max(c1, c2), Math.max(c3, c4));
        }

        cache[s][e] = result;
        return result;
    }

    // Given string of integers (ex. "123") count number of ways it can be converted into string
    // of letters (ex. "ABC", "AY", "LC") where letters are [0, 25]
    public static int countPossibleLetterStrings(String s, int start, int[] cache) {
        if (s == null || s.length() == 0 || start >= s.length()) {
            return 0;
        }
        if (cache[start] > 0) {
            return cache[start];
        }
        if (s.length() == 1 || start == s.length() - 1) {
            return 1;
        }

        // Choose first number char of substring to be converted
        int result = countPossibleLetterStrings(s, start + 1, cache);

        // Get two-digit number at start of substring
        if (start + 1 < s.length()) {
            int num = (s.charAt(start) - '0') * 10 + (s.charAt(start + 1) - '0');
            if (num <= 25) {
                if (start + 2 < s.length()) {
                    result += countPossibleLetterStrings(s, start + 2, cache);
                } else {
                    result += 1;
                }
            }
        }

        cache[start] = result;

        return result;
    }

    public static class BSTNode<T> {
        public BSTNode<T> left = null;
        public BSTNode<T> right = null;
        public T data = null;
        public boolean visited = false;

        public BSTNode(T data) {
            this.data = data;
        }
    }

    public static void traverseBSTIterativeInOrder(BSTNode<Integer> root) {
        Stack<BSTNode<Integer>> stack = new Stack<BSTNode<Integer>>();
        stack.push(root);
        while (!stack.empty()) {
            BSTNode<Integer> current = stack.pop();
            if (current == null) {
                continue;
            }
            if (!current.visited) {
                current.visited = true;
                stack.push(current.right);
                stack.push(current);
                stack.push(current.left);
            } else {
                System.out.println(current.data);
            }
        }
    }

    // Ensure every 2nd element of the array is greater than the left and right element
    public static void reOrder(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if ((i & 1) == 0) { // Even index - need to be larger
                if (a[i - 1] > a[i]) {
                    swap(a, i - 1, i);
                }
            } else { // Odd index - need to be smaller
                if (a[i - 1] < a[i]) {
                    swap(a, i - 1, i);
                }
            }
        }
    }

    public static void printFactors(int number) {
        System.out.println(number + " * 1");
        printFactors("", number, number);
    }

    public static void printFactors(String expression, int n, int prevFactor) {
        for (int factor = n / 2; factor >= 2; factor--) {
            if (n % factor == 0 && factor <= prevFactor) {
                int next = n / factor;
                if (next <= factor) {
                    System.out.println(expression + factor + " * " + next);
                }
                printFactors(expression + factor + " * ", next, factor);
            }
        }
    }

    public static ArrayList<ArrayList<Integer>> getFactors(int n) {
        ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
        if (n < 1) {
            return results;
        }
        // Implicitly add n * 1 as a result
        ArrayList<Integer> temp = new ArrayList<Integer>();
        temp.add(n);
        temp.add(1);
        results.add(temp);
        getFactors(n, n, new ArrayList<Integer>(), results);
        return results;
    }

    // Get all factorizations of n, not including any duplicates
    public static void getFactors(int n, int prevFactor, ArrayList<Integer> currRes, ArrayList<ArrayList<Integer>> res) {

        // Try factors from n/2 down to 2 (anything larger than n/2 other than n
        // would not divide n)
        for (int factor = n / 2; factor >= 2; factor--) {

            // Only look at factors in a decreasing fashion (ie. newFactor <=
            // oldFactor) to prevent duplicate factorizations
            if (n % factor == 0 && factor <= prevFactor) {
                int next = n / factor;

                // If next n is <= factor, we have a factorization that
                // satisfies the "decreasing" condition mentioned above
                // So add this factorization to the results
                if (next <= factor) {
                    ArrayList<Integer> temp = new ArrayList<Integer>(currRes);
                    temp.add(factor);
                    temp.add(next);
                    res.add(temp);
                }

                // Recurse on the next n, which now also has an updated
                // prevFactor
                ArrayList<Integer> temp = new ArrayList<Integer>(currRes);
                temp.add(factor);
                getFactors(next, factor, temp, res);
            }
        }
    }

    public static ArrayList<Integer> getPrimeFactors2(int n) {
        ArrayList<Integer> results = new ArrayList<Integer>();

        // While n is even, keep dividing by 2
        while (n % 2 == 0) {
            n >>= 1;
            results.add(2);
        }

        // n is now odd; only need to test odd factors
        int max = (int) Math.sqrt(n);
        for (int i = 3; i <= max; i += 2) {
            while (n % i == 0) {
                results.add(i);
                n /= i;
            }
        }

        // n is prime; if n weren't prime, it would be reduced to 1
        if (n > 1) {
            results.add(n);
        }

        return results;
    }

    public static ArrayList<Integer> getPrimeFactors(int n) {
        ArrayList<Integer> results = new ArrayList<Integer>();

        // Test factors from 2 to sqrt(n) (going larger than sqrt(n) leads to
        // testing complements)
        int max = (int) Math.sqrt(n);
        for (int i = 2; i <= max; i++) {
            while (n % i == 0) {
                results.add(i);
                n /= i;
            }
        }

        // n is prime; if n weren't prime, it would be reduced to 1
        if (n > 1) {
            results.add(n);
        }

        return results;
    }

    public static int findInfluencer2(boolean[][] m) {

        // (x,y) indicates that x influences y
        // Influencer: someone who influences every other person and not
        // influenced by any other person

        // ie. Find column i with all 1's (other than at row i) such that row i
        // is all 0's

        // Check if column i has all 1's; then check if row i has all 0's
        // short circuit out when first failure is encountered
        // Check if x influences everyone (except himself, since that's not
        // required)
        for (int x = 0; x < m[0].length; x++) {
            boolean xInfluencesEveryone = true;
            for (int y = 0; y < m.length; y++) {
                if (x != y) {
                    if (!m[y][x]) {
                        xInfluencesEveryone = false;
                        break;
                    }
                }
            }
            // If x influences everyone, check that x is not influenced by
            // anyone else
            // Check that row x has all 0's
            boolean xNotInfluenced = true;
            if (xInfluencesEveryone) {
                for (int z = 0; z < m[0].length; z++) {
                    if (m[x][z]) {
                        xNotInfluenced = false;
                        break;
                    }
                }
                if (xNotInfluenced) {
                    return x;
                }
            }
        }

        return -1;
    }

    public static int findInfluencer(boolean[][] m) {
        int result = -1;

        // (x,y) indicates that x influences y
        // Influencer: someone who influences every other person and not
        // influenced by any other person

        // ie. Find column i with all 1's (other than at row i) such that row i
        // is all 0's

        // Initially, consider everyone is an influencer, and slowly remove them
        boolean[] influencers = new boolean[m[0].length];
        for (int i = 0; i < influencers.length; i++) {
            influencers[i] = true;
        }

        // For each relation other than a person to themselves:
        // - if x does not influence y, x is not an influencer
        // - if x influences y, y is not an influencer
        for (int y = 0; y < m.length; y++) {
            for (int x = 0; x < m[0].length; x++) {
                if (x != y) {
                    if (!m[y][x]) { // x does not influence y
                        influencers[x] = false;
                    } else { // x influences y
                        influencers[y] = false;
                    }
                }
            }
        }

        // Return influencer if one is found
        for (int i = 0; i < influencers.length; i++) {
            if (influencers[i]) {
                return i;
            }
        }

        return result;
    }

    public static void reverse(int[] a, int left, int right) {
        int mid = (left + right) / 2;
        for (int i = left; i < mid; i++) {
            swap(a, i, right - i);
        }
    }

    public static void rotateRight(int[] a, int n) {
        rotateLeft(a, a.length - n);
    }

    public static void rotateLeft(int[] a, int n) {
        reverse(a, 0, n - 1);
        reverse(a, n, a.length - 1);
        reverse(a, 0, a.length - 1);
    }

    public static double pow(int a, int b) {
        if (a == 0) {
            return 0;
        }
        if (a == 1 || b == 0) {
            return 1;
        }
        if (b == 1) {
            return a;
        }

        int mult = 1;
        if (a < 0 && b % 2 != 0) {
            mult = -1;
            a *= -1;
        }

        boolean bNeg = false;
        if (b < 0) {
            bNeg = true;
            b *= -1;
        }

        double pow = 1;
        while (b > 0) {
            // If b is even, mult current result with itself, allowing us to divide b by 2
            // Otherwise, mult current result with a, which results in b decreasing by 1
            if (b % 2 == 0) {
                a *= a;
                b >>= 1;
            } else {
                pow *= a;
                b -= 1;
            }
        }

        pow *= mult;
        if (bNeg) {
            return 1 / pow;
        }
        return pow;
    }

    // Return number of numbers [0, n] with an even number of odd digits
    public static int numEvenOddDigits(int n) {
        int total = 0;
        for (int i = 0; i <= n; i++) {
            int j = i;
            int numOddDigits = 0;
            while (j > 0) {
                if ((j % 10) % 2 == 1) {
                    numOddDigits++;
                }
                j /= 10;
            }
            if ((numOddDigits % 2) == 0) {
                System.out.println(i);
                total++;
            }
        }
        return total;
    }
}
