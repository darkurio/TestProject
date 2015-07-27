package datastructure;

public class HashTable<K, V> {

    private Entry<K, V>[] table = null;
    private int count = 0;
    private float loadFactor = 0.75f;

    private static final int INIT_CAP = 16;

    public HashTable() {
        this.table = new Entry[INIT_CAP];
    }

    public void add(K key, V value) {
        int hashIndex = hashFunction(key);
        for (Entry<K, V> entry = table[hashIndex]; entry != null; entry = entry.next()) {
            if (entry.key() == key) {
                entry.setValue(value);
                return;
            }
        }
        Entry<K, V> entry = new Entry<K, V>(key, value, table[hashIndex]);
        table[hashIndex] = entry;
        count++;

        if (count > (int) (loadFactor * table.length) + 1) {
            rehash();
        }
    }

    public V get(K key) {
        int hashIndex = hashFunction(key);
        for (Entry<K, V> entry = table[hashIndex]; entry != null; entry = entry.next()) {
            if (entry.key() == key) {
                return entry.value;
            }
        }
        return null;
    }

    public void remove(K key) {
        int hashIndex = hashFunction(key);
        for (Entry<K, V> entry = table[hashIndex], prev = null; entry != null; prev = entry, entry = entry
                .next()) {
            if (entry.key() == key) {
                if (prev != null) {
                    prev.next = entry.next();
                } else {
                    table[hashIndex] = entry.next();
                }
                count--;
            }
        }
    }

    public void clear() {
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
        count = 0;
    }

    private void rehash() {
        Entry<K, V>[] tempTable = new Entry[table.length * 2];
        for (int i = 0; i < table.length; i++) {
            for (Entry<K, V> entry = tempTable[i]; entry != null; entry = entry.next) {
                int hashIndex = hashFunction(entry.key(), tempTable.length);
                Entry<K, V> newEntry = new Entry<K, V>(entry.key(), entry.value(),
                        tempTable[hashIndex]);
                tempTable[hashIndex] = newEntry;
            }
        }
    }

    private int hashFunction(K key) {
        return hashFunction(key, table.length);
    }

    private int hashFunction(K key, int capacity) {
        return key.hashCode() % capacity;
    }

    private class Entry<K, V> {
        private K key;
        private V value;
        private Entry next;

        public Entry(K key, V value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K key() {
            return this.key;
        }

        public V value() {
            return this.value;
        }

        public Entry next() {
            return this.next;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public void setNext(Entry next) {
            this.next = next;
        }
    }
}
