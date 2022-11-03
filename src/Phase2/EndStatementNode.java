package Phase2;
/**
 * This class is responsible for parsing and translating an End Statement Node in the JottTree.
 *
 * @author Jonathon LoTempio, Halle Masaryk, Celina Chen, Kaiming Zhang
 **/

import java.util.*;

public class EndStatementNode implements JottTree {

    public static EndStatementNode parseEndExpressionNode(ArrayList<Token> tokens){
        return new EndStatementNode(tokens.remove(0));
    }

    private EndStatementNode(Token inputToken) {
        //doesn't actually have any information that can be changed.
    }
    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    public String convertToJott() {
        return ";";
    }

    /**
     * Will output a string of this tree in Java
     * @return a string representing the Java code of this tree
     */
    public String convertToJava() {
        return ";";
    }

    /**
     * Will output a string of this tree in C
     * @return a string representing the C code of this tree
     */
    public String convertToC() {
        return ";";
    }

    /**
     * Will output a string of this tree in Python
     * @return a string representing the Python code of this tree
     */
    public String convertToPython() {
        return "\n";
    }

    /**
     * This will validate that the tree follows the semantic rules of Jott
     * Errors validating will be reported to System.err
     * @return true if valid Jott code; false otherwise
     */
    public boolean validateTree() {
        return true;
    }
}

