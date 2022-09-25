package Phase2;

import java.util.*;

public class FunctionReturnNode {
    private final Token token;
    private final InformationType type;
    private final TypeNode typeNode;

    public FunctionReturnNode(List<Token> inputTokens) {
        token = inputTokens.remove(0);
        if(token.getToken() == "Void") {
            type = InformationType.VOID;
            // how else better to do this?
            typeNode = null;
        }
        else{
            typeNode = new TypeNode(inputTokens);
            type = typeNode.getType();
        }
    }

    public String getValue() {
        return token.getToken();
    }
    
    public InformationType getType() {
        return type;
    }

    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    public String convertToJott() {
        if(type == InformationType.VOID){
            return "Void";
        }
        else{
            return typeNode.convertToJott();
        }
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
}
