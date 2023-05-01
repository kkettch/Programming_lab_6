package MusicBand;

import Exceptions.IllegalValuesException;

public class Coordinates {
    private int x; //Максимальное значение поля: 223
    private int y; //Значение поля должно быть больше -326

    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
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
        if (x > 223) throw new IllegalValuesException("Значение координаты х не может быть больше 223");
        this.x = x;
    }

    public void setY(int y) throws IllegalValuesException{
        if (y < -325) throw new IllegalValuesException("Значение координаты у должно быть больше -326");
        this.y = y;
    }
}
