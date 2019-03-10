package Model.Utils;
import java.util.Stack;

public interface IStack<T> {
    void push(T elem);
    T pop();
    boolean isEmpty();
    Stack<T> getStack();
    String toString();
}
