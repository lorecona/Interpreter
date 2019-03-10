package Model.Utils;

public interface IList<T> {
    void add(T elem);
    void remove(T elem);
    T get(int index);
    boolean contains(T elem);
    int size();
    String toString();
}
