package datastructure;

public class Stack<K, V> {

    private Entry<K, V> top = null;

    public void push(K key, V value) {
        Entry<K, V> newEntry = new Entry<K, V>(key, value, null);
        if (top != null) {
            newEntry.next = top;
        }
        top = newEntry;
    }

    public V pop(V value) {
        if (top != null) {
            Entry<K, V> entry = top;
            top = top.next;
            return entry.value;
        }
        return null;
    }

    public V get(K key) {
        for (Entry<K, V> entry = top; entry != null; entry = entry.next) {
            if (entry.key == key) {
                return entry.value;
            }
        }
        return null;
    }

    public void remove(K key) {
        for (Entry<K, V> entry = top, prev = null; entry != null; prev = entry, entry = entry.next) {
            if (entry.key == key) {
                if (prev != null) {
                    prev.next = entry.next;
                } else {
                    top = entry.next;
                }
                return;
            }
        }
    }

    public void clear() {
        top = null;
    }

    private static class Entry<K, V> {

        private K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
