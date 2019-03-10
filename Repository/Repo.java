package Repository;

import Model.ProgramState;
import Model.Utils.MyList;

import java.io.PrintWriter;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repo implements IRepo {

    private List<ProgramState> programStates;
    private String file;
    private PrintWriter printWriter;

    public Repo() {
        this.programStates = new ArrayList<>();
        this.file = "C:\\Users\\lorry\\Desktop\\926\\src\\file.txt";
    }

    @Override
    public void add(ProgramState ps) {
        programStates.add(ps);
    }

    @Override
    public void logPrgStateExec(ProgramState ps)
    {
        try{
            printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            printWriter.println("---------------------------------------------------------------");
            printWriter.println("|EXECUTION STACK|");
            printWriter.println(ps.getExeStack().toString());
            printWriter.println("|SYM TABLE|");
            printWriter.println(ps.getSymTable().toString());
            printWriter.println("|OUTPUT|");
            printWriter.println(ps.getOutput().toString());
            printWriter.println("|FILE TABLE|");
            printWriter.println(ps.getFileTable().toString());
            printWriter.println("|HEAP|");
            printWriter.println(ps.getHeap().toString());
            printWriter.println("\n");
            printWriter.close();
        }catch (java.io.IOException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public List<ProgramState> getProgramStates() {
        return this.programStates;
    }

    @Override
    public void setProgramStates(List<ProgramState> list) {
        this.programStates = list;
    }

}
