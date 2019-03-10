package Model.Statement;

import Model.Expression.IExpression;
import Model.ProgramState;

public class IfStm implements IStatement {
    private IExpression expression;
    private IStatement thenS;
    private IStatement elseS;

    public IfStm(IExpression expression, IStatement thenS, IStatement elseS)
    {
        this.expression = expression;
        this.elseS = elseS;
        this.thenS = thenS;
    }

    @Override
    public ProgramState execute(ProgramState ps) {

        int result = expression.evaluate(ps.peekStackSymTab(), ps.getHeap());
        if(result != 0)
            thenS.execute(ps);
        else
            elseS.execute(ps);

        return null;
    }

    @Override
    public String toString()
    {
        return "IfStatement IF( "+ expression.toString()+" ) THEN( " +thenS.toString() +" )ELSE( "+elseS.toString()+" )";
    }
}
