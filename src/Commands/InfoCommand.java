package Commands;

import Utility.CollectionManager;
import Utility.ConsoleManager;

/**
 * Класс реализует выполнение команды info
 * Вывод информации о коллекции
 * @author maria
 */
public class InfoCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    public InfoCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.consoleManager = new ConsoleManager();
    }
    public InfoCommand(){}
    @Override
    public String getName() {
        return "info";
    }
    @Override
    public String getDescription() {
        return "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
    @Override
    public void execute(String arg) {
        if (checkArgument(getArguments())) {
            collectionManager.getInfoAboutCollection();
        }
    }
    @Override
    public boolean checkArgument(Object arguments) {
        if (arguments == null) {
            return true;
        } else {
            consoleManager.println("Комманда 'info' не имеет аргументов!");
            return false;
        }
    }
}
