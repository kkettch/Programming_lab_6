package server;

public class ServerApp {
    private ServerApp() {
        throw new UnsupportedOperationException("Класс ServerApp не может быть инициализирован");
    }
    public static void main(String[] args) {
        ServerInstance serverInstance = new ServerInstance();
        serverInstance.run(args[0]);
    }
}