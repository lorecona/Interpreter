package Model.Utils;

import java.util.HashMap;
import java.util.Map;

public class MyHeap<T> implements IHeap<T> {

    private int memory;
    private Map<Integer, T> values;

    public MyHeap(Map<Integer, T> values) {
        this.values = values;
    }

    @Override
    public int allocate(T value) {
        ++this.memory;
        this.values.put(this.memory, value);

        return this.memory;
    }

    @Override
    public void putValue(int addr, T value) {
        this.values.put(addr, value);
    }

    @Override
    public T getValue(int addr) {
        return this.values.get(addr);
    }

    @Override
    public T deallocate(int addr) {
        return this.values.remove(addr);
    }

    @Override
    public void setMap(Map<Integer, T> values) {
        this.values = values;
    }

    @Override
    public Map<Integer, T> getMap() {
        return this.values;
    }

    @Override
    public String toString() {

        String s = "";
        boolean firstTime = true;

        for (HashMap.Entry<Integer, T> entry : this.values.entrySet()) {
            if (!firstTime)
                s += "\n";


            s += entry.getKey().toString() + " -> " + entry.getValue().toString();
            firstTime = false;
        }

        return s;
    }

}
