package common.Commands;

import common.Message.Request;
import common.Utility.CollectionManager;

/**
 * Класс реализует выполнение команды info
 * Вывод информации о коллекции
 * @author maria
 */
public class InfoCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    public InfoCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
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
    public String execute(Request request) {
        return collectionManager.getInfoAboutCollection();
    }
}
