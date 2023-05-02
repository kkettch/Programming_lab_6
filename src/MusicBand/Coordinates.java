package MusicBand;

import Exceptions.IllegalValuesException;
import Utility.Validation;

/**
 * Шаблон класса Coordinates. Содержит геттеры и сеттеры для всех полей
 * Содержит ограничения прописанные для каждого поля
 * @author maria
 */
public class Coordinates {
    private int x; //Максимальное значение поля: 223
    private int y; //Значение поля должно быть больше -326
    private final Validation validation = new Validation();
    public Coordinates(int x, int y) throws IllegalValuesException {
        this.setX(x);
        this.setY(y);
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    @Override
    public String toString() {
        return "X: " + x + "; Y: " + y + ";";
    }
    public void setX(int x) throws IllegalValuesException{
        if (validation.validateX(x)) {
            this.x = x;
        } else throw new IllegalValuesException("Значение координаты х не может быть больше 223");
    }
    public void setY(int y) throws IllegalValuesException{
        if (validation.validateY(y)) {
            this.y = y;
        } else throw new IllegalValuesException("Значение координаты у должно быть больше -326");
    }
}