import java.io.*;
import java.util.*;

import Phase1.*;
import Phase2.*;

public class Jott {
    public static void main(String args[]) throws Exception {
        String inputName = args[1];
        String outputName = args[2];
        String language = args[3].toLowerCase();
        String result = "";

        ArrayList<Token> tokens =  JottTokenizer.tokenize(inputName);
        ProgramNode root = ProgramNode.parseProgramNode(tokens);
        root.validateTree();

        switch(language){
            case "java":
                result = root.convertToJava();
                result.replaceFirst("Main", outputName.split(".")[0]);
                break;
            case "python":
                result = root.convertToPython();
                break;
            case "c":
                result = root.convertToC();
                break;
            case "jott":
                result = root.convertToJott();
                break;
        }

        try {
            BufferedWriter f_writer = new BufferedWriter(new FileWriter(outputName));
            f_writer.write(result);
            f_writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
    }
}