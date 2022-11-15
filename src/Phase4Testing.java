import Phase1.*;
import Phase2.*;

import java.io.*;
import java.util.ArrayList;

public class Phase4Testing {
    public static void main(String[] args) {
        ArrayList<Token> tokens = JottTokenizer.tokenize("./src/phase4testfile.txt");
        JottTree root = JottParser.parse(tokens);
        System.out.println(root.convertToJott());
        System.out.println("====================================================");
        System.out.println(root.convertToJava());
        System.out.println("====================================================");
        System.out.println(root.convertToPython());
        System.out.println("====================================================");
        System.out.println(root.convertToC());
        System.out.print("");
    }
}
