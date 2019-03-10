package Model.Statement;

import Model.Expression.IExpression;
import Model.Expression.NotExp;
import Model.ProgramState;
import Model.Utils.MyStack;

public class RepeatStm implements IStatement {

    private IStatement s;
    private IExpression exp;

    public RepeatStm(IStatement s, IExpression exp) { this.s = s; this.exp = exp;}

    @Override
    public ProgramState execute(ProgramState ps)
    {
        IStatement t = new WhileStm(new NotExp(exp), s);
        ps.getExeStack().push(new CompoundStm(s, t));

        return null;
    }

    @Override
    public String toString(){
        return "RepeatStm REPEAT(" + s + ") UNTIL(" + exp + ")";
    }
}
