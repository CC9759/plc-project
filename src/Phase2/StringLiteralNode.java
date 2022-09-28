package Phase2;

import java.util.*;
public class StringLiteralNode implements JottTree {
    NodeType myType = NodeType.STRINGLITERAL;

    private final ConstantNode child;

    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    public StringLiteralNode(List<Token> inputTokens) {
        // adding this remove to keep it so it ignores quotes
        inputTokens.remove(0);
        this.child = new ConstantNode(inputTokens);
    }

    public String convertToJott() {
        return "\"" + this.child.toString() + "\"";
    }

    /**
     * Will output a string of this tree in Java
     * @return a string representing the Java code of this tree
     */
    public String convertToJava() {
        return "\"" + this.child.toString() + "\"";
    }

    /**
     * Will output a string of this tree in C
     * @return a string representing the C code of this tree
     */
    public String convertToC() {
        return "\"" + this.child.toString() + "\"";
    }

    /**
     * Will output a string of this tree in Python
     * @return a string representing the Python code of this tree
     */
    public String convertToPython() {
        return "\"" + this.child.toString() + "\"";
    }

    /**
     * This will validate that the tree follows the semantic rules of Jott
     * Errors validating will be reported to System.err
     * @return true if valid Jott code; false otherwise
     */
    public boolean validateTree() {
        return true;
    }

    public String toString() {
        return "\"" + this.child.toString() + "\"";
    }

    public NodeType getMyType() {
        return myType;
    }

}
