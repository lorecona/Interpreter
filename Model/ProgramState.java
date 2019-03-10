package Model;

import Model.Statement.IStatement;
import Model.Utils.*;
import java.util.HashMap;
import java.io.BufferedReader;
import javafx.util.Pair;
import java.util.Stack;
import Exception.*;

public class ProgramState {
    private MyStack<IStatement> exeStack;
    private MyList<Integer> output;
    //private IDictionary<String, Integer> symTable;
    private Stack<IDictionary<String,Integer>>  symTable;
    private IStatement program;
    private MyDictionary<Integer, Pair<String, BufferedReader>> fileTable;
    private MyHeap<Integer> heap;
    private MyProcTable<String, Pair<MyList<String>, IStatement>> procTable;
    private int id;


    public ProgramState(IStatement program) {
        this.exeStack = new MyStack<>();
        this.output = new MyList<>();
        this.symTable = new Stack<>();
        this.fileTable = new MyDictionary<>();
        this.program = program;
        this.heap = new MyHeap<Integer>(new HashMap<Integer, Integer>());
        this.procTable = new MyProcTable<>();
        this.id = 1;

        this.symTable.push(new MyDictionary<String, Integer>());
        this.exeStack.push(program);
    }

    public ProgramState(IStatement program,
                        MyStack<IStatement> exeStack,
                        MyList<Integer> output,
                        Stack<IDictionary<String, Integer>> symTable,
                        MyDictionary<Integer, Pair<String, BufferedReader>> fileTable,
                        MyHeap<Integer> heap,
                        MyProcTable<String, Pair<MyList<String>, IStatement>> procTable,
                        int threadId)
    {
        this.program = program;
        this.exeStack = exeStack;
        this.output = output;
        this.symTable = symTable;
        this.fileTable = fileTable;
        this.heap = heap;
        this.procTable = procTable;
        this.id = threadId;

        this.exeStack.push(this.program);
    }

    public Stack<IDictionary<String, Integer>> getSymTable() {
        return symTable;
    }

    public void setSymTable(Stack<IDictionary<String, Integer>> symTable) {
        this.symTable = symTable;
    }

    public Stack<IDictionary<String, Integer>> copyStack(){
        Stack<IDictionary<String, Integer>> newStack = new Stack<IDictionary<String, Integer>>();
        for(IDictionary d : symTable)
            newStack.push(d.copy());

        return newStack;
    }

    public void pushStackSymTab(IDictionary x){
        symTable.add(x);
    }
    public IDictionary popStackSymTab(){
        return symTable.pop();
    }
    public IDictionary peekStackSymTab(){
        return symTable.peek();
    }


    public MyStack<IStatement> getExeStack() {
        return exeStack;
    }

    public void setExeStack(MyStack<IStatement> exeStack) {
        this.exeStack = exeStack;
    }

    public MyList<Integer> getOutput() { return output; }

    public void setOutput(MyList<Integer> output) {
        this.output = output;
    }


    public void setProgram(IStatement program) {
        this.program = program;
    }

    public IStatement getProgram() {
        return this.program;
    }

    public void setFileTable(MyDictionary<Integer, Pair<String, BufferedReader>> fileTable) {
        this.fileTable = fileTable;
    }

    public MyDictionary<Integer, Pair<String, BufferedReader>> getFileTable() {
        return this.fileTable;
    }

    public void setHeap(MyHeap<Integer> heap) {
        this.heap = heap;
    }

    public MyHeap<Integer> getHeap() {
        return this.heap;
    }

    public MyProcTable<String, Pair<MyList<String>, IStatement>> getProcTable() {return this.procTable;}

    public void setProcTable(MyProcTable<String, Pair<MyList<String>, IStatement>> procTable) { this.procTable = procTable;}

    public boolean isNotCompleted() {
        return !this.exeStack.isEmpty();
    }

    public ProgramState executeProgram()
    {

        if(this.exeStack.isEmpty())
            throw new ADTException("(In ProgramState) The stack is empty!");

        IStatement crtStm = this.exeStack.pop();
        try
        {
            return crtStm.execute(this);
        }
        catch(ADTException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public int getId() {
        return this.id;
    }

    public String toString(){
        String s = "";
        s += "in exeStack: \n";
        s += this.exeStack.toString();
        s += "\n in symTable:";
        s += this.symTable.toString();
        s += "\n in output:";
        s += this.output.toString();
        s += "\n in file table:";
        s += this.fileTable.toString();
        s += "\n in heap:";
        s += this.heap.toString();
        return s;
    }

}
