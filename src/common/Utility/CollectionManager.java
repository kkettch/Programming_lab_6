package common.Utility;

import common.Exceptions.IllegalValuesException;
import common.MusicBand.MusicBand;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Содержит методы для управления коллекцией элементов common.MusicBand
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
     * Вывод элементов коллекции для команды show
     */
    public String showCollection(){
        StringBuilder show;
        if (musicBandVector.isEmpty()) {
            show = new StringBuilder("Коллекция пуста"); //если коллекция пустая, выводим соответствующее сообщение
        } else {
            show = new StringBuilder("Элементы коллекции: " + '\n');
            show.append(musicBandVector.stream() //формируем потом
                    .map(MusicBand::toString) //преобразовываем каждый объект MusicBand в строковое представление
                    .collect(Collectors.joining("\n"))); //объединяем все элементы в одну строку, разделяя регулярным выражением \n
        }
        return show.toString();
    }

    /**
     * Удаление всех элементов из коллекции
     */
    public String clearCollection(){
        musicBandVector.clear(); //очищаем коллекцию
        return "Коллекция была очищена"; //отправляем клиенту сообщение об успешном выполненнии команды
    }
    public String removeFirstElement() {
        if (musicBandVector.isEmpty()) {
            return "Удалить первый элемент в коллекции невозможно, т.к. коллекция пуста";
        } else {
            musicBandVector.remove(musicBandVector.firstElement());
            return "Первый элемент коллекции был удалён";
        }
    }

    /**
     * Получение среднего значения поля numberOfParticipants
     */
    public String getAverageNumberOfParticipants() {
        double average = musicBandVector.stream() //среднее значение записывается в переменную типа double
                .mapToDouble(MusicBand::getNumberOfParticipants)//преобразовываем объект MusicBand в значение поля NumberOfParticipants
                .average() //находим среднее значение этого поля во всей коллекции
                .orElse(0.0); //иначе возвращаем ноль
        String formattedAverage = String.format("%.2f", average); //выводим только 2 знака после запятой
        return "Среднее количество участников в команде: " + formattedAverage; //возвращаем результат
    }

    /**
     * Получение информации о коллекции
     */
    public String getInfoAboutCollection() {
        StringBuilder result = new StringBuilder();
        result.append("Тип коллекции: ").append(musicBandVector.getClass().getSimpleName()).append("\n");
        result.append("Количество элементов в коллекции: ").append(musicBandVector.size()).append("\n");
        if (musicBandVector.size() == 0) {
            result.append("Дата инициализации отсутствует т.к. коллекция пуста");
        } else {
            result.append("Дата инициализации: ").append(musicBandVector.firstElement().getCreationDate());
        }
        return result.toString();
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

    public String isElementExist(Integer id) {
        boolean exist = musicBandVector.stream()
                .anyMatch(musicBand -> musicBand.getId().equals(id));
        return String.valueOf(exist);
    }

    /**
     * Удаление элемента из коллекции
     * @param musicBand
     */
    public String removeElementById(MusicBand musicBand) {
        if (musicBand == null) {
            return "Произошла непредвиденная ошибка";
        } else {
            musicBandVector.remove(musicBand);
            return "Элемент, имеющий id " + musicBand.getId() + " был удален";
        }
    }

    /**
     * Нахождение элементов, которые содержит указанную подстроку в поле description
     * @param description подстрока
     */
    public String findElementsByDescription(String description) {
        List<MusicBand> musicBandList = musicBandVector.stream() //создаем лист, в который запишем полходящие по условию объекты MusicBand
                .filter(musicBand -> musicBand.getDescription().contains(description)) //если в объекте musicBand содержится введенная клиентом строка
                .toList(); //то записываем объект в лист
        if (musicBandList.isEmpty()) {
            return "В коллекции нет объектов с таким описанием";  //если нет объектов с введенным описание, то отправляем соответствующее сообщение
        } else {
            return musicBandList.toString(); //иначе отправляем объекты коллекции
        }
    }

    /**
     * Удаление наименьшего элемента по числу участников
     * @param numberOfParticipants число участников
     */
    public String removeLowerByNumberOfParticipants(int numberOfParticipants) {
        StringBuilder result = new StringBuilder();
        if (musicBandVector.isEmpty()) {
            result.append("Коллекция пуста. Ничего нельзя удалить");
        } else {
            Iterator<MusicBand> iterator = musicBandVector.iterator();
            while (iterator.hasNext()) {
                MusicBand musicBand = iterator.next();
                if (musicBand.getNumberOfParticipants() < numberOfParticipants) {
                    result.append("Элемент с именем '").append(musicBand.getName()).append("' был удален из коллекции").append("\n");
                    iterator.remove();
                }
            }
        } return result.toString();
    }

    /**
     * Сортировка коллекции по убыванию
     * @return отсортированная коллекция
     */
    public String sortDescending() {
        return musicBandVector.stream()
                .sorted(new SortByGenre()) //сортировка коллекции по полю genre
                .map(MusicBand::getGenre) //преобразование в список поля genre
                .map(Enum::toString) //преобразование поля genre в строку
                .collect(Collectors.joining(", ")); //вывод значения этого поля через запятую
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
    public String checkPassportID(String passportID) {
        List<String> listOfPassportID = getListOfPassportID();
        if (listOfPassportID.contains(passportID)){
            return "passportID уже содержится в коллекции! Попробуйте ввести другое значение!";
        } else if (passportID.length() > 21) {
            return "Длина поля passportID не должна быть больше 21 символа!";
        } else
            return "true";

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
     * Добавление объекта типа common.MusicBand в коллекцию
     * @param musicBand объект типа common.MusicBand
     */
    public void addElement(MusicBand musicBand) {
        musicBandVector.add(musicBand); //добавление элемента MusicBand в колелкцию
    }

    public Integer generateID() {
        Integer id;
        do {
            id = new Random().nextInt(100000) + 1; //получаем значение id
        } while (checkUniqueId(id)); //до тех пор пока не будет получено уникальное значение
        return id;
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
    public String updateElement(MusicBand musicBand, Integer id, CollectionManager collect) {
        CreatorOfMusicBand creatorOfMusicBand = new CreatorOfMusicBand(collect);
        Integer numberInCollection = getNumberInCollection(id);
        creatorOfMusicBand.setMusicBandForUpdateIdCommand(musicBand, numberInCollection);
        return "Элемент с id-" + id + " был обновлен!";
    }

    /**
     * Получение индекса элемента в коллекции для его обновления
     * @param id элемент коллекции
     * @return индекс
     */
    public Integer getNumberInCollection(Integer id) {
        int i = 0;
        for (MusicBand musicBand : musicBandVector) {
            if (musicBand.getId().equals(id)) {
                return i;
            }
            i++;
        }
        return null;
    }

    /**
     * Максимальное значения поля numberOfParticipants в коллекции
     * @return
     */
    public int findMaxNumberOfParticipants() {
        return musicBandVector.stream()
                .mapToInt(MusicBand::getNumberOfParticipants) //преобразование каждого объекта в число участников
                .max() //нахождение максимального значения
                .orElse(0); //иначе устанавливаем 0
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