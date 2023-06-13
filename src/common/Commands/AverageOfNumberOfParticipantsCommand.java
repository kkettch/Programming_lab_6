package common.Commands;

import common.Message.Request;
import common.Utility.CollectionManager;

/**
 * Класс содержит реализацию команды average_of_number_of_participants
 * Вывод среднего значение поля numberOfParticipants
 * @author maria
 */
public class AverageOfNumberOfParticipantsCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    public AverageOfNumberOfParticipantsCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    public AverageOfNumberOfParticipantsCommand(){}
    @Override
    public String getName() {
        return "average_of_number_of_participants";
    }
    @Override
    public String getDescription() {
        return "вывести среднее значение поля numberOfParticipants для всех элементов коллекции";
    }
    @Override
    public String execute(Request request) {
        return collectionManager.getAverageNumberOfParticipants(); //возвращаем клиенту среднее число участников
    }
}
