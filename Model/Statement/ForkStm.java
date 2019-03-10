package Model.Statement;

import Model.ProgramState;
import Model.Utils.MyStack;

public class ForkStm implements IStatement{

    private IStatement stm;

    public ForkStm(IStatement stm) {
        this.stm = stm;
    }

    @Override
    public ProgramState execute(ProgramState ps)
    {
        return new ProgramState(this.stm, new MyStack<IStatement>(), ps.getOutput(), ps.copyStack(), ps.getFileTable(), ps.getHeap(), ps.getProcTable(), ps.getId()*10);
    }

    @Override
    public String toString() {
        return "FORK(" + this.stm + ")";
    }
}
