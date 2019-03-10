package Model.Utils;

import java.util.ArrayList;

public class MyList<T> implements IList<T> {

    private ArrayList<T> list;

    public MyList(){
        list = new ArrayList<>();
    }

    @Override
    public void add(T elem) {
        list.add(elem);
    }

    @Override
    public int size(){
        return list.size();
    }

    @Override
    public void remove(T elem) {
        list.remove(elem);
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public boolean contains(T elem) { return list.contains(elem); }

    @Override
    public String toString(){
        String s = "";
        for(T elem : list)
            s += elem.toString() + " ";
        s += "\n";
        return s;
    }
}
