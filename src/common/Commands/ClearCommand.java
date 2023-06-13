package common.Commands;

import common.Message.Request;
import common.Utility.CollectionManager;
/**
 * Класс содержит реализацию команды clear
 * Очищение коллекции
 * @author maria
 */
public class ClearCommand extends AbstractCommand {
    CollectionManager collectionManager = new CollectionManager();
    public ClearCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    public ClearCommand(){}
    @Override
    public String getName() {
        return "clear";
    }
    @Override
    public String getDescription() {
        return "очистить коллекцию";
    }
    @Override
    public String execute(Request request) {
        return collectionManager.clearCollection(); //вызываем метод, очищающий коллекцию
    }
}