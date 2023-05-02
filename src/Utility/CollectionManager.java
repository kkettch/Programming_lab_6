package Utility;

import Exceptions.IllegalValuesException;
import MusicBand.MusicBand;
import java.util.*;

/**
 * Содержит методы для управления коллекцией элементов MusicBand
 * @author maria
 */
public class CollectionManager {
    private Vector<MusicBand> musicBandVector = new Vector<>();
    private final ConsoleManager consoleManager = new ConsoleManager();
    public CollectionManager() {}
    public CollectionManager(Vector<MusicBand> musicBandVector) {
        this.musicBandVector = musicBandVector;
        checkCollection(musicBandVector);
    }

    /**
     * Вывод элементов коллекции
     */
    public void showCollection(){
        ConsoleManager consoleManager = new ConsoleManager();
        if (musicBandVector.isEmpty()) {
            consoleManager.println("Коллекция пуста");
        } else {
            consoleManager.println("Элементы коллекции: ");
            for (MusicBand musicBand : musicBandVector){
                consoleManager.println(musicBand);
            }
        }

    }

    /**
     * Удаление всех элементов из коллекции
     */
    public void clearCollection(){
        musicBandVector.clear();
        consoleManager.println("Коллекция была очищена");
    }
    public void removeFirstElement() {
        if (musicBandVector.isEmpty()) {
            consoleManager.println("Удалить первый элемент в коллекции невозможно, т.к. коллекция пуста");
        } else {
            musicBandVector.remove(musicBandVector.firstElement());
            consoleManager.println("Первый элемент коллекции был удалён");
        }
    }

    /**
     * Получение среднего значения поля numberOfParticipants
     */
    public void getAverageNumberOfParticipants() {
        int number = 0;
        int i = 0;
        if (musicBandVector.isEmpty()) {
            consoleManager.println("0");
        } else {
            for (MusicBand musicBand : musicBandVector) {
                number += musicBand.getNumberOfParticipants();
                i++;
            }
            consoleManager.print("Среднее число участников в команде: ");
            consoleManager.println(number/i);
        }
    }

    /**
     * Получение информации о коллекции
     */
    public void getInfoAboutCollection() {
        consoleManager.println("Тип коллекции: " + musicBandVector.getClass().getSimpleName());
        consoleManager.println("Количество элементов в коллекции: " + musicBandVector.size());
        if (musicBandVector.size() == 0) {
            consoleManager.println("Дата инициализации отсутствует т.к. коллекция пуста");
        } else {
            consoleManager.println("Дата инициализации: " + musicBandVector.firstElement().getCreationDate());
        }
    }

    /**
     * Нахождение элемента по id
     * @param id
     * @return элемент коллекции с введенным id
     */
    public MusicBand findElementById(Integer id) {
        for (MusicBand musicBand : musicBandVector) {
            if (musicBand.getId().equals(id)) {
                return musicBand;
            }
        }
        return null;
    }

    /**
     * Удаление элемента из коллекции
     * @param musicBand
     */
    public void removeElementById(MusicBand musicBand) {
        if (musicBand == null) {
            consoleManager.println("Элемента с введенным id нет в коллекции");
        } else {
            musicBandVector.remove(musicBand);
            consoleManager.println("Элемент, имеющий id " + musicBand.getId() + " был удален");
        }
    }

    /**
     * Нахождение элементов, которые содержит указанную подстроку в поле description
     * @param description подстрока
     */
    public void findElementsByDescription(String description) {
        List<MusicBand> musicBandList = new ArrayList<>();
        for (MusicBand musicBand : musicBandVector) {
            if (musicBand.getDescription().contains(description)) {
                musicBandList.add(musicBand);
            }
        }
        if (musicBandList.isEmpty()) {
            consoleManager.println("В коллекции нет объектов с таким описанием");
        } else {
            consoleManager.println(musicBandList.toString());
        }
    }

    /**
     * Удаление наименьшего элемента по числу участников
     * @param numberOfParticipants число участников
     */
    public void removeLowerByNumberOfParticipants(int numberOfParticipants) {
        if (musicBandVector.isEmpty()) {
            consoleManager.println("Коллекция пуста. Ничего нельзя удалить");
        } else {
            Iterator<MusicBand> iterator = musicBandVector.iterator();
            while (iterator.hasNext()) {
                MusicBand musicBand = iterator.next();
                if (musicBand.getNumberOfParticipants() < numberOfParticipants) {
                    consoleManager.println("Элемент с именем '" + musicBand.getName() + "' был удален из коллекции");
                    iterator.remove();
                }
            }
        }
    }

    /**
     * Сортировка коллекции по убыванию
     * @return отсортированная коллекция
     */
    public Vector<MusicBand> sortDescending() {
        musicBandVector.sort(new SortByGenre());
        return musicBandVector;
    }

    /**
     * Получение всех значений коллекции поля passportID
     * @return List, содержащий все поля коллекции
     */
    public List<String> getListOfPassportID() {
        List<String> listOfPassportID = new ArrayList<>();
        for (MusicBand musicBand : musicBandVector) {
            if (musicBand.getFrontMan().getPersonPassportID()!=null) {
                listOfPassportID.add(musicBand.getFrontMan().getPersonPassportID());
            }
        }
        return listOfPassportID;
    }

    /**
     * Проверка введенного passportID на уникальность
     * @param passportID введенный passportID
     * @return ответ на вопрос является ли поле уникальным
     */
    public boolean checkUniquePassportID(String passportID) {
        List<String> listOfPassportID = getListOfPassportID();
        return listOfPassportID.contains(passportID);
    }

    /**
     * Получения списка имеющихся id у всех элементов коллекции
     * @return List, содержащий все id коллекции
     */
    public List<Integer> getListOfId() {
        List<Integer> listOfId = new ArrayList<>();
        for (MusicBand musicBand : musicBandVector) {
            listOfId.add(musicBand.getId());
        }
        return listOfId;
    }

    /**
     * Проверка имеющегося id на уникальность
     * @param id имеющийся id
     * @return ответ на вопрос является ли поле уникальным
     */
    public boolean checkUniqueId(Integer id) {
        List<Integer> listOfId = getListOfId();
        return listOfId.contains(id);
    }

    /**
     * Добавление объекта типа MusicBand в коллекцию
     * @param musicBand объект типа MusicBand
     */
    public void addElement(MusicBand musicBand) {
        musicBandVector.add(musicBand);
    }

    /**
     * Геттер для получения коллекции типа Vector
     * @return коллекция Vector
     */
    public Vector<MusicBand> getMusicBandVector() {
        return musicBandVector;
    }

    /**
     * Обноевление элемента в коллекции
     * @param musicBand элемент который обновляем
     * @param id его нынешний id
     * @param collect
     */
    public void updateElement(MusicBand musicBand, Integer id, CollectionManager collect) {
        if (findElementById(id) != null) {
            CreatorOfMusicBand creatorOfMusicBand = new CreatorOfMusicBand(collect);
            creatorOfMusicBand.setMusicBandForUpdateIdCommand(musicBand);
            consoleManager.println("Элемент с id-" + id + " был обновлен!");
        } else {
            consoleManager.println("В коллекции нет элемента с введенным id!");
        }
    }

    /**
     * Максимальное значения поля numberOfParticipants в коллекции
     * @return
     */
    public int findMaxNumberOfParticipants() {
        int maxNumber = 0;
        for (MusicBand musicBand : musicBandVector) {
            if (musicBand.getNumberOfParticipants() > maxNumber) {
                maxNumber = musicBand.getNumberOfParticipants();
            }
        }
        return maxNumber;
    }

    /**
     * Проверка всех полей элементов коллекции на валидность
     * @param collection
     */
    public void checkCollection(Vector<MusicBand> collection){
        try {
            for (MusicBand musicband : collection) {
                musicband.setId(musicband.getId());
                musicband.setName(musicband.getName());
                musicband.setCoordinates(musicband.getCoordinates());
                musicband.setCreationDate(musicband.getCreationDate());
                musicband.setNumberOfParticipants(musicband.getNumberOfParticipants());
                musicband.setAlbumsCount(musicband.getAlbumsCount());
                musicband.setDescription(musicband.getDescription());
                musicband.setGenre(musicband.getGenre());
                musicband.setFrontMan(musicband.getFrontMan());
            }
            this.musicBandVector = collection;
        }
        catch(IllegalValuesException msg){
            consoleManager.println(msg.getMessage());
            this.musicBandVector = new Vector<>();
        }
    }
}