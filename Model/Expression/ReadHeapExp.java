package Model.Expression;

import Model.Utils.IDictionary;
import Model.Utils.MyHeap;
import Exception.ExpressionException;


public class ReadHeapExp implements IExpression {

    private String id;

    public ReadHeapExp(String id) {
        this.id = id;
    }

    @Override
    public int evaluate(IDictionary<String, Integer> symTable, MyHeap<Integer> heap) {

        Integer address = symTable.get(this.id);

        if(address == null)
            throw new ExpressionException("(In ReadHeapExp) Unknown variable!");

        Integer value = heap.getValue(address);

        if(value == null)
            throw new ExpressionException("(In ReadHeapExp) Unknown memory adress!");

        return value;

    }

    @Override
    public String toString() {
        return "readHeapExp(" + id + ")";
    }
}
