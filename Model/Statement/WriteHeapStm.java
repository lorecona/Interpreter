package Model.Statement;

import Model.Expression.IExpression;
import Model.ProgramState;
import Exception.ExpressionException;
import Model.Utils.IDictionary;

public class WriteHeapStm implements IStatement {

    private String id;
    private IExpression expr;

    public WriteHeapStm(String id, IExpression expr) {
        this.id = id;
        this.expr = expr;
    }

    @Override
    public ProgramState execute(ProgramState ps) {
        IDictionary<String, Integer> symTable = ps.peekStackSymTab();
        Integer address = symTable.get(id);

        if(address == null)
            throw new ExpressionException("(In WriteHeapStm) Unknown variable!");

        Integer value = this.expr.evaluate(ps.peekStackSymTab(), ps.getHeap());
        ps.getHeap().putValue(address, value);

        return null;
    }

    @Override
    public String toString() {
        return "writeHeap(" + id + ", " + expr + ")";
    }

}
