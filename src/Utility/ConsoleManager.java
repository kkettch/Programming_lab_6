package Utility;

import java.util.Scanner;

public class ConsoleManager {

    private Scanner sc = new Scanner(System.in);

    public void println(Object msg) {
        System.out.println(msg);
    }

    public void print(Object msg){
        System.out.print(msg);
    }

    public String getLine(){
        if(!sc.hasNextLine()) {
            System.exit(0);
        }
        return sc.nextLine();
    }

    public boolean ScHasNext() {
        return sc.hasNext();
    }

    public String gerString() {
        if (!sc.hasNextLine()){
            exit();
        }
        return sc.nextLine();
    }

    public void exit() {
        System.exit(0);
    }
}
