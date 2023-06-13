package common.Commands;

import common.Message.Request;
import common.Utility.CollectionManager;
/**
 * Класс содержит реализацию команды filter_contains_description
 * Нахождение элементов с совпадающим подстрокой из поля description
 * @author maria
 */
public class FilterContainsDescriptionCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    public FilterContainsDescriptionCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    public FilterContainsDescriptionCommand(){}
    @Override
    public String getName() {
        return "filter_contains_description";
    }
    @Override
    public String getDescription() {
        return "вывести элементы, значение поля description которых содержит заданную подстроку";
    }
    @Override
    public String execute(Request request) {
        return collectionManager.findElementsByDescription(request.getArgument()); //находим элементы с указаным описанием
    }
}