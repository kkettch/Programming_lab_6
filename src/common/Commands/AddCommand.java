package common.Commands;

import common.Message.Request;
import common.MusicBand.MusicBand;
import common.Utility.CollectionManager;
import common.Utility.ConsoleManager;
import common.Utility.CreatorOfMusicBand;

/**
 * Класс содержит реализацию команды add
 * Добавление нового элемента в коллекцию
 * @author maria
 */
public class AddCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    public AddCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    public AddCommand(){}
    @Override
    public String getName() {
        return "add";
    }
    @Override
    public String getDescription() {
        return "добавить новый элемент в коллекцию";
    }
    @Override
    public String execute(Request request) {
        MusicBand musicBand = request.getMusicBand(); //получаем отправленный клиентом объекта
        musicBand.setId(collectionManager.generateID()); //устанавливаем уникальное значение id
        collectionManager.addElement(musicBand); //вызываем метод для добавления в коллекцию
        return "Элемент был успешно добавлен в коллекцию"; //сообщаем об успешном добавлении клиенту
    }
}