package Model.Statement;

import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.IDictionary;
import Model.Utils.MyDictionary;

import java.util.ArrayList;

public class CallStm implements IStatement {

    private String name;
    private ArrayList<IExpression> exp = new ArrayList<>();

    public CallStm(String name, ArrayList<IExpression> exp) {
        this.name = name;
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState ps) {

        if(!ps.getProcTable().contains(name))
            throw new RuntimeException("Procedure " + name + "is not in our ProcTable");

        ArrayList<Integer> evaluated = new ArrayList<>();
        for(IExpression e : this.exp)
            evaluated.add(e.evaluate(ps.peekStackSymTab(), ps.getHeap()));

        IDictionary<String, Integer> symTab = new MyDictionary<>();

        for(int i = 0; i < ps.getProcTable().get(name).getKey().size(); i++)
            symTab.put(ps.getProcTable().get(name).getKey().get(i), evaluated.get(i));

        ps.getSymTable().push(symTab);
        ps.getExeStack().push(new ReturnStm());
        ps.getExeStack().push(ps.getProcTable().get(name).getValue());

        return null;
    }

    @Override
    public String toString(){
        String x = new String();
        x+="call("+ name +" ; ";
        for(IExpression e : exp)
            x += " "+e;

        return x;
    }
}
