package Model.Utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import Exception.*;

public class MyProcTable<K,V> implements IProcTable<K,V> {

    private HashMap<K, V> dictionary;

    public MyProcTable(){
        dictionary =  new HashMap<>();
    }

    @Override
    public V get(K key) {
        if (dictionary.get(key) == null) {
            throw new ADTException("(In MyProcTable) Invalid key " + key + " in dictionary");
        }
        return dictionary.get(key);
    }

    @Override
    public V put(K key, V value) {
        return dictionary.put(key, value);
    }

    @Override
    public V remove(K key) {
        return dictionary.remove(key);
    }

    @Override
    public boolean contains(K key) { return dictionary.containsKey(key);
    }

    @Override
    public Collection<V> values() {
        return this.dictionary.values();
    }

    @Override
    public Collection<K> keys() {
        return this.dictionary.keySet();
    }

    @Override
    public int size() {
        return dictionary.size();
    }

    @Override
    public boolean isEmpty() {
        return dictionary.isEmpty();
    }

    @Override
    public IDictionary<K, V> copy() {

        IDictionary<K, V> newCopy = new MyDictionary<>();
        for (K key : this.keys())
            newCopy.put(key, this.dictionary.get(key));
        return newCopy;
    }

    @Override
    public Map<K, V> getMap() {
        return this.dictionary;
    }

    @Override
    public String toString(){
        String s = "";
        for(K key : dictionary.keySet())
            s += key.toString() + "->" + dictionary.get(key).toString() + "\n";
        return s;
    }
}
