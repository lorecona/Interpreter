package Model.Statement;

import Model.Expression.IExpression;
import Model.ProgramState;
import java.io.BufferedReader;
import java.io.IOException;

import javafx.util.Pair;
import Exception.FileException;
import Model.Utils.*;


public class ReadFileStm implements IStatement {

    private IExpression expFid;
    private String varName;

    public ReadFileStm(IExpression expFid, String varName) {
        this.expFid = expFid;
        this.varName = varName;
    }

    @Override
    public ProgramState execute(ProgramState ps)
    {
        IDictionary<String, Integer> symTable = ps.peekStackSymTab();
        MyDictionary<Integer, Pair<String, BufferedReader>> fileTable = ps.getFileTable();
        MyHeap<Integer> heap = ps.getHeap();

        int fd = this.expFid.evaluate(symTable, heap);
        BufferedReader reader = fileTable.get(fd).getValue();

        if (reader == null) {
            throw new FileException("(In ReadFileStm) File descriptor not found!!");
        }

        String line = "";
        try {
            line = reader.readLine();
        } catch (IOException e) { System.out.println(e.toString()); }


        int val = 0;
        if (line != null) {
            val = Integer.valueOf(line);
        }

        symTable.put(this.varName, val);

        return null;
    }

    @Override
    public String toString() {
        return "readFileStmt(" + this.expFid.toString() + ", " + this.varName + ")";
    }
}
