package Model.Expression;

import Model.Utils.IDictionary;
import Exception.ExpressionException;
import Model.Utils.MyHeap;

public class ArithExp implements IExpression {
    private IExpression op1, op2;
    private char operator;

    public ArithExp(IExpression op1, char operator, IExpression op2) {
        this.op1 = op1;
        this.operator = operator;
        this.op2 = op2;
    }


    @Override
    public int evaluate(IDictionary<String, Integer> st, MyHeap<Integer> heap) {
        int firstRes = this.op1.evaluate(st, heap);
        int secondRes = this.op2.evaluate(st, heap);

        switch (this.operator) {
            case '+':
                return firstRes + secondRes;

            case '-':
                return firstRes - secondRes;

            case '*':
                return firstRes * secondRes;

            case '/':
                if (secondRes == 0) {
                    throw new ExpressionException("(In ArithExp) Division by 0!");
                }

                return firstRes / secondRes;

            default:
                throw new ExpressionException("Invalid operator!");
        }
    }

    @Override
    public String toString()
    {
        return op1.toString() + " " + this.operator + " " + this.op2.toString();
    }
}
