package MusicBand;

import Exceptions.IllegalValuesException;

import java.util.Date;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.util.Date birthday; //Поле не может быть null
    private Double height; //Поле не может быть null, Значение поля должно быть больше 0
    private float weight; //Значение поля должно быть больше 0
    private String passportID; //Строка не может быть пустой, Значение этого поля должно быть уникальным, Длина строки не должна быть больше 21, Поле может быть null

     public Person(String name, Date birthday, Double height, float weight, String passportID) {
        this.name = name;
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.passportID = passportID;
    }

    public String getPersonName() {
        return name;
    }

    public Date getPersonBirthday() {
        return birthday;
    }

    public Double getPersonHeight() {
        return height;
    }

    public float getPersonWeight() {
        return weight;
    }

    public String getPersonPassportID() {
        return passportID;
    }

    @Override
    public String toString() {
        String info = "";
        info += "\nИмя: " + name;
        info += "\nДата рождения: " + birthday;
        info += "\nРост: " + height;
        info += "\nВес: " + weight;
        info += "\nПаспорт: " + passportID+"\n";
        return info;
    }

    public void setPersonName(String name) throws IllegalValuesException {
         if (name == null || name.isEmpty()) throw new IllegalValuesException("Имя не может принимать значение null и быть пустым");
         this.name = name;
    }

    public void setPersonBirthday(Date birthday) throws IllegalValuesException{
         if (birthday == null) throw new IllegalValuesException("Дата не может принимать значение null");
         this.birthday = birthday;
    }

    public void setPersonHeight(Double height) throws IllegalValuesException {
         if (height == null || height <= 0) throw new IllegalValuesException("Рост не может принимать значение null и быть меньше нуля");
        this.height = height;
    }

    public void setPersonWeight(float weight) throws IllegalValuesException {
         if (weight <= 0) throw new IllegalValuesException("Вес не может принимать неположительные значения");
        this.weight = weight;
    }

    public void setPersonPassportID(String passportID) throws IllegalValuesException{
         if (passportID.isEmpty() || passportID.length() > 21) throw new IllegalValuesException("Пасспорт ID не может быть пустым и быть длиннее 21 символа");
        this.passportID = passportID;
    }
}
