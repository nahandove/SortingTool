package sorting;

import java.util.Scanner;

public class ConsoleHelper {
    static Scanner inputReader = new Scanner(System.in);
    public static String readString() {
        return inputReader.nextLine();
    }

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static boolean hasNext(){
        return inputReader.hasNextLine();
    }
}

