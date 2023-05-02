package Exceptions;

/**
 * Пользовательское исключение для некорректных полей
 * @author maria
 */
public class IllegalValuesException extends Exception {
    public IllegalValuesException(String msg){
        super(msg);
    }
}
