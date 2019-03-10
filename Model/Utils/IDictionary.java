package Model.Utils;
import java.util.Collection;
import java.util.Map;

public interface IDictionary<K, V> {
    V get(K key);
    V put(K key, V value);
    V remove(K key);
    Collection<V> values();
    Collection<K> keys();
    boolean contains(K key);
    int size();
    boolean isEmpty();
    Map<K, V> getMap();
    String toString();

    IDictionary<K,V> copy();

}
