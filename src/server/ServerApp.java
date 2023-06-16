package server;

import common.Utility.ConsoleManager;

public class ServerApp {
    private static final ConsoleManager consoleManager = new ConsoleManager();
    private ServerApp() {
        throw new UnsupportedOperationException("Класс ServerApp не может быть инициализирован");
    }
    public static void main(String[] args) {
        try {
            ServerInstance serverInstance = new ServerInstance();
            serverInstance.run(args[0]);
        } catch (ArrayIndexOutOfBoundsException msg) {
            consoleManager.println("Перед запуском программы укажите файл!");
        }

    }
}