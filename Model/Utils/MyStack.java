package Model.Utils;

import java.util.Stack;

public class MyStack<T> implements IStack<T>{
    Stack<T> stack;

    public MyStack(){
        stack = new Stack<>();
    }

    @Override
    public void push(T elem) {
        stack.push(elem);
    }

    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public Stack<T> getStack() { return this.stack; }

    @Override
    public String toString(){
        String s = "";
        for(T elem : stack)
            s += elem.toString() + "\n";
        return s;
    }
}
