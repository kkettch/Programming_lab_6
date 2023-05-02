package Exceptions;

/**
 * Пользовательское исключения для некорректного ввода пользователем
 * @author maria
 */

public class InvalidInputException extends Exception {
    public InvalidInputException(String msg) {
        super(msg);
    }
}
