import FileManager.FileManager;
import Utility.CollectionManager;
import Utility.CommandManager;
import MusicBand.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        Vector<MusicBand> vector = new Vector<MusicBand>();

        String input = "2004-04-04";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar(2004, Calendar.APRIL, 4);
        Date birthDate = calendar.getTime();
        LocalDateTime currentDate = LocalDateTime.now();
        Person person1 = new Person("jack", birthDate, 6.6, 70, "1234");
        Person person2 = new Person("john", birthDate, 6.3, 77, "123456");
        Person person3 = new Person("jason", birthDate, 7.6, 90, "98765");

        MusicBand band1 = new MusicBand(1,"Group1", new Coordinates(200,200), currentDate, 3, 2, "какая-то группа1", MusicGenre.POP, person1);
        MusicBand band2 = new MusicBand(2,"Group2", new Coordinates(100,150), currentDate, 6, 8, "какая-то группа2", MusicGenre.MATH_ROCK, person2);
        MusicBand band3 = new MusicBand(3,"Group3", new Coordinates(140,200), currentDate, 4, 3, "какая-то группа3", MusicGenre.PSYCHEDELIC_ROCK, person3);
        vector.add(band1);
        vector.add(band2);
        vector.add(band3);


        FileManager fileManagerReader = new FileManager(args[0]);
        CollectionManager collectionManager = new CollectionManager(fileManagerReader.readFromFile());
        List<String> fileNameList = new ArrayList<>();
        CommandManager commandManager = new CommandManager(collectionManager, fileManagerReader, fileNameList);

        commandManager.commandExecute();
    }
}