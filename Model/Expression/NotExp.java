package Model.Expression;

import Model.Utils.IDictionary;
import Exception.ExpressionException;
import Model.Utils.MyHeap;

public class NotExp implements IExpression{

    private IExpression exp;

    public NotExp(IExpression exp) { this.exp = exp;}

    @Override
    public int evaluate(IDictionary<String, Integer> st, MyHeap<Integer> heap) {

        if(exp.evaluate(st, heap) > 0)
            return 0;
        return 1;
    }

    @Override
    public String toString() { return "NotExp(" + exp + ")";}
}
