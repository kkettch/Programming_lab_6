package Commands;

import Utility.CollectionManager;
import Utility.CommandManager;
import Utility.ConsoleManager;

public class HelpCommand extends AbstractCommand{
    private ConsoleManager consoleManager = new ConsoleManager();
    private CollectionManager collectionManager = new CollectionManager();
    private final CommandManager commandManager = new CommandManager();

    public HelpCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.consoleManager = new ConsoleManager();
    }

    public HelpCommand(){};

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "вывести справку по доступным командам";
    }

    @Override
    public void execute(String arg) {
        if (checkArgument(getArguments())) {
            for (AbstractCommand commandList : commandManager.getListOfCommand()) {
                consoleManager.println(commandList.getName() + ": " + commandList.getDescription());
            }
        }

    }

    @Override
    public boolean checkArgument(Object arguments) {
        if (arguments == null) {
            return true;
        } else {
            consoleManager.println("Комманда 'help' не имеет аргументов!");
            return false;
        }
    }
}
