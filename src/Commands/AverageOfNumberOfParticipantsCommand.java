package Commands;

import Utility.CollectionManager;
import Utility.ConsoleManager;
public class AverageOfNumberOfParticipantsCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    public AverageOfNumberOfParticipantsCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.consoleManager = new ConsoleManager();
    }
    public AverageOfNumberOfParticipantsCommand(){}
    @Override
    public String getName() {
        return "average_of_number_of_participants";
    }
    @Override
    public String getDescription() {
        return "вывести среднее значение поля numberOfParticipants для всех элементов коллекции";
    }
    @Override
    public void execute(String arg) {
        if (checkArgument(getArguments())) {
            collectionManager.getAverageNumberOfParticipants();
        }
    }
    @Override
    public boolean checkArgument(Object arguments) {
        if (arguments == null) {
            return true;
        } else {
            consoleManager.println("Комманда 'average_of_number_of_participants' не имеет аргументов!");
            return false;
        }
    }
}
