package Commands;

import FileManager.FileManager;
import Utility.CollectionManager;
import Utility.ConsoleManager;

public class SaveCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    private FileManager fileManager;

    public SaveCommand(CollectionManager collectionManager, FileManager fileManager){
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
        this.consoleManager = new ConsoleManager();
    }
    public SaveCommand(){}
    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescription() {
        return "сохранить коллекцию в файл";
    }

    @Override
    public void execute(String arg) {
        if (checkArgument(getArguments())) {
            fileManager.writeCollection(collectionManager.getMusicBandVector(), fileManager.getFileName());;
        }
    }

    @Override
    public boolean checkArgument(Object arguments) {
        if (arguments == null) {
            return true;
        } else {
            consoleManager.println("Комманда 'show' не имеет аргументов!");
            return false;
        }
    }
}
