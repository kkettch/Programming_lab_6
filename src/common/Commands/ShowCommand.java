package common.Commands;

import common.Message.Request;
import common.Utility.CollectionManager;
/**
 * Класс реализует выполнение команды show
 * Вывод всех элементов коллекции
 * @author maria
 */
public class ShowCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    public ShowCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
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
    public String execute(Request request) {
        return collectionManager.showCollection(); //вызываем метод по выводу коллекции клиенту
    }
}
