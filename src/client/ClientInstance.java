package client;

import common.Message.Request;
import common.Message.Response;
import common.Utility.CommandManager;
import common.Utility.ConsoleManager;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClientInstance {
    private final ConsoleManager consoleManager = new ConsoleManager();
    private static final List<String> fileList = new ArrayList<>();
    private static boolean connection = true;
    private static final int MAX_RECONNECT_ATTEMPTS = 10; // Максимальное количество попыток переподключения
    private static final int RECONNECT_DELAY = 3000; // Задержка между попытками переподключения (в миллисекундах)

    public void run() {
        int reconnectAttempts = 0; // Количество попыток переподключения
        boolean reconnectFlag = true;
        while (reconnectAttempts < MAX_RECONNECT_ATTEMPTS) {
            try {
                Socket clientSocket = new Socket("localhost", 2589);
                ObjectOutputStream objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream objectIn = new ObjectInputStream(clientSocket.getInputStream());
                consoleManager.println("Для получения списка доступных комманд введите help.");
                consoleManager.println("Для выхода из клиентского приложения введите exit");
                connection = true; // Восстанавливаем соединение

                do {
                    consoleManager.print("Введите объект для передачи на сервер: ");
                    Request request = readUserInput(objectOut, objectIn);
                    if (request != null) {
                        objectOut.writeObject(request);
                        objectOut.flush();
                        objectOut.reset();
                        consoleManager.println("Объект отправлен");
                        if (!connection) {
                            consoleManager.println("Отключение от сервера");
                            reconnectAttempts = 3;
                            reconnectFlag = false;
                            break;
                        }
                        Response response = (Response) objectIn.readObject();
                        consoleManager.println("Сервер отправил объект: " + '\n' + response.getDescription());
                    }
                } while (connection);

                objectOut.close();
                objectIn.close();
                clientSocket.close();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Соединение потеряно!");
                reconnectAttempts++;
                consoleManager.println("Попытка переподключения...");
                try {
                    Thread.sleep(RECONNECT_DELAY);
                } catch (InterruptedException ex) {
                    consoleManager.println("Проблема с потоком");
                }
            }
        }
        if(reconnectFlag) {
            consoleManager.println("Достигнуто максимальное количество попыток переподключения. Завершение работы клиента.");
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
                Request request = commandManager.checkClientInput(userInput, objectOut, objectIn);
                if (!(request == null) && request.getCommandName().equals("execute_script")) {
                    makeExecute(request, objectOut, objectIn, commandManager);
                    return null;
                } else {
                    return request;
                }
            }

        } catch (IOException e) {
            consoleManager.println("Считывание ввода пользователя не выполнен!");
        } return null;
    }
    public void makeExecute(Request req, ObjectOutputStream objectOut, ObjectInputStream objectIn, CommandManager commandManager) {
        File file = new File(req.getArgument());
        if (fileList.contains(req.getArgument())) {
            consoleManager.println("В файле обнаружена рекурсия!");
        } else {
            fileList.add(req.getArgument());
            try (Scanner scanner = new Scanner(file)) {
//                if (!scanner.hasNextLine()) throw new NoSuchElementException();
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine().trim();
                    if (line.equals("exit")) {
                        consoleManager.println("Встречена команда exit, выполнение команд файла остановлено!");
                        break;
                    }
                    if (line.isEmpty()) {
                        continue;
                    } else {
                        String[] command = line.split("\\s");
                        Request request = commandManager.checkClientInput(command, objectOut, objectIn);
                        if (request != null) {
                            if (request.getCommandName().equals("execute_script")) {
                                makeExecute(request, objectOut, objectIn, commandManager);
                            } else {
                                objectOut.writeObject(request);
                                objectOut.flush();
                                objectOut.reset();
                                consoleManager.println("Объект отправлен");
                                Response response = (Response) objectIn.readObject();
                                consoleManager.println("Сервер отправил объект: " + '\n' + response.getDescription());
                            }
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (NoSuchElementException msg) {
                consoleManager.println("Содержимое файла прочитано");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}