package datastructure;

public class LinkedList<K, V> {

    private Entry<K, V> head = null;

    public void add(K key, V value) {

        if (head == null) {
            head = new Entry<K, V>(key, value, null, null);
            return;
        }

        Entry<K, V> newEntry = new Entry<K, V>(key, value, null, head);
        head.prev = newEntry;
        head = newEntry;
    }

    public V get(K key) {
        for (Entry<K, V> entry = head; entry != null; entry = entry.next) {
            if (entry.key == key) {
                return entry.value;
            }
        }
        return null;
    }

    public void remove(K key) {
        for (Entry<K, V> entry = head, prev = null; entry != null; prev = entry, entry = entry.next) {
            if (entry.key == key) {
                if (prev != null) {
                    prev.next = entry.next;
                    entry.next.prev = prev;
                } else {
                    head = entry.next;
                    head.prev = null;
                }
            }
        }
    }

    public void clear() {
        head = null;
    }

    private static class Entry<K, V> {

        private K key;
        private V value;
        private Entry<K, V> next;
        private Entry<K, V> prev;

        public Entry(K key, V value, Entry<K, V> next, Entry<K, V> prev) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }
}
