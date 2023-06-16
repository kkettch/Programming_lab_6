package server;

import common.FileManager.FileManager;
import common.Message.Request;
import common.Message.Response;
import common.Utility.CollectionManager;
import common.Utility.CommandManager;
import common.Utility.ConsoleManager;


import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;


public class ServerInstance {
    private final ConsoleManager consoleManager = new ConsoleManager();
    private static Logger logger = Logger.getLogger(ServerInstance.class.getName());


    public void run(String arg) {
        FileManager fileManagerReader = new FileManager(arg);
        CollectionManager collectionManager = new CollectionManager(fileManagerReader.readFromFile());
        List<String> fileNameList = new ArrayList<>();
        CommandManager commandManager = new CommandManager(collectionManager, fileManagerReader, fileNameList);
        Thread consoleThread = new Thread(() -> {
            Scanner commandServer = new Scanner(System.in);
            while (true) {
                String commandLine = commandServer.nextLine();
                if ("exit".equals(commandLine)) {
                    System.exit(0);
                    break;
                } else if ("save".equals(commandLine)) {
                    fileManagerReader.writeCollection(collectionManager.getMusicBandVector(), fileManagerReader.getFileName());
                    consoleManager.println("Коллекция сохранена!");
                }
            }
        });
        consoleThread.start();
        logger.info("Сервер запущен!");
        consoleManager.println("Команды доступные серверу: exit - выключение сервера, save - сохранение коллекции в файл\"");
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress(2589));
            ssc.configureBlocking(false);
            while (true) {
                SocketChannel sc = ssc.accept();
                if (sc != null) {
                    logger.info("Получено соединение от: " + sc.socket().getRemoteSocketAddress());
                    try {
                        ObjectOutputStream objectOut = new ObjectOutputStream(sc.socket().getOutputStream());
                        ObjectInputStream objectIn = new ObjectInputStream(sc.socket().getInputStream());
                        while (true) { // Цикл будет выполняться, пока клиент подключен
                            Request request = (Request) objectIn.readObject();
                            if (request == null) {
                                logger.info("Соединение с клиентом потеряно");
                                break;
                            }
                            if (request.isSubquery()) {
                                if (request.getCommandName().equals("check_id")) {
                                    objectOut.writeObject(new Response(collectionManager.isElementExist(Integer.parseInt(request.getArgument()))));
                                } else {
                                    objectOut.writeObject(new Response(collectionManager.checkPassportID(request.getArgument())));
                                }
                            } else {
                                logger.info("Получен сериализованный объект от клиента: " + request.getCommandName() + " " + request.getArgument());
                                if (request.getCommandName().equals("exit")) {
                                    logger.info("Клиент завершил соединение");
                                    break;
                                }
                                String result = commandManager.commandExecute(request);
                                Response response = new Response(result);
                                objectOut.writeObject(response);
                                objectOut.flush();
                                objectOut.reset();
                                logger.info("Объект отправлен обратно клиенту");
                            }
                        }
                    } catch (ClassNotFoundException e) {
                        logger.warning("Ошибка при чтении объекта от клиента: " + e.getMessage());
                    } finally {
                        sc.close();
                        logger.info("Соединение с клиентом потеряно");
                        continue;
                    }
                }
            }
        } catch (IOException e) {
            logger.warning("Ошибка IO");
        }
    }
}