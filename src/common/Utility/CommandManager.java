package common.Utility;
import common.Commands.*;
import common.Exceptions.*;
import common.FileManager.FileManager;
import common.Message.Request;
import common.Message.Response;
import common.MusicBand.MusicBand;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    private final CreatorOfMusicBand creatorOfMusicBand = new CreatorOfMusicBand();
    public CommandManager(CollectionManager collectionManager, FileManager fileManagerReader, List<String> fileNameList) {
        this.fileManagerReader = fileManagerReader;
        commandHashMap = getCommandHashMap(collectionManager, fileNameList);
    }
    public CommandManager() {
        listOfCommand = new ArrayList<>();
//        listOfCommand.add(new ExitCommand());
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
//        listOfCommand.add(new SaveCommand());
//        listOfCommand.add(new ExecuteScriptCommand());
    }
    public List<String> commandWithoutArguments() {
        ArrayList<String> listOfCommandWithoutArguments = new ArrayList<>();
        listOfCommandWithoutArguments.add("help");
        listOfCommandWithoutArguments.add("info");
        listOfCommandWithoutArguments.add("show");
        listOfCommandWithoutArguments.add("clear");
        listOfCommandWithoutArguments.add("average_of_number_of_participants");
        listOfCommandWithoutArguments.add("print_field_descending_genre");
        listOfCommandWithoutArguments.add("remove_first");
        return listOfCommandWithoutArguments;
    }

    public List<String> commandWithStringArguments() {
        ArrayList<String> listOfCommandWithArguments = new ArrayList<>();
        listOfCommandWithArguments.add("execute_script");
        listOfCommandWithArguments.add("filter_contains_description");
        return listOfCommandWithArguments;
    }

    public List<String> commandWithNumberArguments() {
        ArrayList<String> listOfCommandWithNumberArguments = new ArrayList<>();
        listOfCommandWithNumberArguments.add("remove_by_id");
        return listOfCommandWithNumberArguments;
    }
    public List<String> commandWithObject() {
        ArrayList<String> listOfCommandWithObject = new ArrayList<>();
        listOfCommandWithObject.add("update");
        listOfCommandWithObject.add("add");
        listOfCommandWithObject.add("add_if_max");
        listOfCommandWithObject.add("remove_lower");
        return listOfCommandWithObject;
    }
    public List<AbstractCommand> getListOfCommand() {
        return listOfCommand;
    }

    public Request checkClientInput(String[] userInput, ObjectOutputStream objectOut, ObjectInputStream objectIn) {
        String commandName = userInput[0];
        try {
            if (userInput.length > 2) throw new InvalidInputException();  //проверка, что введено не более 2 слов
            if (commandWithoutArguments().contains(commandName)) { //проверка что введена команда без аргументов
                if (userInput.length == 1) return new Request(commandName, null, null, false);
                else throw new InvalidInputException();
            }
            if (commandWithStringArguments().contains(commandName)) { //проверка, что введена команда со строковым аргументом
                if (userInput.length == 2) return new Request(commandName, userInput[1], null, false);
                else throw new InvalidInputException();
            }
            if (commandWithNumberArguments().contains(commandName)) { //проверка, что введена команда с числовым аргументом
                if (userInput.length == 2) {
                    String argument = userInput[1];
                    if (checkNumber(argument)) {
                        if (checkId("check_id", argument, objectOut, objectIn)) return new Request(commandName, argument, null, false);
                        else {
                            consoleManager.println("Введеное id не существует в коллекции");
                            return null;
                        }
                    }
                } else throw new InvalidInputException();
            }
            if (commandWithObject().contains(commandName)) { //проверка на команду с вводом объекта
                if (!commandName.equals("update") && userInput.length > 1) {
                    throw new InvalidInputException();
                }
                MusicBand musicBand = new MusicBand();
                String argument;
                if (userInput.length == 2) {
                    argument = userInput[1];
                    if (!checkNumber(argument)) throw new InvalidInputException();
                    else if (!checkId("check_id", argument, objectOut, objectIn)) {
                        consoleManager.println("Указаного id не существует в коллекции! Попробуйте снова");
                        return null;
                    }
                    String passportID = checkPassportID(commandName, objectOut, objectIn); //проверяем пасспортID
                    creatorOfMusicBand.setMusicBandOnClient(musicBand, passportID);
                    return new Request(commandName, argument, musicBand, false);
                }
                if (userInput.length == 1 && !commandName.equals("update")) {
                    String passportID = checkPassportID(commandName, objectOut, objectIn); //проверяем пасспортID
                    creatorOfMusicBand.setMusicBandOnClient(musicBand, passportID);
                    return new Request(commandName, null, musicBand, false);
                } else throw new InvalidInputException();
            } else throw new InvalidInputException();
        } catch (InvalidInputException msg) {
            consoleManager.println("Команда введена в неверном формате! Для получение информации о корректном вводе команды введите help");
        } return null;
    }
    public boolean checkId(String commandName, String argument, ObjectOutputStream objectOut, ObjectInputStream objectIn) {
        try {
            objectOut.writeObject(new Request(commandName, argument, null, true));
            objectOut.flush();
            objectOut.reset();
            Response response = (Response) objectIn.readObject();
            if (response.getDescription().equals("true")) {
                return true;
            } else {
                return false;
            }
        } catch (IOException | ClassNotFoundException e) {
            consoleManager.println("Произошла ошибка при отправке или получении объекта с сервером");
        }
        return false;
    }

    public String checkPassportID(String commandName, ObjectOutputStream objectOut, ObjectInputStream objectIn) {
        String passportID;
        while(true) {
            consoleManager.print("Введите passportID солиста группы, длинной не более 21 символа (опционально): ");
            passportID = consoleManager.getString().trim();
            if (passportID.isEmpty()) {
                passportID = null;
                break;
            } else {
                while (true) {
                    try {
                        objectOut.writeObject(new Request(commandName, passportID, null, true));
                        objectOut.flush();
                        objectOut.reset();
                        Response response = (Response) objectIn.readObject();
                        if (response.getDescription().equals("true")) {
                            break;
                        } else {
                            consoleManager.println(response.getDescription());
                            consoleManager.print("Введите passportID солиста группы, длинной не более 21 символа (опционально): ");
                            passportID = consoleManager.getString().trim();
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        consoleManager.println("Произошла ошибка при отправке или получении объекта с сервером");
                    }
                }
                break;
            }
        } return passportID;
    }

    public boolean checkNumber(String arguments) {
        try {
            Integer.parseInt(arguments);
            return true;
        } catch (NumberFormatException msg) {
            return false;
        }
    }

//    public Request checkInputForClient(String[] userInput, ObjectOutputStream objectOut, ObjectInputStream objectIn) throws IOException, ClassNotFoundException {
//        Request request = null;
//        String argument;
//        String command = userInput[0];
//        try {
//            if (!checkAmountOfWords(userInput)) throw new InvalidInputException("Введено неверное количество слов!");
//            if (commandWithoutArguments().contains(command)) {
//                if (userInput.length == 1) {
//                    return new Request(command, null, null, false);
//                } else {
//                    throw new InvalidInputException("Команда не имеет аргументов! Попробуйте снова!");
//                }
//            } else if (commandWithArguments().contains(command)) {
//                if (userInput.length == 2) {
//                    argument = userInput[1];
//                    return new Request(command, argument, null, false);
//                } else {
//                    throw new InvalidInputException("Команда должна иметь аргументы! Попробуйте снова!");
//                }
//            } else if (commandWithObject().contains(command)) {
//                if (userInput.length == 1) {
//                    MusicBand musicBand = new MusicBand();
//                    String passportID;
//                    while(true) {
//                        consoleManager.print("Введите passportID солиста группы, длинной не более 21 символа (опционально): ");
//                        passportID = consoleManager.getString().trim();
//                        if (passportID.isEmpty()) {
//                            passportID = null;
//                            break;
//                        } else {
//                            while(true) {
//                                objectOut.writeObject(new Request(passportID, null, null, true));
//                                objectOut.flush();
//                                objectOut.reset();
//                                Response response = (Response) objectIn.readObject();
//                                if (response.getDescription().equals("true")){
//                                    break;
//                                } else {
//                                    consoleManager.println(response.getDescription());
//                                    consoleManager.print("Введите passportID солиста группы, длинной не более 21 символа (опционально): ");
//                                    passportID = consoleManager.getString().trim();
//                                }
//                            }
//                            break;
//                        }
//                    }
//                    creatorOfMusicBand.setMusicBand(musicBand, passportID);
//                    return new Request(command, null, musicBand, false);
//                } else {
//                    throw new InvalidInputException("Команда не имеет аргуемнтов! Попробуйте снова!");
//                }
//            } else {
//                throw new InvalidInputException("Команда не существует");
//            }
//        }catch (InvalidInputException msg) {
//            consoleManager.println(msg.getMessage());
////                consoleManager.print("Введите команду: ");
//        }
//        return null;
//    }

    public String commandExecute(Request request) throws FileNotFoundException {
        String commandName = request.getCommandName();
        commandHashMap.get(commandName).setRequest(request);
        return commandHashMap.get(commandName).execute(request);
    }
//    public void scriptExecute(Scanner scanner, List<String> fileNameList, HashMap<String, AbstractCommand> executeMap) throws FileNotFoundException {
//        try {
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine().trim();
//                String[] array;
//                if (line.isEmpty()) {
//                    continue;
//                }
//                array = line.split(" ");
//                if (array.length > 2) throw new InvalidInputException("Введено неверное количество слов");
//                if (array.length == 2 && array[0].equals(array[1])) throw new InvalidInputException("Комманда введена дважды");
//                if (executeMap.get(array[0]) == null) {
//                    consoleManager.println("Команда не существует");
//                } else {
//                    if (array[0].equals("execute_script")) {
//                        if (fileNameList.contains(array[0])) {
//                            throw new RecursionException();
//                        } else{
//                            fileNameList.add(array[0]);
//                        }
//                    }
//                    if (array.length > 1) {
//                        consoleManager.println(executeMap.get(array[0]).getName() + " " + array[1] + ": ");
//                        executeMap.get(array[0]).setArguments(array[1]);
//                    } else {
//                        consoleManager.println(executeMap.get(array[0]).getName() + ": ");
//                        executeMap.get(array[0]).setArguments(null);
//                    }
//                    executeMap.get(array[0]).execute(array[0]);
//                }
//            }
//        } catch (InvalidInputException msg) {
//            consoleManager.println(msg.getMessage());
//        } catch (RecursionException msg) {
//            consoleManager.println("Рекурсия в файле!");
//            fileNameList.clear();
//        } finally {
//            fileNameList.clear();
//        }
//    }
    public HashMap<String, AbstractCommand> getCommandHashMap(CollectionManager collectionManager, List<String> fileNameList) {
        commandHashMap = new HashMap<>();
        commandHashMap.put("show", new ShowCommand(collectionManager));
//        commandHashMap.put("exit", new ExitCommand());
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
//        commandHashMap.put("save", new SaveCommand(collectionManager, fileManagerReader));
//        commandHashMap.put("execute_script", new ExecuteScriptCommand(collectionManager, fileNameList));
        return commandHashMap;
    }
}