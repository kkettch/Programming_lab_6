package common.Commands;

import common.Message.Request;
import common.Utility.CollectionManager;
/**
 * Класс реализует выполнение команды remove_first
 * Удаление первого элемента коллекции
 * @author maria
 */
public class RemoveFirstCommand extends AbstractCommand {
    CollectionManager collectionManager = new CollectionManager();
    public RemoveFirstCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
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
    public String execute(Request request) {
        return collectionManager.removeFirstElement();
    }
}