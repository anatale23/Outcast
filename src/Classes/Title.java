package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import Classes.PrintFromTextFile;
public class Title {
    public static void printTitle() {
        System.out.print("\033[H\033[2J"+"\u001B[40m"+"\u001B[97m");
        System.out.flush();
        PrintFromTextFile.print("src/title.txt");
        System.out.print("\n");
    }
    public static void printDescription() {
        PrintFromTextFile.print("src/description.txt");
        System.out.print("\n");
    }
    public static void timer(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
