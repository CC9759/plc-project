package Phase2;
/**
 * This class is responsible for parsing and translating an ID Keyword Node in the JottTree
 *
 * @author Jonathon LoTempio, Halle Masaryk, Celina Chen, Kaiming Zhang
 **/

import java.util.*;

/**
 *
 */
public class IDKeywordNode implements JottTree {
    final NodeType myType = NodeType.IDKEYWORD;
    private final String value;

    public IDKeywordNode(Token token){this.value = token.getToken();}
    public static IDKeywordNode parseIdKeyWordNode(ArrayList<Token> tokens) throws Exception{
        Token token = tokens.remove(0);
        return new IDKeywordNode(token);
    }

    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    public String convertToJott() {
        return this.value;
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