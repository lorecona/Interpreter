package Model.Statement;

import Model.Expression.*;
import Model.ProgramState;

public class SleepStm implements IStatement {

    private IExpression exp;

    public SleepStm(IExpression exp) { this.exp = exp;}

    @Override
    public ProgramState execute(ProgramState ps)
    {
        int number = exp.evaluate(ps.peekStackSymTab(), ps.getHeap());

        if(number!=0)
            ps.getExeStack().push(new SleepStm(new ConstExp(number - 1)));

        return null;
    }

    @Override
    public String toString() {
        return "Sleep("+exp+")";
    }
}
