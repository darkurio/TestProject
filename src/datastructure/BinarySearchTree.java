package datastructure;

public class BinarySearchTree<K extends Comparable, V> {

    private Node<K, V> root = null;

    public void insert(K key, V value) {
        Node<K, V> curr = root, prev = null;
        boolean right = false;
        while (curr != null) {
            prev = curr;
            if (key.compareTo(curr.key) > 0) { // If value >= curr
                curr = curr.right;
                right = true;
            } else if (key.compareTo(curr.key) < 0) { // If value < curr
                curr = curr.left;
                right = false;
            } else {
                curr.value = value;
                return;
            }
        }
        curr = new Node<K, V>(key, value, null, null, prev);
        if (prev != null) {
            if (right) {
                prev.right = curr;
            } else {
                prev.left = curr;
            }
        }
    }

    public void insertRecursive(K key, V value, Node<K, V> root, Node<K, V> prev, boolean right) {

        // Base
        if (root == null) {
            root = new Node<K, V>(key, value, null, null, prev);

            // Update parent link based on whether traversed left or right to get to current root
            if (prev != null) {
                if (right) {
                    prev.right = root;
                } else {
                    prev.left = root;
                }
            }
            return;
        }

        // Update this node
        if (root.key == key) {
            root.value = value;
            return;
        }

        // Recursive
        if (key.compareTo(root.key) >= 0) {
            insertRecursive(key, value, root.right, root, true);
        } else {
            insertRecursive(key, value, root.left, root, false);
        }
    }

    public V get(K key) {
        Node<K, V> curr = root;
        while (curr != null) {
            if (key.compareTo(curr.key) > 0) {
                curr = curr.right;
            } else if (key.compareTo(curr.key) < 0) {
                curr = curr.left;
            } else {
                return curr.value;
            }
        }
        return null;
    }

    public void remove(K key) {
        Node<K, V> curr = root;
        while (curr != null) {
            if (key.compareTo(curr.key) > 0) {
                curr = curr.right;
            } else if (key.compareTo(curr.key) < 0) {
                curr = curr.left;
            } else {
                remove(curr);
            }
        }
    }

    public void clear() {
        this.root = null;
    }

    private void remove(Node<K, V> n) {
        if (n.parent == null) { // n is root
            root = null;
            return;
        }

        // n has no children
        if (n.left == null && n.right == null) {
            // update parent
            if (n.parent.left == n) {
                n.parent.left = null;
            } else {
                n.parent.right = null;
            }
        }

        // n has only left child
        if (n.left != null && n.right == null) {
            // find max of left sub-tree of n and replace n with max
            Node<K, V> max = findMax(n.left);

            max.parent.right = null; // remove link on old parent

            max.parent = n.parent; // set link to new parent
            if (n.parent.left == n) { // set parent link to max based on whether n is left or right
                max.parent.left = max;
            } else {
                max.parent.right = max;
            }

            max.left = n.left; // set link to new left child

            return;
        }

        // n has only right child
        if (n.left == null && n.right != null) {
            // find min of right sub-tree of n and replace n with min
            Node<K, V> min = findMin(n.right);

            min.parent.left = null; // remove link on old parent

            min.parent = n.parent; // set link to new parent
            if (n.parent.left == n) { // set parent link to min based on whether n is left or right
                min.parent.left = min;
            } else {
                min.parent.right = min;
            }

            min.right = n.right; // set link to new right child

            return;
        }

        // n has both left and right child
        // 1. find min of right sub-tree of n and replace n with it
        Node<K, V> min = findMin(n.right);
        n.key = min.key;
        n.value = min.value;
        // 2. remove min from right sub-tree
        remove(min);
    }

    private Node<K, V> findMax(Node<K, V> root) {
        Node<K, V> curr = root;
        while (curr != null && curr.right != null) {
            curr = curr.right;
        }
        return curr;
    }

    private Node<K, V> findMin(Node<K, V> root) {
        Node<K, V> curr = root;
        while (curr != null && curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    private static class Node<K extends Comparable, V> {

        private K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;
        private Node<K, V> parent;

        public Node(K key, V value, Node left, Node right, Node parent) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }
}
