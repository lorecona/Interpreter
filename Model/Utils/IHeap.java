package Model.Utils;

import java.util.Map;

public interface IHeap<T> {

    int allocate(T val);
    void putValue(int addr, T val);
    T getValue(int addr);
    T deallocate(int addr);

    void setMap(Map<Integer, T> map);
    Map<Integer, T> getMap();
    String toString();
}
