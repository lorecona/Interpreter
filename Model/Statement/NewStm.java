package Model.Statement;

import Model.Expression.IExpression;
import Model.ProgramState;


public class NewStm implements IStatement {

    private String variable;
    private IExpression expr;

    public NewStm(String variable, IExpression expr) {
        this.variable = variable;
        this.expr = expr;
    }

    @Override
    public ProgramState execute(ProgramState ps) {

        int result = this.expr.evaluate(ps.peekStackSymTab(), ps.getHeap());
        int address = ps.getHeap().allocate(result);
        ps.peekStackSymTab().put(variable, address);

        return null;
    }

    @Override
    public String toString() {
        return "newStmt(" + variable + ", " + expr + ")";
    }
}
