package common.Commands;

import common.Message.Request;
import common.MusicBand.MusicBand;
import common.Utility.CollectionManager;
import common.Utility.ConsoleManager;
import common.Utility.CreatorOfMusicBand;
/**
 * Класс реализует выполнение команды update
 * Обновление элемента коллекции по id
 * @author maria
 */
public class UpdateIdCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    public UpdateIdCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    public UpdateIdCommand(){}
    @Override
    public String getName() {
        return "update";
    }
    @Override
    public String getDescription() {
        return "обновить значение элемента коллекции, id которого равен заданному";
    }
    @Override
    public String execute(Request request) {
        int id = Integer.parseInt(request.getArgument());
        MusicBand musicBand = request.getMusicBand();
        return collectionManager.updateElement(musicBand, id, collectionManager);
    }
}