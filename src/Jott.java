import java.io.*;
import java.util.*;

import Phase1.*;
import Phase2.*;

public class Jott {
    public static void main(String args[]) {
        String inputName = args[1];
        String outputName = args[2];
        String language = args[3];
        String result = "";

        ArrayList<Token> tokens =  JottTokenizer.tokenize(inputName);
        ProgramNode root = ProgramNode.parseProgramNode(tokens);

        switch(language){
            case "Java":
                result = root.convertToJava();
                result.replaceFirst("Main", outputName.split(".")[0]);
                break;
            case "Python":
                result = root.convertToPython();
                break;
            case "C":
                result = root.convertToC();
                break;
            case "Jott":
                result = root.convertToJott();
                break;
        }

        try {
            BufferedWriter f_writer = new BufferedWriter(new FileWriter(outputName));
            f_writer.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
    }
}