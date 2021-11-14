
package dictionary;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author mayte
 * @param <K>
 * @param <V>
 */
public class MyDictionary<K,V> implements Dictionary<K,V> {
    
    /**
     * @param <T> Key type
     * @param <U> Value type
     *
     */
    private class HashEntry<T, U> implements Entry<T, U> {

        protected T key;
        protected U value;

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
            U oldValue = value;
            value = val;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {

            if (o.getClass() != this.getClass()) {
                return false;
            }

            HashEntry<T, U> ent;
            try {
                ent = (HashEntry<T, U>) o;
            } catch (ClassCastException ex) {
                return false;
            }
            return (ent.getKey().equals(this.key))
                    && (ent.getValue().equals(this.value));
        }

        /**
         * Entry visualization.
         */
        @Override
        public String toString() {
            return "(" + key + "," + value + ")";
        }
    }
    
    private class HashDictionaryIterator<T, U> implements Iterator<Entry<T, U>> {
        int index;
        MyDictionary<T, U> dictionary;
        Iterator<MyDictionary<T, U>.HashEntry<T, U>> iterator;

        public HashDictionaryIterator(MyDictionary<T, U> dictionary){
            this.dictionary = dictionary;
            index = 0;
            while (index<dictionary.cap&&dictionary.table[index]==null){
                index++;
            }
            if(dictionary.table[index]!=null){
                iterator = dictionary.table[index].iterator();
            }

        }

        @Override
        public boolean hasNext() {
            return  iterator!=null&&iterator.hasNext();
        }

        @Override
        public Entry<T, U> next() {
            Entry<T, U> out = iterator.next();
            if(!iterator.hasNext()){
                iterator = null;
                while (index<dictionary.cap&&dictionary.table[index]==null){
                    index++;
                }
                if(dictionary.table[index]!=null){
                    iterator = dictionary.table[index].iterator();
                }
            }
            return out;
        }

    }
    private int size;
    private int cap;
    private LinkedList<HashEntry<K, V>>[] table;
    private int prime, a, b;

    public MyDictionary(){
        size=0;
        cap=20;
        table= new LinkedList[20];
        prime=23;
        a = (int) (Math.random()*23);
        b = (int) (Math.random()*23);
    }
    
    /**
     * Hash function applying MAD method to default hash code.
     *
     * @param key Key
     * @return
     */
    private int hashValue(K key) {
        double m = key.hashCode()%prime;
        double n = (((m*a)%prime)+b)%prime;
        return (int) (n%cap);
    }
    
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalStateException {
        if(key ==null){
            throw new IllegalStateException("InvalidKeyException");
        }
        int index = hashValue(key);
        HashEntry<K, V> entry = new HashEntry<>(key, value);
        if(table[index]==null){
            table[index] = new LinkedList();
        }
        table[index].addLast(entry);
        size++;
        double rat = (double)size/ (double) cap;
        if(rat>=0.75){
            rehash();
        }
        return entry;
    }

    @Override
    public Entry<K, V> find(K key) throws IllegalStateException {
        if(key ==null){
            throw new IllegalStateException("InvalidKeyException");
        }
        int index = hashValue(key);
        Entry<K, V> out = null;
        if(table[index]!=null){
            Iterator<HashEntry<K, V>> it = table[index].iterator();
            while (it.hasNext()&&out==null){
                HashEntry<K, V> entry = it.next();
                if(entry.getKey().equals(key)){
                    out = entry;
                }
            }
        }
        return out;
    }

    @Override
    public Iterable<Entry<K, V>> findAll(K key) throws IllegalStateException {
        if(key ==null){
            throw new IllegalStateException("InvalidKeyException");
        }
        int index = hashValue(key);
        LinkedList<Entry<K, V>> out = new LinkedList<>();
        if(table[index]!=null){
            Iterator<HashEntry<K, V>> it = table[index].iterator();
            while (it.hasNext()&&out==null){
                HashEntry<K, V> entry = it.next();
                if(entry.getKey().equals(key)){
                    out.addLast(entry);
                }
            }
        }
        return out;
    }

    @Override
    public Entry<K, V> remove(Entry<K, V> e) throws IllegalStateException {
        HashEntry<K, V> entry = checkHashEntry(e);
        int index = hashValue(entry.getKey());
        Entry<K, V> out = null;
        if(table[index]!=null&&table[index].remove(e)){
            out = entry;
            size--;
        }
        return out;
    }

    @Override
    public Iterable<Entry<K, V>> entries() {
        LinkedList<Entry<K, V>> out = new LinkedList<>();
        for(int i=0; i<cap; i++){
            if(table[i]!=null){
                for(Entry<K, V> entry: table[i]){
                    out.addLast(entry);
                }
            }
        }
        return out;
    }
    
    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new HashDictionaryIterator(this);
    }
    /**
     * Doubles the size of the hash table and rehashes all the entries.
     */
    private void rehash() {
        Iterable<Entry<K, V>> entries = entries();
        cap = cap*2;
        if(prime<=cap){
            prime = newPrime(cap);
        }
        a = (int) (Math.random()*prime);
        b = (int) (Math.random()*prime);
        table = new LinkedList[cap];
        size=0;
        for(Entry<K, V> entry: entries){
            insert(entry.getKey(), entry.getValue());
        }
    }
    private int newPrime(int start){
        boolean prime = false;
        int out = start;
        while (!prime){
            out++;
            prime=true;
            int div = 2;
            int lim = (int) Math.sqrt(out);
            while (div<=lim&& prime){
                prime&= (out%div!=0);
                div++;
            }
        }
        return out;
    }

    private HashEntry checkHashEntry(Entry<K, V> entry){
        if(entry==null || !(entry instanceof HashEntry<K, V>)){
            throw new IllegalStateException("The entry is invalid");
        }
        return (HashEntry<K, V>)entry;
    }
}
