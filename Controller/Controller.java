package Controller;

import Model.Statement.IStatement;
import Model.Utils.*;
import Repository.IRepo;
import Model.ProgramState;
import Exception.*;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller {

    private IRepo repo;
    private ExecutorService executor;

    public Controller(IRepo repo)
    {
        this.repo = repo;
    }

    public void oneStep2() {

        this.executor = Executors.newFixedThreadPool(2);

        List<ProgramState> programList = removeCompletedPrg(this.repo.getProgramStates());


        programList.forEach(this.repo::logPrgStateExec);

        if (programList.size() > 0) {

            this.oneStep(programList);

        }

        this.executor.shutdownNow();

    }

    public void allSteps()  {
        this.executor = Executors.newFixedThreadPool(2);

        List<ProgramState> programStates = this.removeCompletedPrg(this.repo.getProgramStates());
        programStates.forEach(this.repo::logPrgStateExec);


        while(programStates.size() > 0) {
            this.oneStep(programStates);
            programStates = this.removeCompletedPrg(this.repo.getProgramStates());
        }

        this.executor.shutdownNow();

        this.repo.getProgramStates().forEach(
                (ps) -> {
                    ps.getFileTable().values()
                            .forEach((e) -> {
                                try {
                                    e.getValue().close();
                                } catch (java.io.IOException ex) {
                                    System.out.println(ex.toString());
                                }
                            });
                }
        );

        this.repo.setProgramStates(programStates);
    }

    public void oneStep(List<ProgramState> programStates) {
        programStates.forEach(this.repo::logPrgStateExec);

        List<Callable<ProgramState>> callList = programStates.stream()
                .map((ProgramState p) -> (Callable<ProgramState>)(p::executeProgram))
                .collect(Collectors.toList());
        try {
            List<ProgramState> newPrgList = executor.invokeAll(callList).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                        }
                        return null;
                    }).filter(Objects::nonNull).collect(Collectors.toList());
            programStates.addAll(newPrgList);
        } catch (java.lang.InterruptedException e) {
            System.out.println(e.toString());
        }


        programStates.forEach(this.repo::logPrgStateExec);
        this.repo.setProgramStates(programStates);

    }


    public List<ProgramState> removeCompletedPrg(List<ProgramState> inPrgList) {
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    public Map<Integer,Integer> conservativeGarbageCollector(Collection<Integer> symTableValues, Map<Integer, Integer> heap) {
        return heap.entrySet()
                .stream()
                .filter(e->symTableValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public IRepo getRepo() {
        return this.repo;
    }

}
