package common.Commands;

import common.Message.Request;
import common.Utility.CollectionManager;

/**
 * Класс реализует выполнение команды remove_by_id
 * Удаление элемента по id
 * @author maria
 */
public class RemoveByIdCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    public RemoveByIdCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    public RemoveByIdCommand(){}
    @Override
    public String getName() {
        return "remove_by_id";
    }
    @Override
    public String getDescription() {
        return "удалить элемент из коллекции по его id";
    }
    @Override
    public String execute(Request request) {
        int id = Integer.parseInt(request.getArgument());
        return collectionManager.removeElementById(collectionManager.findElementById(id));
    }
}