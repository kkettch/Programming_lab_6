package Commands;

import java.io.FileNotFoundException;

/**
 * Абстрактный класс содержит базовые методы для реализации комманд
 * @author maria
 */
public abstract class AbstractCommand implements Command {
    private Object arguments;
    public Object getArguments() {
        return arguments;
    }
    public void setArguments(Object arguments) {
        this.arguments = arguments;
    }
    public abstract void execute(String arg) throws FileNotFoundException;
}