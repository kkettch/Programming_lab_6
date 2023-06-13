package common.Commands;

import common.Message.Request;
import common.Utility.CollectionManager;

/**
 * Клас реализует выполнение команды filter_contains_description
 * Вывод поля genre всех элементов в порядке убывания
 * @author maria
 */
public class PrintFieldDescendingGenre extends AbstractCommand {
    private CollectionManager collectionManager;
    public PrintFieldDescendingGenre(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
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
    public String execute(Request request) {
        return collectionManager.sortDescending(); //сортировка поля genre в порядке убывания
    }
}
