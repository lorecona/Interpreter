package View;

import Controller.Controller;

public class RunExample extends Command {

    private Controller ctrl;

    public RunExample(String key, String description, Controller ctrl)
    {
        super(key, description);
        this.ctrl = ctrl;
    }

    @Override
    public void execute()
    {
        this.ctrl.allSteps();

    }
}
