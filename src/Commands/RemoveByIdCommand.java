package Commands;

import Utility.CollectionManager;
import Utility.ConsoleManager;

public class RemoveByIdCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;

    public RemoveByIdCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.consoleManager = new ConsoleManager();
    }
    public RemoveByIdCommand(){}
    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getDescription() {
        return "удалить элемент из коллекции по его id";
    }

    @Override
    public void execute(String arg) {
        if (checkArgument(getArguments())) {
            try{
                int id = Integer.parseInt(getArguments().toString());
                collectionManager.removeElementById(collectionManager.findElementById(id));
            }catch (NumberFormatException msg) {
                consoleManager.println("Введите id типа данных Integer!");
            }

        }
    }

    @Override
    public boolean checkArgument(Object arguments) {
        if (arguments == null) {
            consoleManager.println("Укажите id, по которому нужно удалить элемент из коллекции");
            return false;
        } else {
            return true;
        }
    }
}
