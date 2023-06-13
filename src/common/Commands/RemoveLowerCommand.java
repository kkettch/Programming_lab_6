package common.Commands;

import common.Message.Request;
import common.Utility.CollectionManager;
/**
 * Класс реализует выполнение команды remove_lower
 * Удаление элементов меньше, чем заданный
 * @author maria
 */
public class RemoveLowerCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    public RemoveLowerCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    public RemoveLowerCommand(){}
    @Override
    public String getName() {
        return "remove_lower";
    }
    @Override
    public String getDescription() {
        return "удалить из коллекции все элементы, меньшие, чем заданный (сравнение по количеству участников)";
    }
    @Override
    public String execute(Request request) {
        int numberOfParticipants = request.getMusicBand().getNumberOfParticipants();
        return collectionManager.removeLowerByNumberOfParticipants(numberOfParticipants);
    }
}