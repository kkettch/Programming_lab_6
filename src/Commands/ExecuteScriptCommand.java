package Commands;

import Exceptions.InvalidInputException;
import Exceptions.RecursionException;
import Utility.CollectionManager;
import Utility.CommandManager;
import Utility.ConsoleManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class ExecuteScriptCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    private CommandManager commandManager;
    private List<String> fileNameList;

    public ExecuteScriptCommand(CollectionManager collectionManager,List<String> fileNameList){
        this.collectionManager = collectionManager;
        this.commandManager = new CommandManager();
        this.consoleManager = new ConsoleManager();
        this.fileNameList = fileNameList;
    }
    public ExecuteScriptCommand(){}
    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getDescription() {
        return "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме";
    }

    @Override
    public void execute(String arg){
        if (checkArgument(getArguments())) {
            File file = new File(getArguments().toString());
            try (Scanner scanner = new Scanner(file)) {
                if (!scanner.hasNextLine()) throw new NoSuchElementException();
                String[] array = {"", ""};
                String line;
                HashMap<String, AbstractCommand> executeMap = commandManager.getCommandHashMap(collectionManager, fileNameList);
                while (!Objects.equals(array, "exit")) {
                    while (scanner.hasNextLine()) {
                        line = scanner.nextLine().trim();
                        if (line.isEmpty()) {
                            continue;
                        }
                        array = line.split(" ");
                        if (array.length > 2) throw new InvalidInputException("Введено неверное количество слов");
                        if (array.length == 2 && array[0].equals(array[1]))
                            throw new InvalidInputException("Комманда введена дважды");
                        if (executeMap.get(array[0]) == null) {
                            consoleManager.println("Команда не существует");
                        } else {
                            if (array.length == 1) {
                                arg = null;
                            } else {
                                arg = array[1];
                            }
                            if (array[0].equals("execute_script")) {
                                if (fileNameList.contains(arg)) {
                                    throw new RecursionException();

                                } else{
                                    fileNameList.add(arg);
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
                    if (!scanner.hasNextLine()) {
                        break;
                    }
                    if(scanner.nextLine().isEmpty()){
                        break;
                    }
                }
            } catch (NoSuchElementException msg) {
                consoleManager.println("Файл пустой!");
            } catch (IOException e) {
                if(Paths.get(arg).toFile().exists()){
                    consoleManager.println("Нет прав на чтение файла!");
                }
            else {
                    consoleManager.println("Файл с данным именем не найден!");
                }
            } catch (InvalidInputException msg) {
                consoleManager.println(msg.getMessage());
            } catch (RecursionException msg) {
                consoleManager.println("Рекурсия в файле!");
                fileNameList.clear();
            }finally {
                fileNameList.clear();
            }
        }
    }

    @Override
    public boolean checkArgument(Object arguments) {
        if (arguments == null) {
            consoleManager.println("Укажите файл, из которого нужно выполнить команды!");

            return false;
        } else {
            return true;
        }
    }
}
