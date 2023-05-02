package Utility;
import Commands.*;
import Exceptions.InvalidInputException;
import Exceptions.RecursionException;
import FileManager.FileManager;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Реализация метода commandExecute для выполнения всех комманд
 * Получения списка всех комманд
 * Реализация метода scriptExecute для выполнения комманд из скрипта
 * @author maria
 */

public class CommandManager {
    private HashMap<String, AbstractCommand> commandHashMap;
    private List<AbstractCommand> listOfCommand;
    private final ConsoleManager consoleManager = new ConsoleManager();
    private FileManager fileManagerReader;
    public CommandManager(CollectionManager collectionManager, FileManager fileManagerReader, List<String> fileNameList) {
        this.fileManagerReader = fileManagerReader;
        commandHashMap = getCommandHashMap(collectionManager, fileNameList);
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
        while (consoleManager.ScHasNext()) {
            try {
                String[] arg = consoleManager.gerString().trim().split("\\s");
                List<String> collection = new ArrayList<>(Arrays.asList(arg));
                collection.removeAll(Arrays.asList("", null));
                arg = collection.toArray(new String[0]);
                if (arg.length > 2) throw new InvalidInputException("Введено неверное количество слов");
                if (arg.length == 2 && arg[0].equals(arg[1]))
                    throw new InvalidInputException("Комманда введена дважды");
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
            } catch (InvalidInputException msg) {
                consoleManager.println(msg.getMessage());
                consoleManager.print("Введите команду: ");
            }
        }
    }
    public void scriptExecute(Scanner scanner, List<String> fileNameList, HashMap<String, AbstractCommand> executeMap) throws FileNotFoundException {
        try {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                String[] array;
                if (line.isEmpty()) {
                    continue;
                }
                array = line.split(" ");
                if (array.length > 2) throw new InvalidInputException("Введено неверное количество слов");
                if (array.length == 2 && array[0].equals(array[1])) throw new InvalidInputException("Комманда введена дважды");
                if (executeMap.get(array[0]) == null) {
                    consoleManager.println("Команда не существует");
                } else {
                    if (array[0].equals("execute_script")) {
                        if (fileNameList.contains(array[0])) {
                            throw new RecursionException();
                        } else{
                            fileNameList.add(array[0]);
                        }
                    }
                    if (array.length > 1) {
                        consoleManager.println(executeMap.get(array[0]).getName() + " " + array[1] + ": ");
                        executeMap.get(array[0]).setArguments(array[1]);
                    } else {
                        consoleManager.println(executeMap.get(array[0]).getName() + ": ");
                        executeMap.get(array[0]).setArguments(null);
                    }
                    executeMap.get(array[0]).execute(array[0]);
                }
            }
        } catch (InvalidInputException msg) {
            consoleManager.println(msg.getMessage());
        } catch (RecursionException msg) {
            consoleManager.println("Рекурсия в файле!");
            fileNameList.clear();
        } finally {
            fileNameList.clear();
        }
    }
    public HashMap<String, AbstractCommand> getCommandHashMap(CollectionManager collectionManager, List<String> fileNameList) {
        commandHashMap = new HashMap<>();
        commandHashMap.put("show", new ShowCommand(collectionManager));
        commandHashMap.put("exit", new ExitCommand());
        commandHashMap.put("help", new HelpCommand());
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