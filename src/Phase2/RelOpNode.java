package Phase2;
/**
 * This class is responsible for parsing and translating a RelOp Node in the JottTree
 *
 * @author Jonathon LoTempio, Halle Masaryk, Celina Chen, Kaiming Zhang
 **/

import java.util.*;

public class RelOpNode implements JottTree {
    final NodeType myType = NodeType.RELOP;
    private final Token token;

    private RelOpNode(Token inputToken) {
        this.token = inputToken;
    }
    
    public static RelOpNode parseRelOpNode(ArrayList<Token> tokens) throws Exception{
        Token inputToken = tokens.remove(0);
        return new RelOpNode(inputToken);
    }

    public String getValue() {
        return token.getToken();
    }

    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    public String convertToJott() {
        return token.getToken();
    }

    /**
     * Will output a string of this tree in Java
     * @return a string representing the Java code of this tree
     */
    public String convertToJava() {
        return null;
    }

    /**
     * Will output a string of this tree in C
     * @return a string representing the C code of this tree
     */
    public String convertToC() {
        return null;
    }

    /**
     * Will output a string of this tree in Python
     * @return a string representing the Python code of this tree
     */
    public String convertToPython() {
        return null;
    }

    /**
     * This will validate that the tree follows the semantic rules of Jott
     * Errors validating will be reported to System.err
     * @return true if valid Jott code; false otherwise
     */
    public boolean validateTree() {
        return true;
    }

    public NodeType getMyType() {
        return myType;
    }
}
