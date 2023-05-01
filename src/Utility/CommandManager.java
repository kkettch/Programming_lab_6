package Utility;

import Commands.*;
import Exceptions.InvalidInputException;
import FileManager.FileManager;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CommandManager {

    private String[] arg;
    private HashMap<String, AbstractCommand> commandHashMap;
    private List<AbstractCommand> listOfCommand;
    private ConsoleManager consoleManager = new ConsoleManager();
    private FileManager fileManagerReader;


    public CommandManager(CollectionManager collectionManager, FileManager fileManagerReader,List<String> fileNameList) {
        this.fileManagerReader = fileManagerReader;
        commandHashMap = new HashMap<>();
        commandHashMap.put("show", new ShowCommand(collectionManager));
        commandHashMap.put("exit", new ExitCommand(collectionManager));
        commandHashMap.put("help", new HelpCommand(collectionManager));
        commandHashMap.put("clear", new ClearCommand(collectionManager));
        commandHashMap.put("remove_first", new RemoveFirstCommand(collectionManager));
        commandHashMap.put("average_of_number_of_participants", new AverageOfNumberOfParticipantsCommand(collectionManager));
        commandHashMap.put("info", new InfoCommand(collectionManager));
        commandHashMap.put("remove_by_id", new RemoveByIdCommand(collectionManager));
        commandHashMap.put("filter_contains_description", new FilterContainsDescriptionCommand(collectionManager));
        commandHashMap.put("add", new AddCommand(collectionManager));
        commandHashMap.put("remove_lower", new RemoveLowerCommand(collectionManager));
        commandHashMap.put("print_field_descending_genre", new PrintFieldDescendingGenre(collectionManager));
        commandHashMap.put("update", new UpdateIdCommand(collectionManager));
        commandHashMap.put("add_if_max", new AddIfMaxCommand(collectionManager));
        commandHashMap.put("save", new SaveCommand(collectionManager, fileManagerReader));
        commandHashMap.put("execute_script", new ExecuteScriptCommand(collectionManager, fileNameList));
    }

    public CommandManager() {
        listOfCommand = new ArrayList<>();
        listOfCommand.add(new ExitCommand());
        listOfCommand.add(new ShowCommand());
        listOfCommand.add(new ClearCommand());
        listOfCommand.add(new RemoveFirstCommand());
        listOfCommand.add(new AverageOfNumberOfParticipantsCommand());
        listOfCommand.add(new InfoCommand());
        listOfCommand.add(new RemoveByIdCommand());
        listOfCommand.add(new FilterContainsDescriptionCommand());
        listOfCommand.add(new AddCommand());
        listOfCommand.add(new RemoveLowerCommand());
        listOfCommand.add(new PrintFieldDescendingGenre());
        listOfCommand.add(new UpdateIdCommand());
        listOfCommand.add(new AddIfMaxCommand());
        listOfCommand.add(new SaveCommand());
        listOfCommand.add(new ExecuteScriptCommand());
    }

    public List<AbstractCommand> getListOfCommand() {
        return listOfCommand;
    }

    public void commandExecute() throws FileNotFoundException {
        consoleManager.print("Введите команду: ");
        String argument;
        while(consoleManager.ScHasNext()) {
            try{
                arg = consoleManager.gerString().trim().split("\\s");
                List<String> collection = new ArrayList<String>(Arrays.asList(arg));
                collection.removeAll(Arrays.asList("", null));
                arg = collection.toArray(new String[0]);
                if (arg.length > 2) throw new InvalidInputException("Введено неверное количество слов");
                if (arg.length == 2 && arg[0].equals(arg[1])) throw new InvalidInputException("Комманда введена дважды");
                if (commandHashMap.get(arg[0]) == null) {
                    consoleManager.println("Команда не существует");
                    consoleManager.print("Введите команду: ");
                } else {
                    if (arg.length == 1) {
                        argument = null;
                    } else {
                        argument = arg[1];
                    }
                    commandHashMap.get(arg[0]).setArguments(argument);
                    commandHashMap.get(arg[0]).execute(arg[0]);
                    consoleManager.print("Введите команду: ");
                }
            }catch (InvalidInputException msg) {
                consoleManager.println(msg.getMessage());
                consoleManager.print("Введите команду: ");
            }
        }
    }

    public HashMap<String, AbstractCommand> getCommandHashMap(CollectionManager collectionManager, List<String> fileNameList) {
        commandHashMap = new HashMap<>();
        commandHashMap.put("show", new ShowCommand(collectionManager));
        commandHashMap.put("exit", new ExitCommand(collectionManager));
        commandHashMap.put("help", new HelpCommand(collectionManager));
        commandHashMap.put("clear", new ClearCommand(collectionManager));
        commandHashMap.put("remove_first", new RemoveFirstCommand(collectionManager));
        commandHashMap.put("average_of_number_of_participants", new AverageOfNumberOfParticipantsCommand(collectionManager));
        commandHashMap.put("info", new InfoCommand(collectionManager));
        commandHashMap.put("remove_by_id", new RemoveByIdCommand(collectionManager));
        commandHashMap.put("filter_contains_description", new FilterContainsDescriptionCommand(collectionManager));
        commandHashMap.put("add", new AddCommand(collectionManager));
        commandHashMap.put("remove_lower", new RemoveLowerCommand(collectionManager));
        commandHashMap.put("print_field_descending_genre", new PrintFieldDescendingGenre(collectionManager));
        commandHashMap.put("update", new UpdateIdCommand(collectionManager));
        commandHashMap.put("add_if_max", new AddIfMaxCommand(collectionManager));
        commandHashMap.put("save", new SaveCommand(collectionManager, fileManagerReader));
        commandHashMap.put("execute_script", new ExecuteScriptCommand(collectionManager, fileNameList));
        return commandHashMap;
    }
}
