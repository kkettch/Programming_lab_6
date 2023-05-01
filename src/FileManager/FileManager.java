package FileManager;

import MusicBand.MusicBand;
import Utility.ConsoleManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.io.PrintWriter;


public class FileManager {
    private Gson gsonBuilder = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).registerTypeAdapter(
            Date.class, new DateAdapt()).setPrettyPrinting().create();
    private String fileName;
    private ConsoleManager consoleManager = new ConsoleManager();

    public FileManager(String fileName){
        this.fileName= fileName;
    }
    public FileManager(){}
    public Vector<MusicBand> readFromFile() throws IOException {
        Vector<MusicBand> musicBandVector = new Vector<>();
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {
            Type musicBandVectorType = new TypeToken<Vector<MusicBand>>() {}.getType();
            String coll = null;
            while (scanner.hasNext()){
                String col = scanner.nextLine().trim();
                coll = coll + col;
            }
            String a = coll.substring(4);
            musicBandVector = gsonBuilder.fromJson(a, musicBandVectorType);
        }catch (NullPointerException msg) {
            consoleManager.println("Файл пустой! Считать коллекцию не удалось!");
        }catch (FileNotFoundException msg) {
            if(file.exists()){
                consoleManager.println("Файл не удалось открыть, проверьте права доступа к файлу");
            }
            else {
                consoleManager.println("Файл с указанным названием не найден! Считать коллекцию не удалось!");
            }
        }catch (JsonSyntaxException msg) {
            consoleManager.println("Данные в файле повреждены! Перепроверьте содержимое файла");
        }catch (DateTimeParseException msg){
            consoleManager.println("В файле невалидная дата creationDate! Считать коллекцию не удалось!");
        }catch (JsonParseException msg){
            consoleManager.println("В файле невалидная дата birthday! Считать коллекцию не удалось!");
        }
        return musicBandVector;
    }

    public void writeCollection(Collection<?> collection, String name) {
        File file = new File(name);
        if (name!=null && file.canWrite() ) {
            try (FileWriter collectionFileWrit = new FileWriter(name)){
                collectionFileWrit.write(gsonBuilder.toJson(collection));
            }catch (IOException exception) {
                consoleManager.println("Ошибка: загрузочный файл является директорией/не может быть открыт!");
            }
        } else consoleManager.println("Системная переменная с загрузочным файлом не найдена!");
    }
    public void writeCollection(Collection<?> collection, FileManager fileManagerReader) {
        try {
            PrintWriter printer = new PrintWriter(new File(fileManagerReader.getFileName()));
            printer.write(gsonBuilder.toJson(collection));
            consoleManager.println("Коллекция успешна сохранена в файл!");
        } catch (IOException exception) {
            consoleManager.println("Загрузочный файл является директорией/не может быть открыт!");
        }
    }

    public String getFileName(){
        return fileName;
    }
}
