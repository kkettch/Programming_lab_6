package client;

import common.Exceptions.InvalidInputException;

public class ClientApp {
    private ClientApp() {
        throw new UnsupportedOperationException("Класс ClientApp не может быть инициализирован");
    }

    public static void main(String[] args) {
        ClientInstance clientInstance = new ClientInstance();
        clientInstance.run();
    }
}