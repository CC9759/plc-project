package Phase2;

import java.util.List;

/**
 *
 */
public class IDKeywordNode implements JottTree {
    NodeType myType = NodeType.IDKEYWORD;
    private Token idKeyword;

    public IDKeywordNode(List<Token> inputTokens) {
        idKeyword = inputTokens.remove(0);
    }

    public String getValue() {
        return idKeyword.getToken();
    }

    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    public String convertToJott() {
        return idKeyword.getToken();
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