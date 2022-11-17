import Phase2.*;
import Phase1.JottTokenizer;
import java.util.*;

public class Phase2Testing {
    public static void main(String[] args) throws Exception{

        System.out.println("testing on the function definition in phase1Example");
        ArrayList<Token> tokens =  JottTokenizer.tokenize("phase3TestCases/funcReturnInExpr.jott");
        ProgramNode result = ProgramNode.parseProgramNode(tokens);
        if(result != null) {
            System.out.println(result.convertToJott());
            Boolean valid = result.validateTree();

            System.out.printf("valid:" + valid);
        }
        else {
            System.out.println("result is null, exception thrown");

        }
    }
}

