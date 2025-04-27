package cz.jpcz.cryptomanager.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DebugManager {
    public static boolean DEBUG = false;

    public static void setDebug(boolean debug) {
        DEBUG = debug;
    }

    public static boolean isDebug() {
        return DEBUG;
    }

    public static void printHeader(String message) {
        if (DEBUG) {
            System.out.println("\n" + ConsoleColor.YELLOW + getFormattedTime() + message + ConsoleColor.RESET);
        }
    }

    public static void print(String message) {
        if (DEBUG) {
            System.out.println(getFormattedTime() + message + ConsoleColor.RESET);
        }
    }

    private static String getFormattedTime() {
        return "[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "] ";
    }

    public static void printError(String message) {
        System.out.println(ConsoleColor.RED + getFormattedTime() + message + ConsoleColor.RESET);
    }
}