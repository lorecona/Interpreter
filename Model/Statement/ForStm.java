package Model.Statement;

import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.MyStack;

public class ForStm implements IStatement{

    private IStatement s1, s2, s3;
    private IExpression exp;

    public ForStm(IStatement s1, IExpression exp, IStatement s2, IStatement s3) {
        this.s1 = s1;
        this.exp = exp;
        this.s2 = s2;
        this.s3 = s3;
    }

    @Override
    public ProgramState execute(ProgramState ps){

        IStatement t1 = new WhileStm(this.exp, new CompoundStm(this.s3, this.s2));
        IStatement t2 = new CompoundStm(s1, t1);

        ps.getExeStack().push(t2);
        return null;
    }

    @Override
    public String toString()
    {
        return "ForStm FOR(" + s1 + ";" + exp + ";" + s2 + ")"+ "{" + s3 + "}";
    }

}
