import common.FileManager.FileManager;
import common.Message.Request;
import common.Utility.CollectionManager;
import common.Utility.CommandManager;
import java.io.IOException;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        FileManager fileManagerReader = new FileManager(args[0]);
        CollectionManager collectionManager = new CollectionManager(fileManagerReader.readFromFile());
        List<String> fileNameList = new ArrayList<>();
        CommandManager commandManager = new CommandManager(collectionManager, fileManagerReader, fileNameList);
        Request request = new Request("show", "1", null, false);
        String show = commandManager.commandExecute(request);
        System.out.println(show);
    }
}