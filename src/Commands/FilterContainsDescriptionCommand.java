package Commands;

import Utility.CollectionManager;
import Utility.ConsoleManager;

public class FilterContainsDescriptionCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;

    public FilterContainsDescriptionCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.consoleManager = new ConsoleManager();
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
    public void execute(String arg) {
        if (checkArgument(getArguments())) {
            String description = String.valueOf(getArguments());
            collectionManager.findElementsByDescription(description);
        }
    }

    @Override
    public boolean checkArgument(Object arguments) {
        if (arguments == null) {
            consoleManager.println("Укажите description, по которому нужно найти элемент в коллекции");
            return false;
        } else {
            return true;
        }
    }
}
