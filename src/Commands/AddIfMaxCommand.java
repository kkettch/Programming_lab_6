package Commands;

import MusicBand.MusicBand;
import Utility.CollectionManager;
import Utility.ConsoleManager;
import Utility.CreatorOfMusicBand;

public class AddIfMaxCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    private CreatorOfMusicBand creatorOfMusicBand;

    public AddIfMaxCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.consoleManager = new ConsoleManager();
        this.creatorOfMusicBand = new CreatorOfMusicBand(collectionManager);
    }

    public AddIfMaxCommand(){}


    @Override
    public String getName() {
        return "add_if_max";
    }

    @Override
    public String getDescription() {
        return "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }

    @Override
    public void execute(String arg) {
        if (checkArgument(getArguments())) {
            MusicBand musicBand = new MusicBand();
            creatorOfMusicBand.setMusicBandByUser(musicBand);
            if (musicBand.getNumberOfParticipants() > collectionManager.findMaxNumberOfParticipants()) {
                collectionManager.addElement(musicBand);
                consoleManager.println("Элемент был успешно добавлен в коллекцию");
            } else {
                consoleManager.println("Элемент не был добавлен в коллекцию");
            }
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
