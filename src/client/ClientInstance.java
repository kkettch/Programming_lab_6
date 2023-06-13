package client;

import common.Message.Request;
import common.Message.Response;
import common.Utility.CommandManager;
import common.Utility.ConsoleManager;

import java.io.*;
import java.net.Socket;

public class ClientInstance {
    private final ConsoleManager consoleManager = new ConsoleManager();
    private boolean connection = true;
    public void run() {
        try {
            Socket clientSocket = new Socket("localhost", 1234);
            ObjectOutputStream objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream objectIn = new ObjectInputStream(clientSocket.getInputStream());
            consoleManager.println("Для получения списка доступных комманд введите help.");
            consoleManager.println("Для выхода из клиентского приложения введите exit");
            do{
                consoleManager.print("Введите объект для передачи на сервер: ");
                Request request = readUserInput(objectOut, objectIn);
                if (request != null) {
                    objectOut.writeObject(request);
                    objectOut.flush();
                    objectOut.reset();
                    consoleManager.println("Объект отправлен");
                    if (!connection) {
                        consoleManager.println("Отключение от сервера");
                        break;
                    }
                    Response response = (Response) objectIn.readObject();
                    consoleManager.println("Сервер отправил объект: " + '\n' + response.getDescription());
                }
            }while (connection);

            objectOut.close();
            objectIn.close();
            clientSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Соединение потеряно!");
        }
    }

    public Request readUserInput(ObjectOutputStream objectOut, ObjectInputStream objectIn) throws ClassNotFoundException {
        CommandManager commandManager = new CommandManager();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] userInput = reader.readLine().trim().split("\\s");
            if (userInput[0].equals("exit") && userInput.length == 1) {
                connection = false;
                return new Request("exit", null, null, false);
            } else {
                return commandManager.checkClientInput(userInput, objectOut, objectIn); //?
            }

        } catch (IOException e) {
            throw new RuntimeException("Считывание ввода пользователя не выполнен!");
        }
    }
}