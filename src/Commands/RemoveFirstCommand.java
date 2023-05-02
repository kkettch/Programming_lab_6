package Commands;

import Utility.CollectionManager;
import Utility.ConsoleManager;
/**
 * Класс реализует выполнение команды remove_first
 * Удаление первого элемента коллекции
 * @author maria
 */
public class RemoveFirstCommand extends AbstractCommand {
    ConsoleManager consoleManager = new ConsoleManager();
    CollectionManager collectionManager = new CollectionManager();
    public RemoveFirstCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.consoleManager = new ConsoleManager();
    }
    public RemoveFirstCommand(){}
    @Override
    public String getName() {
        return "remove_first";
    }
    @Override
    public String getDescription() {
        return "удалить первый элемент из коллекции";
    }
    @Override
    public void execute(String arg) {
        if (checkArgument(getArguments())) {
            collectionManager.removeFirstElement();
        }
    }
    @Override
    public boolean checkArgument(Object arguments) {
        if (arguments == null) {
            return true;
        } else {
            consoleManager.println("Комманда 'remove_first' не имеет аргументов!");
            return false;
        }
    }
}