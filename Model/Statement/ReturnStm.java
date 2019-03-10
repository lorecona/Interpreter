package Model.Statement;

import Model.ProgramState;

public class ReturnStm implements IStatement {

    @Override
    public ProgramState execute(ProgramState ps) {
        ps.getSymTable().pop();
        return null;
    }

    @Override
    public String toString(){
        return "return()";
    }
}
