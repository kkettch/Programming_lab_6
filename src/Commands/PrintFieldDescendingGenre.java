package Commands;

import Utility.CollectionManager;
import Utility.ConsoleManager;

/**
 * Клас реализует выполнение команды filter_contains_description
 * Вывод поля genre всех элементов в порядке убывания
 * @author maria
 */
public class PrintFieldDescendingGenre extends AbstractCommand {
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    public PrintFieldDescendingGenre(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.consoleManager = new ConsoleManager();
    }
    public PrintFieldDescendingGenre(){}
    @Override
    public String getName() {
        return "print_field_descending_genre";
    }
    @Override
    public String getDescription() {
        return "вывести значения поля genre всех элементов в порядке убывания";
    }
    @Override
    public void execute(String arg) {
        if (checkArgument(getArguments())) {
            consoleManager.println(collectionManager.sortDescending());
        }
    }
    @Override
    public boolean checkArgument(Object arguments) {
        if (arguments == null) {
            return true;
        } else {
            consoleManager.println("Комманда 'print_field_descending_genre' не имеет аргументов!");
            return false;
        }
    }
}
