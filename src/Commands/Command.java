package Commands;

import java.io.FileNotFoundException;

public interface Command {
    String getName();
    String getDescription();
    void execute(String arg) throws FileNotFoundException;
    boolean checkArgument(Object arguments);
}
