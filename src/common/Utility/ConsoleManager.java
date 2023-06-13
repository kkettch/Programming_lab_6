package common.Utility;
import java.util.Scanner;

/**
 * Класс для работы с консолью
 * @author maria
 */
public class ConsoleManager {
    private final Scanner sc = new Scanner(System.in);
    public void println(Object msg) {
        System.out.println(msg);
    }
    public void print(Object msg){
        System.out.print(msg);
    }
    public boolean ScHasNext() {
        return sc.hasNextLine();
    }
    public String getString() {
        if (!sc.hasNextLine()){
            exit();
        }
        return sc.nextLine();
    }
    public void exit() {
        System.exit(0);
    }
}