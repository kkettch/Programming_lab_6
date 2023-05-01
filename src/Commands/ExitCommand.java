package Commands;

import Utility.CollectionManager;
import Utility.ConsoleManager;

public class ExitCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;

    public ExitCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.consoleManager = new ConsoleManager();
    }

    public ExitCommand(){}


    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "завершить программу (без сохранения в файл)";
    }

    @Override
    public boolean checkArgument(Object arguments) {
        if (arguments == null) {
            return true;
        } else {
            consoleManager.println("Комманда 'exit' не имеет аргументов!");
            return false;
        }
    }

    @Override
    public void execute(String arg) {
        if (checkArgument(getArguments())) {
            consoleManager.println("Работа приложения завершена");
            consoleManager.exit();
        }
    }
}
