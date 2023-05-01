package MusicBand;

import Exceptions.IllegalValuesException;

import java.time.LocalDateTime;
import java.util.Comparator;

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

    public MusicBand(Integer id, String name, Coordinates coordinates, LocalDateTime creationDate, int numberOfParticipants,
                     Integer albumsCount, String description, MusicGenre genre, Person frontMan) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.numberOfParticipants = numberOfParticipants;
        this.albumsCount = albumsCount;
        this.description = description;
        this.genre = genre;
        this.frontMan = frontMan;
    }

    public MusicBand(){};

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
        if (id == null || id <= 0) throw new IllegalValuesException("ID не может принимать значение null и быть неположительным числом");
        this.id = id;
    }

    public void setName(String name) throws IllegalValuesException {
        if (name == null || name.isEmpty()) throw new IllegalValuesException("Имя не может принимать значение null и быть пустой строкой");
        this.name = name;
;    }

    public void setCoordinates(Coordinates coordinates) throws IllegalValuesException {
        if (coordinates== null) throw new IllegalValuesException("Координаты не могут принимать значение null");
        coordinates.setX(coordinates.getX());
        coordinates.setY(coordinates.getY());
        this.coordinates = coordinates;
    }

    public void setCreationDate(LocalDateTime creationDate) throws IllegalValuesException {
        if (creationDate == null) throw new IllegalValuesException("Дата создания не может принимать значение null");
        this.creationDate = creationDate;
    }

    public void setNumberOfParticipants(int numberOfParticipants) throws IllegalValuesException {
        if (numberOfParticipants <= 0) throw new IllegalValuesException("Количество участников должно принимать значения больше нуля");
        this.numberOfParticipants = numberOfParticipants;
    }

    public void setAlbumsCount(Integer albumsCount) throws IllegalValuesException {
        if (albumsCount!=null) {
            if (albumsCount <= 0) throw new IllegalValuesException("Количество выпущенных альбомов должно принимать значения больше нуля");
        }
        this.albumsCount = albumsCount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }

    public void setFrontMan(Person frontMan) throws IllegalValuesException {
        frontMan.setPersonName(frontMan.getPersonName());
        frontMan.setPersonBirthday(frontMan.getPersonBirthday());
        frontMan.setPersonHeight(frontMan.getPersonHeight());
        frontMan.setPersonWeight(frontMan.getPersonWeight());
        frontMan.setPersonPassportID(frontMan.getPersonPassportID());
        this.frontMan = frontMan;
    }

}
