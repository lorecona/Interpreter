package Model.Statement;

import Model.Utils.*;
import Model.Expression.IExpression;
import Model.ProgramState;
import Exception.FileException;

import javafx.util.Pair;
import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFileStm implements IStatement{

    IExpression expFid;

    public CloseRFileStm(IExpression expFid) { this.expFid = expFid;}

    @Override
    public ProgramState execute(ProgramState ps) {
        IDictionary<String, Integer> symTable = ps.peekStackSymTab();
        MyDictionary<Integer, Pair<String, BufferedReader>> fileTable = ps.getFileTable();
        MyHeap<Integer> heap = ps.getHeap();

        int fd = expFid.evaluate(symTable, heap);
        BufferedReader reader = fileTable.get(fd).getValue();

        if(reader == null)
            throw new FileException("(In CloseRFile) File descriptor not found!");

        try {
            reader.close();
            fileTable.remove(fd);
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        return null;
    }

    @Override
    public String toString() {
        return  "closeRFileStmt(" + this.expFid.toString() + ")";
    }
}
