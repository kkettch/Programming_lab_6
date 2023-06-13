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

public class ServerInstance {
    private final ConsoleManager consoleManager = new ConsoleManager();
    int port;
    public void run(String arg) {
        FileManager fileManagerReader = new FileManager(arg);
        CollectionManager collectionManager = new CollectionManager(fileManagerReader.readFromFile());
        List<String> fileNameList = new ArrayList<>();
        CommandManager commandManager = new CommandManager(collectionManager, fileManagerReader, fileNameList);

        Thread consoleThread = new Thread(()->{
            Scanner commandServer = new Scanner(System.in);
            while(true){
                String commandLine = commandServer.nextLine();
                if ("exit".equals(commandLine)){
                    System.exit(0);
                    break;
                } else if ("save".equals(commandLine)){
                    fileManagerReader.writeCollection(collectionManager.getMusicBandVector(), fileManagerReader.getFileName());
                    consoleManager.println("Коллекция сохранена!");
                }
            }
        });
        consoleThread.start();

        try {
            port = 1234;
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress(port));
            ssc.configureBlocking(false);
            while (true) {
                SocketChannel sc = ssc.accept();
                if (sc != null) {
                    consoleManager.println("Получено соединение от: " + sc.socket().getRemoteSocketAddress());
                    ObjectInputStream objectIn = new ObjectInputStream(sc.socket().getInputStream());
                    ObjectOutputStream objectOut = new ObjectOutputStream(sc.socket().getOutputStream());
                    try {
                        while(true) {
                            Request request = (Request) objectIn.readObject();
                            if (request.isSubquery()) {
                                if (request.getCommandName().equals("check_id")) {
                                    objectOut.writeObject(new Response(collectionManager.isElementExist(Integer.parseInt(request.getArgument()))));
                                } else {
                                    objectOut.writeObject(new Response(collectionManager.checkPassportID(request.getArgument())));
                                }
                            } else {
                                consoleManager.println("Получен сериализованный объект от клиента");
                                consoleManager.println("Объект: " + request.getCommandName() + " " + request.getArgument());
                                if (request.getCommandName().equals("exit")) {
                                    consoleManager.println("Клиент завершил соединение");
                                    break;
                                }
                                String result = commandManager.commandExecute(request);
                                Response response = new Response(result);
                                objectOut.writeObject(response);
                                objectOut.flush();
                                objectOut.reset();
                                consoleManager.println("Отправлено обратно клиенту");
                            }
                        }
                    } catch (ClassNotFoundException e) {
                        System.err.println("Ошибка при чтении объекта от клиента: " + e.getMessage());
                    } finally {
                        objectIn.close();
                        objectOut.close();
                        sc.close();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}