package MusicBand;

import java.util.Date;

/**
 * Шаблон класса Person. Содержит геттеры и сеттеры для всех полей
 * Содержит ограничения прописанные для каждого поля
 * @author maria
 */
public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.util.Date birthday; //Поле не может быть null
    private Double height; //Поле не может быть null, Значение поля должно быть больше 0
    private float weight; //Значение поля должно быть больше 0
    private String passportID; //Строка не может быть пустой, Значение этого поля должно быть уникальным, Длина строки не должна быть больше 21, Поле может быть null
     public Person(String name, Date birthday, Double height, float weight, String passportID) {
        this.setPersonName(name);
        this.setPersonBirthday(birthday);
        this.setPersonHeight(height);
        this.setPersonWeight(weight);
        this.setPersonPassportID(passportID);
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
    public void setPersonName(String name) {
         this.name = name;
    }
    public void setPersonBirthday(Date birthday) {
         this.birthday = birthday;
    }
    public void setPersonHeight(Double height)  {
         this.height = height;
    }
    public void setPersonWeight(float weight) {
         this.weight = weight;
    }
    public void setPersonPassportID(String passportID) {
         this.passportID = passportID;
    }
}