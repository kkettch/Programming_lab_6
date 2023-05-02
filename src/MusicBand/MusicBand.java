package MusicBand;

import Exceptions.IllegalValuesException;
import Utility.Validation;
import java.time.LocalDateTime;
public class MusicBand {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int numberOfParticipants; //Значение поля должно быть больше 0
    private Integer albumsCount; //Поле может быть null, Значение поля должно быть больше 0
    private String description; //Поле может быть null
    private MusicGenre genre; //Поле может быть null
    private Person frontMan; //Поле может быть null
    private final Validation validation = new Validation();
    public MusicBand(Integer id, String name, Coordinates coordinates, LocalDateTime creationDate, int numberOfParticipants,
                     Integer albumsCount, String description, MusicGenre genre, Person frontMan) throws IllegalValuesException {
        this.setId(id);
        this.setName(name);
        this.setCoordinates(coordinates);
        this.setCreationDate(creationDate);
        this.setNumberOfParticipants(numberOfParticipants);
        this.setAlbumsCount(albumsCount);
        this.setDescription(description);
        this.setGenre(genre);
        this.setFrontMan(frontMan);
    }
    public MusicBand(){}
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }
    public Integer getAlbumsCount() {
        return albumsCount;
    }
    public String getDescription() {
        return description;
    }
    public MusicGenre getGenre() {
        return genre;
    }
    public Person getFrontMan() {
        return frontMan;
    }
    @Override
    public String toString() {
        String info = "";
        info += "Группа №" + id;
        info += "(добавлена " + creationDate.toLocalDate() + "" + creationDate.toLocalTime() + ")";
        info += "\nИмя: " + name;
        info += "\nМестоположение: " + coordinates;
        info += "\nКоличество участников в группе: " + numberOfParticipants;
        info += "\nКоличество выпущенных альбомов: " + albumsCount;
        info += "\nОписание: " + description;
        info += "\nМузыкальный жанр: " + genre;
        info += "\nИнформация о солисте группы: " + frontMan.toString();
        return info;
    }
    public void setId(Integer id) throws IllegalValuesException {
        if (validation.validateID(id)) {
            this.id = id;
        } else throw new IllegalValuesException("ID не может принимать значение null и быть неположительным числом, а также должен принимать уникальные значения!");
    }
    public void setName(String name) throws IllegalValuesException {
        if (validation.validateName(name)) {
            this.name = name;
        } else throw new IllegalValuesException("Имя не может принимать значение null и быть пустой строкой");
    }
    public void setCoordinates(Coordinates coordinates) throws IllegalValuesException {
        if (validation.validateCoordinates(coordinates)) {
            this.coordinates = coordinates;
        } else throw new IllegalValuesException("Координаты не соответствуют нужным требованиям");
    }
    public void setCreationDate(LocalDateTime creationDate) throws IllegalValuesException {
        if (validation.validateCreationDate(creationDate)) {
            this.creationDate = creationDate;
        } else throw new IllegalValuesException("Дата создания не может принимать значение null");
    }
    public void setNumberOfParticipants(int numberOfParticipants) throws IllegalValuesException {
        if (validation.validateNumberOfParticipants(numberOfParticipants)) {
            this.numberOfParticipants = numberOfParticipants;
        } else throw new IllegalValuesException("Количество участников должно принимать значения больше нуля");
    }
    public void setAlbumsCount(Integer albumsCount) throws IllegalValuesException {
        if (validation.validateAlbumsCount(albumsCount)) {
            this.albumsCount = albumsCount;
        } else throw new IllegalValuesException("Количество выпущенных альбомов должно принимать значения больше нуля");
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }
    public void setFrontMan(Person frontMan) throws IllegalValuesException {
        if (validation.validatePerson(frontMan)) {
            this.frontMan = frontMan;
        } else throw new IllegalValuesException("Данные для солиста введены неверно!");
    }
}