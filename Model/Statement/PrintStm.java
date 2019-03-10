package Model.Statement;

import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.MyList;

public class PrintStm implements IStatement {
    IExpression expression;

    public PrintStm(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState ps) {
        MyList<Integer> output = ps.getOutput();
        output.add(expression.evaluate(ps.peekStackSymTab(), ps.getHeap()));

        return null;
    }

    @Override
    public String toString()
    {
        return "print(" + expression.toString() + ")";
    }
}
