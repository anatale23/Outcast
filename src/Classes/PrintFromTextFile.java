package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PrintFromTextFile {
    public static void print(String fileName){
        try {
            File nameFile = new File(fileName);
            Scanner lineRead = new Scanner(nameFile);
            String lineTxt;
            while (lineRead.hasNextLine()) {
                lineTxt= lineRead.nextLine();
                System.out.println(lineTxt);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
