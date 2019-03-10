package Model.Expression;

import Model.Utils.IDictionary;
import Model.Utils.MyHeap;

public class ConstExp implements IExpression {
    private int value;
    public ConstExp(int value){ this.value = value;}

    @Override
    public int evaluate(IDictionary<String, Integer> st, MyHeap<Integer> heap) {
        return this.value;
    }

    @Override
    public String toString()
    {
        return String.valueOf(value);
    }
}
