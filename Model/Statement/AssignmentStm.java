package Model.Statement;

import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.IDictionary;
import Model.Utils.MyHeap;

public class AssignmentStm implements IStatement {
    private String varName;
    private IExpression expression;

    public AssignmentStm(String varName, IExpression expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState ps) {
        IDictionary<String, Integer> symTable = ps.peekStackSymTab();
        MyHeap<Integer> heap = ps.getHeap();

        symTable.put(this.varName, this.expression.evaluate(symTable, heap));

        return null;
    }

    @Override
    public String toString()
    {
        return varName + " = " + expression.toString();
    }
}
