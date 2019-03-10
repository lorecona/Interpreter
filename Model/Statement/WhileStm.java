package Model.Statement;

import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.MyStack;

public class WhileStm implements IStatement {

    private IExpression exp;
    private IStatement stm;

    public WhileStm(IExpression exp, IStatement stm) {
        this.exp = exp;
        this.stm = stm;
    }

    @Override
    public ProgramState execute(ProgramState ps) {
        MyStack<IStatement> exeStack = ps.getExeStack();
        int res = this.exp.evaluate(ps.peekStackSymTab(), ps.getHeap());

        if (res != 0) {
            exeStack.push(this);
            exeStack.push(stm);
        }

        return null;
    }

    @Override
    public String toString() {
        return "WhileStm  WHILE(" + this.exp + ") DO " + this.stm + " END";
    }
}
