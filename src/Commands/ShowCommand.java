package Commands;

import Utility.CollectionManager;
import Utility.ConsoleManager;
/**
 * Класс реализует выполнение команды show
 * Вывод всех элементов коллекции
 * @author maria
 */
public class ShowCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    public ShowCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.consoleManager = new ConsoleManager();
    }
    public ShowCommand(){}
    @Override
    public String getName() {
        return "show";
    }
    @Override
    public String getDescription() {
        return "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
    @Override
    public void execute(String arg) {
        if (checkArgument(getArguments())) {
            collectionManager.showCollection();
        }
    }
    @Override
    public boolean checkArgument(Object arguments) {
        if (arguments == null) {
            return true;
        } else {
            consoleManager.println("Комманда 'show' не имеет аргументов!");
            return false;
        }
    }
}
