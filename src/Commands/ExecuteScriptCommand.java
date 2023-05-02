package Commands;

import Utility.CollectionManager;
import Utility.CommandManager;
import Utility.ConsoleManager;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

/**
 * Класс содержит реализацию команды execute_script
 * Чтение и выполнение команд из файла
 * @author maria
 */
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
                HashMap<String, AbstractCommand> executeMap = commandManager.getCommandHashMap(collectionManager, fileNameList);
                while (!Objects.equals(array, "exit")) {
                    commandManager.scriptExecute(scanner, fileNameList, executeMap);
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
                if (arg != null && Paths.get(arg).toFile().exists()) {
                    consoleManager.println("Нет прав на чтение файла!");
                }
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
