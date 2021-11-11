package maps;

import java.util.*;

/**
 * Separate chaining table implementation of hash tables. Note that all
 * "matching" is based on the equals method.
 *
 * @author A. Duarte, J. Vélez, J. Sánchez-Oro
 * @param <K> The key
 * @param <V> The stored value
 */
public class HashTableMapSC<K, V> implements Map<K, V> {
    private ArrayList<HashEntry<K, V>>[] map;
    private int factor, cap, size;

    private class HashEntry<T, U> implements Entry<T, U> {
        private T key;
        private U value;

        public HashEntry(T k, U v) {
            key = k;
            value = v;
        }

        @Override
        public U getValue() {
            return value;
        }

        @Override
        public T getKey() {
            return key;
        }
        public U setValue(U val) {
            U out = value;
            value = val;
            return val;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            HashEntry<?, ?> hashEntry = (HashEntry<?, ?>) o;
            return Objects.equals(key, hashEntry.key) && Objects.equals(value, hashEntry.value);
        }
        /**
         * Entry visualization.
         */
        @Override
        public String toString() {
            return "key: "+key.toString()+" value: "+value.toString();
        }
    }

    private class HashTableMapIterator<T, U> implements Iterator<Entry<T, U>> {
        int i;
        Iterator<HashEntry<T, U>> it;
        int elemRes;
        ArrayList<HashEntry<T, U>>[] map;

        //Ejercicio 2.2
        public HashTableMapIterator(ArrayList<HashEntry<T, U>>[] map, int numElems) {
            i=0;
            elemRes = numElems;
            this.map = map;
            goToNextElement();
        }

        private void goToNextElement() {
            while(i<map.length && map[i]!=null){
                i++;
            }
            if(i!=map.length){
                it = map[i].iterator();
            }
        }

        @Override
        public boolean hasNext() {
            if (it.hasNext()){
                return true;
            }
            else{
                goToNextElement();
                return i!=map.length;
            }
        }

        @Override
        public Entry<T, U> next() {
            if (!it.hasNext()){
                goToNextElement();
            }
            return it.next();
        }

        @Override
        public void remove() {
            // NO HAY QUE IMPLEMENTARLO
            throw new UnsupportedOperationException("Not implemented.");
        }
    }

    private class HashTableMapKeyIterator<T, U> implements Iterator<T> {
        HashTableMapIterator<T, U> it;
        public HashTableMapKeyIterator(HashTableMapIterator<T, U> it) {
            this.it = it;
        }

        @Override
        public T next() {
            return it.next().getKey();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public void remove() {
            // NO HAY QUE IMPLEMENTARLO
            throw new UnsupportedOperationException("Not implemented.");
        }
    }

    private class HashTableMapValueIterator<T, U> implements Iterator<U> {
        HashTableMapIterator<T, U> it;
        public HashTableMapValueIterator(HashTableMapIterator<T, U> it) {
            this.it = it;
        }

        @Override
        public U next() {
            return it.next().getValue();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not implemented.");
        }
    }

    /**
     * Creates a hash table
     */
    public HashTableMapSC() {
        map = new ArrayList[100];
        cap = 100;
        factor = 301;
        size=0;
    }

    /**
     * Creates a hash table.
     *
     * @param cap initial capacity
     */
    public HashTableMapSC(int cap) {
        map = new ArrayList[cap];
        this.cap = cap;
        factor = primes(cap);
        size=0;
    }

    private int primes(int n){
        int p = n+1;
        boolean prime = false;
        while(!prime){
            prime = true;
            for(int i=1; i<= Math.sqrt(p) && prime; i++){
                prime &= !(p%i==0);
            }
            p++;
        }
        return p;
    }

    /**
     * Creates a hash table with the given prime factor and capacity.
     *
     * @param p prime number
     * @param cap initial capacity
     */
    public HashTableMapSC(int p, int cap) {
        map = new ArrayList[cap];
        factor = p;
        size=0;
    }

    /**
     * Hash function applying MAD method to default hash code.
     *
     * @param key Key
     * @return
     */
    protected int hashValue(K key) {
        return (Objects.hash(key)%factor)%cap;
    }

    /**
     * Returns the number of entries in the hash table.
     *
     * @return the size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns whether or not the table is empty.
     *
     * @return true if the size is 0
     */
    @Override
    public boolean isEmpty() {
        return size==0;
    }

    /**
     * Returns the value associated with a key.
     *
     * @param key
     * @return value
     */
    @Override
    public V get(K key) throws IllegalStateException {
        int n = hashValue(key);
        ArrayList<HashEntry<K,V>> list = null;
        try{
            list = map[n];
        }
        catch (Exception e){
            throw new IllegalStateException();
        }
        if (list==null){
            return null;
        }
        else{
            Entry<K, V> entry = find(list, key);
            if(entry==null){
                return null;
            }
            else{
                return entry.getValue();
            }
        }


    }

    private HashEntry<K,V> find(List<HashEntry<K, V>> list, K key){
        for(HashEntry<K, V> entry : list){
            if(key.equals(entry.getKey())){
                return entry;
            }
        }
        return null;
    }

    /**
     * Put a key-value pair in the map, replacing previous one if it exists.
     *
     * @param key
     * @param value
     * @return value
     */
    @Override
    public V put(K key, V value) throws IllegalStateException {
        int n = hashValue(key);
        ArrayList<HashEntry<K,V>> list = null;
        try{
            list = map[n];
        }
        catch (Exception e){
            throw new IllegalStateException();
        }
        if (list==null){
            list =new ArrayList<>();
            list.add(new HashEntry<>(key, value));
            map[n]=list;
            size++;
            return null;
        }
        else{
            HashEntry<K, V> entry = find(list, key);
            V out = null;
            if(entry==null){
                list.add(new HashEntry<>(key, value));
                size++;
            }
            else{
                out = entry.getValue();
                entry.setValue(value);
            }
            map[n]= list;
            return out;
        }
    }

    /**
     * Removes the key-value pair with a specified key.
     *
     * @param key
     * @return
     */
    @Override
    public V remove(K key) throws IllegalStateException {
        int n = hashValue(key);
        ArrayList<HashEntry<K,V>> list = null;
        try{
            list = map[n];
        }
        catch (Exception e){
            throw new IllegalStateException();
        }
        if (list==null){
            return null;
        }
        else{
            Entry<K, V> entry = find(list, key);
            V out = null;
            if(entry!=null){
                out = entry.getValue();
                list.remove(entry);
                map[n] = list;
                size--;
            }
            return out;
        }
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new HashTableMapIterator<K, V>(map, size);
    }

    /**
     * Returns an iterable object containing all of the keys.
     *
     * @return
     */
    @Override
    public Iterable<K> keys() {
        HashTableMapKeyIterator<K, V> it=
        new HashTableMapKeyIterator<>(new HashTableMapIterator<K, V>(map, size));
        LinkedList<K> list = new LinkedList<>();
        while(it.hasNext()){
            list.add(it.next());
        }
        return list;
    }

    /**
     * Returns an iterable object containing all of the values.
     *
     * @return
     */
    @Override
    public Iterable<V> values() {
        HashTableMapValueIterator<K, V> it=
                new HashTableMapValueIterator<>(new HashTableMapIterator<K, V>(map, size));
        LinkedList<V> list = new LinkedList<>();
        while(it.hasNext()){
            list.add(it.next());
        }
        return list;
    }

    /**
     * Returns an iterable object containing all of the entries.
     *
     * @return
     */
    @Override
    public Iterable<Entry<K, V>> entries() {
        HashTableMapIterator<K, V> it=
                new HashTableMapIterator<K, V>(map, size);
        LinkedList<Entry<K, V>> list = new LinkedList<>();
        while(it.hasNext()){
            list.add(it.next());
        }
        return list;
    }

    /**
     * Determines whether a key is valid.
     *
     * @param k Key
     */
    protected void checkKey(K k) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Increase/reduce the size of the hash table and rehashes all the entries.
     * @param newCap
     */
    protected void rehash(int newCap) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
