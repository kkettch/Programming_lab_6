package common.Commands;

import common.Message.Request;
import common.MusicBand.MusicBand;
import common.Utility.CollectionManager;
import common.Utility.ConsoleManager;
import common.Utility.CreatorOfMusicBand;
/**
 * Класс содержит реализацию команды add_if_max
 * Добавление нового элемента в коллекцию, если он больше наибольшего элемента в коллекции
 * @author maria
 */
public class AddIfMaxCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    public AddIfMaxCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    public AddIfMaxCommand(){}
    @Override
    public String getName() {
        return "add_if_max";
    }
    @Override
    public String getDescription() {
        return "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции (сравнение по полю - количество участников)";
    }
    @Override
    public String execute(Request request) {
            MusicBand musicBand = request.getMusicBand();
            int maxNumberOfParticipants = collectionManager.findMaxNumberOfParticipants();
            int clientNumberOfParticipants = musicBand.getNumberOfParticipants();
            if (clientNumberOfParticipants > maxNumberOfParticipants) {
                collectionManager.addElement(musicBand); //добавление элемента если его количество участников максимально
                return "Элемент был успешно добавлен в коллекцию";
            } else {
                return "Элемент не был добавлен в коллекцию. Введеное количество участников: " + clientNumberOfParticipants + ". Максимальное количество участников в коллекции: " + maxNumberOfParticipants;
            }
    }
}