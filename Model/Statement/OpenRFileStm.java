package Model.Statement;

import Model.ProgramState;
import Model.Utils.*;
import javafx.util.Pair;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import Exception.FileException;

public class OpenRFileStm implements IStatement {

    private String fileId;
    private String fileName;
    private static int fd = 1;

    public OpenRFileStm(String fileId, String fileName) {
        this.fileId = fileId;
        this.fileName = fileName;
    }

    @Override
    public ProgramState execute(ProgramState ps)
    {
        IDictionary<String, Integer> symTable = ps.peekStackSymTab();
        MyDictionary<Integer, Pair<String, BufferedReader>> fileTable = ps.getFileTable();

        for(Pair<String, BufferedReader> file : ps.getFileTable().values())
        {
            if (file.getKey().equals(this.fileName)) {
                throw new FileException("(In OpenRFile) File already opened!");
            }
        }

        File file = new File("C:\\Users\\lorry\\Desktop\\926\\src\\" + this.fileName);
        if (!file.exists()) {
            throw new FileException("File not found!");
        }

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\lorry\\Desktop\\926\\src\\" + this.fileName));
            Pair<String, BufferedReader> pair = new Pair<>(this.fileName, reader);
            fileTable.put(fd, pair);
            symTable.put(this.fileId, fd);
            fd++;

        }
        catch(FileNotFoundException e)
        {
            System.out.println(e.toString());
        }

        return null;
    }

    @Override
    public String toString() {
        return "openRFile(" + this.fileId + ", " + this.fileName + ")";
    }
}
