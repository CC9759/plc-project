import java.io.*;
import java.util.*;
import Phase1.*;

public class Jott {
    public static void main(String args[]) {
        String inputName = args[2];
        String outputName = args[3];
        String language = args[4];

        File input = new File(inputName);
        File output = new File(outputName);

        String data = "";

        try {
            Scanner myReader = new Scanner(input);
            while(myReader.hasNextLine()) {
                data += myReader.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}