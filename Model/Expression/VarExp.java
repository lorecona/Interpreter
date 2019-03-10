package Model.Expression;

import Model.Utils.IDictionary;
import Exception.ExpressionException;
import Model.Utils.MyHeap;

public class VarExp implements IExpression {
    private String name;

    public VarExp(String name) {
        this.name = name;
    }

    @Override
    public int evaluate(IDictionary<String, Integer> symTable, MyHeap<Integer> heap)
    {
        if (symTable.contains(this.name))
        {
            return symTable.get(this.name);
        }
        else
            throw new ExpressionException("(In VarExp) Inexistent variable!");

    }

    @Override
    public String toString()
    {
        return this.name;
    }
}
