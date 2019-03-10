package Repository;

import Model.ProgramState;
import java.util.List;

import java.io.IOException;

public interface IRepo {
    List<ProgramState> getProgramStates();
    void add(ProgramState ps);
    void logPrgStateExec(ProgramState ps);
    void setProgramStates(List<ProgramState> list);
}
