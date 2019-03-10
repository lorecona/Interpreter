package Model.Expression;

import Model.Utils.IDictionary;
import Model.Utils.MyHeap;
import Exception.ExpressionException;

public class BooleanExp implements IExpression {

    private IExpression exp1, exp2;
    private String rel;

    public BooleanExp(IExpression exp1, String rel, IExpression exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.rel = rel;
    }

    @Override
    public int evaluate(IDictionary<String, Integer> symTable, MyHeap<Integer> heap) {
        int res1 = this.exp1.evaluate(symTable, heap);
        int res2 = this.exp2.evaluate(symTable, heap);

        switch(this.rel) {
            case "<":
                return res1 < res2 ? 1 : 0;

            case "<=":
                return res1 <= res2 ? 1 : 0;

            case ">":
                return res1 > res2 ? 1 : 0;

            case ">=":
                return res1 >= res2 ? 1 : 0;

            case "==":
                return res1 == res2 ? 1 : 0;

            case "!=":
                return res1 != res2 ? 1 : 0;

            default:
                throw new ExpressionException("(In BooleanExp) Invalid relational operator!");
        }
    }

    @Override
    public String toString() {
        return this.exp1 + " " + this.rel + " " + this.exp2;
    }
}
