package Commands;

import Utility.CommandManager;
import Utility.ConsoleManager;

/**
 * Класс содержит реализацию команды help
 * Вывод в консоль всех доступных команд
 * @author maria
 */
public class HelpCommand extends AbstractCommand{
    private final ConsoleManager consoleManager;
    private final CommandManager commandManager = new CommandManager();
    public HelpCommand(){
        this.consoleManager = new ConsoleManager();
    }
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