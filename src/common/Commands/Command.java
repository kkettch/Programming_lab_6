package common.Commands;

import common.Message.Request;

import java.io.FileNotFoundException;

/**
 * Интерфейс содержит методы для реализации выполнения команды и проверки аргументов
 * @author maria
 */
public interface Command {
    String getName(); //имя команды
    String getDescription(); //описание команды
    String execute(Request request) throws FileNotFoundException; //выполнение команды
}