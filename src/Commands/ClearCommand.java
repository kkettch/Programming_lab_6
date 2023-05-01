package Commands;

import Utility.CollectionManager;
import Utility.ConsoleManager;

public class ClearCommand extends AbstractCommand {
    ConsoleManager consoleManager = new ConsoleManager();
    CollectionManager collectionManager = new CollectionManager();
    public ClearCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.consoleManager = new ConsoleManager();
    }
    public ClearCommand(){}

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "очистить коллекцию";
    }

    @Override
    public void execute(String arg) {
        if (checkArgument(getArguments())) {
            collectionManager.clearCollection();
        }
    }

    @Override
    public boolean checkArgument(Object arguments) {
        if (arguments == null) {
            return true;
        } else {
            consoleManager.println("Комманда 'clear' не имеет аргументов!");
            return false;
        }
    }
}
