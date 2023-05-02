import FileManager.FileManager;
import Utility.CollectionManager;
import Utility.CommandManager;
import java.io.IOException;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        FileManager fileManagerReader = new FileManager(args[0]);
        CollectionManager collectionManager = new CollectionManager(fileManagerReader.readFromFile());
        List<String> fileNameList = new ArrayList<>();
        CommandManager commandManager = new CommandManager(collectionManager, fileManagerReader, fileNameList);
        commandManager.commandExecute();
    }
}