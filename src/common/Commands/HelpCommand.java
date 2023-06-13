package common.Commands;

import common.Message.Request;
import common.Utility.CommandManager;
import java.util.stream.Collectors;

/**
 * Класс содержит реализацию команды help
 * Вывод в консоль всех доступных команд
 * @author maria
 */
public class HelpCommand extends AbstractCommand{
    private final CommandManager commandManager = new CommandManager();
    @Override
    public String getName() {
        return "help";
    }
    @Override
    public String getDescription() {
        return "вывести справку по доступным командам";
    }
    @Override
    public String execute(Request request) {
        return commandManager.getListOfCommand().stream() //преобразование списка команд в поток элементов
                .map(commandList -> commandList.getName() + ": " + commandList.getDescription() + "\n") //преобразовываем каждую строку в формат (имя: описание)
                .collect(Collectors.joining()); //собираем все строки в одну строку
    }
}