package Commands;

import java.io.FileNotFoundException;
public abstract class AbstractCommand implements Command {
    private Object arguments;
    public Object getArguments() {
        return arguments;
    }
    public void setArguments(Object arguments) {
        this.arguments = arguments;
    }
    public abstract void execute(String arg) throws FileNotFoundException;
}