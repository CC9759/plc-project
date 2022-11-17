import java.io.*;
import java.util.*;
import Phase1.*;
import Phase2.JottTree;
import Phase2.ProgramNode;
import Phase2.Token;

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
            ArrayList<Token> tokens = JottTokenizer.tokenize(data);
            JottTree root = JottParser.parse(tokens);
            String new_file;
            switch(language) {
                case "c": new_file = root.convertToC();
                case "java": new_file = root.convertToJava();
                case "jott": new_file = root.convertToJott();
                case "python": new_file = root.convertToPython();
            }




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}