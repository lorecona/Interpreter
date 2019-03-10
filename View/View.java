package View;

import Controller.Controller;
import Model.Expression.*;
import Model.ProgramState;
import Model.Statement.*;
import Model.Utils.MyDictionary;
import Model.Utils.MyList;
import Model.Utils.MyStack;
import Repository.*;
import org.omg.CosNaming.IstringHelper;

import java.util.Scanner;
import java.io.BufferedReader;

public class View {
    public static void main(String[] args) {

        MyList<IStatement> statements= makeStm();
        showMenu(statements);

    }

    static MyList<IStatement> makeStm()
    {
        //1. v=2; print(v)
        IStatement statement1 = new CompoundStm(new AssignmentStm("v",new ConstExp(2)), new PrintStm(new VarExp("v")));

        //2. a=2+3*5; b=a+1; print(b)
        IStatement statement2 = new CompoundStm(new AssignmentStm("a", new ArithExp(new ConstExp(2),'+',new
                ArithExp(new ConstExp(3),'*' ,new ConstExp(5)))),
                new CompoundStm(new AssignmentStm("b",new ArithExp(new VarExp("a"),'+', new
                        ConstExp(1))), new PrintStm(new VarExp("b"))));

        //3. a=2-2; (if a then v=2 else v=3); print(v)
        IStatement statement3 = new CompoundStm(new AssignmentStm("a", new ArithExp( new ConstExp(2),'-', new
                ConstExp(1))), new CompoundStm(new IfStm(new VarExp("a"), new AssignmentStm("v", new ConstExp(2)), new
                AssignmentStm("v", new ConstExp(3))), new PrintStm(new VarExp("v"))));

        IStatement statement4 = new CompoundStm(new OpenRFileStm("var_f", "openfile.txt"),
                new CompoundStm(new ReadFileStm(new VarExp("var_f"), "var_c"),
                        new CompoundStm(new PrintStm(new VarExp("var_c")),
                                new CompoundStm(new IfStm(new VarExp("var_c"),
                                        new CompoundStm(new ReadFileStm(new VarExp("var_f"), "var_c"),
                                                new PrintStm(new VarExp("var_c"))),
                                        new PrintStm(new ConstExp(0))),
                                        new CloseRFileStm(new VarExp("var_f"))))));

                /*new CompoundStm(new OpenRFileStm("var_f", "openfile.txt"),
                                                new CompoundStm(new ReadFileStm(new VarExp("var_f"), "var_c"),
                                                        new CompoundStm( new PrintStm( new VarExp("var_c")),
                                                                new CompoundStm( new IfStm( new VarExp("var_c"),
                                                                        new CompoundStm( new ReadFileStm( new VarExp("var_f"), "var_c"),
                                                                                new PrintStm( new VarExp("var_c"))),
                                                                                    new PrintStm( new ConstExp(0))),
                                                                                        new CloseRFileStm(new VarExp("var_f"))))));*/


        IStatement statement5 = new CompoundStm(new OpenRFileStm("var_f", "openfile.txt"),
                new CompoundStm( new ReadFileStm(new ArithExp(new VarExp("var_f"), '+', new ConstExp(2)), "var_c"),
                        new CompoundStm(new PrintStm(new VarExp("var_c")),
                                new CompoundStm( new IfStm(new VarExp("var_c"),
                                        new CompoundStm(new ReadFileStm(new VarExp("var_f"), "var_c"),
                                                new PrintStm( new VarExp("var_c"))),
                                        new PrintStm(new ConstExp(0))),
                                        new CloseRFileStm(new VarExp("var_f"))))));

        IStatement statement6 = new CompoundStm(new AssignmentStm("v", new ConstExp(10)),
                                new CompoundStm(new NewStm("v", new ConstExp(20)),
                                    new CompoundStm( new NewStm("a", new ConstExp(22)),
                                        new CompoundStm(new WriteHeapStm("a", new ConstExp(30)),
                                                new CompoundStm(new PrintStm(new VarExp("a")),
                                                        new CompoundStm(new PrintStm(new ReadHeapExp("a")),
                                                                new AssignmentStm("a", new ConstExp(0))))))));

        IStatement statement7 = new CompoundStm(new AssignmentStm("v", new ArithExp(new ConstExp(10), '+',
                                    new BooleanExp(new ConstExp(2), "<", new ConstExp(6)))),
                                        new PrintStm(new VarExp("v")));


        IStatement statement8 = new CompoundStm(new AssignmentStm("v", new ConstExp(6)),
                                        new CompoundStm(new WhileStm(new ArithExp(new VarExp("v"), '-', new ConstExp(4)),
                                            new CompoundStm(new PrintStm(new VarExp("v")),
                                                new AssignmentStm("v", new ArithExp(new VarExp("v"), '-', new ConstExp(1))))),
                                                    new PrintStm(new VarExp("v"))));

        IStatement statement9 = new CompoundStm(new CompoundStm(new AssignmentStm("v", new ConstExp(10)),
                                    new NewStm("a", new ConstExp(22))),
                                        new CompoundStm(new ForkStm(new CompoundStm(
                                            new WriteHeapStm("a", new ConstExp(30)),
                                                 new CompoundStm(new AssignmentStm("v", new ConstExp(32)),
                                                    new CompoundStm(new PrintStm(new VarExp("v")),
                                                        new PrintStm(new ReadHeapExp("a")))))),
                                                            new CompoundStm(new PrintStm(new VarExp("v")),
                                                                    new PrintStm(new ReadHeapExp("a")))));

        MyList<IStatement> statements = new MyList<>();
        statements.add(statement1);
        statements.add(statement2);
        statements.add(statement3);
        statements.add(statement4);
        statements.add(statement5);
        statements.add(statement6);
        statements.add(statement7);
        statements.add(statement8);
        statements.add(statement9);

        return statements;
    }

    static void showMenu(MyList<IStatement> statements)
    {
        IRepo repository1 = new Repo();
        ProgramState program1 = new ProgramState(statements.get(0));
        repository1.add(program1);
        Controller controller1 = new Controller(repository1);

        IRepo repository2 = new Repo();
        ProgramState program2 = new ProgramState(statements.get(1));
        repository2.add(program2);
        Controller controller2 = new Controller(repository2);

        IRepo repository3 = new Repo();
        ProgramState program3 = new ProgramState(statements.get(2));
        repository3.add(program3);
        Controller controller3 = new Controller(repository3);

        IRepo repository4 = new Repo();
        ProgramState program4 = new ProgramState(statements.get(3));
        repository4.add(program4);
        Controller controller4 = new Controller(repository4);

        IRepo repository5 = new Repo();
        ProgramState program5 = new ProgramState(statements.get(4));
        repository5.add(program5);
        Controller controller5 = new Controller(repository5);

        IRepo repository6 = new Repo();
        ProgramState program6 = new ProgramState(statements.get(5));
        repository6.add(program6);
        Controller controller6 = new Controller(repository6);

        IRepo repository7 = new Repo();
        ProgramState program7 = new ProgramState(statements.get(6));
        repository7.add(program7);
        Controller controller7 = new Controller(repository7);

        IRepo repository8 = new Repo();
        ProgramState program8 = new ProgramState(statements.get(7));
        repository8.add(program8);
        Controller controller8 = new Controller(repository8);

        IRepo repository9 = new Repo();
        ProgramState program9 = new ProgramState(statements.get(8));
        repository9.add(program9);
        Controller controller9 = new Controller(repository9);


        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "Exit"));
        menu.addCommand(new RunExample("1", statements.get(0).toString(), controller1));
        menu.addCommand(new RunExample("2", statements.get(1).toString(), controller2));
        menu.addCommand(new RunExample("3", statements.get(2).toString(), controller3));
        menu.addCommand(new RunExample("4", statements.get(3).toString(), controller4));
        menu.addCommand(new RunExample("5", statements.get(4).toString(), controller5));
        menu.addCommand(new RunExample("6", statements.get(5).toString(), controller6));
        menu.addCommand(new RunExample("7", statements.get(6).toString(), controller7));
        menu.addCommand(new RunExample("8", statements.get(7).toString(), controller8));
        menu.addCommand(new RunExample("9", statements.get(8).toString(), controller9));


        menu.show();
    }
}
