package Commands;

import Utility.ConsoleManager;

/**
 * Класс содержит реализцаию команды exit
 * Завершение работы консольного приложения
 * @author maria
 */
public class ExitCommand extends AbstractCommand {
    private final ConsoleManager consoleManager;
    public ExitCommand(){
        this.consoleManager = new ConsoleManager();
    }
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