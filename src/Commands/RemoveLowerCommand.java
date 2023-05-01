package Commands;

import Utility.CollectionManager;
import Utility.ConsoleManager;

public class RemoveLowerCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;

    public RemoveLowerCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.consoleManager = new ConsoleManager();
    }
    public RemoveLowerCommand(){}
    @Override
    public String getName() {
        return "remove_lower";
    }

    @Override
    public String getDescription() {
        return "удалить из коллекции все элементы, меньшие, чем заданный (сравнение по количеству участников)";
    }

    @Override
    public void execute(String arg) {
        if (checkArgument(getArguments())) {
            int id = Integer.parseInt(getArguments().toString());
            if (collectionManager.findElementById(id) != null) {
                int numberOfParticipants = collectionManager.findElementById(id).getNumberOfParticipants();
                collectionManager.removeLowerByNumberOfParticipants(numberOfParticipants);
                consoleManager.println("Из коллекции были удалены все элементы с меньшим количеством участников");
            } else {
                consoleManager.println("В коллекции нет элементов с указаным id");
            }
        }
    }

    @Override
    public boolean checkArgument(Object arguments) {
        if (arguments == null) {
            consoleManager.println("Укжите id элемента коллекции, по которому нужно удалять другие");
            return false;
        } else {
            return true;
        }
    }
}
