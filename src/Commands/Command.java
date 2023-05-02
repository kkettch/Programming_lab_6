package Commands;

import java.io.FileNotFoundException;

/**
 * Интерфейс содержит методы для реализации выполнения команды и проверки аргументов
 * @author maria
 */
public interface Command {
    String getName();
    String getDescription();
    void execute(String arg) throws FileNotFoundException;
    boolean checkArgument(Object arguments);
}