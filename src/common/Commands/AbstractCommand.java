package common.Commands;

import common.Message.Request;

import java.io.FileNotFoundException;

/**
 * Абстрактный класс содержит базовые методы для реализации комманд
 * @author maria
 */
public abstract class AbstractCommand implements Command {
    private Request request;
    public Object getRequest() {
        return request;
    }
    public void setRequest(Request request) {
        this.request = request;
    }
    public abstract String execute(Request request) throws FileNotFoundException;
}