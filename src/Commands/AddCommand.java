package Commands;

import MusicBand.MusicBand;
import Utility.CollectionManager;
import Utility.ConsoleManager;
import Utility.CreatorOfMusicBand;

public class AddCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    private CreatorOfMusicBand creatorOfMusicBand;

    public AddCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.consoleManager = new ConsoleManager();
        this.creatorOfMusicBand = new CreatorOfMusicBand(collectionManager);
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
    public void execute(String arg) {
        if (checkArgument(getArguments())) {
            MusicBand musicBand = new MusicBand();
            creatorOfMusicBand.setMusicBandByUser(musicBand);
            collectionManager.addElement(musicBand);
            consoleManager.println("Элемент был успешно добавлен в коллекцию");
        }

    }

    @Override
    public boolean checkArgument(Object arguments) {
        if (arguments == null) {
            return true;
        } else {
            consoleManager.println("Некорректный ввод команды, не указывайте аргументы ");
            return false;
        }
    }
}
