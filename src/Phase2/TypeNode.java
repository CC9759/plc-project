package Phase2;

import Phase2Real.InformationType;
import Phase2Real.JottTree;
import Phase2Real.NodeType;
import Phase2Real.Token;

import java.util.*;

public class TypeNode implements JottTree {
    NodeType myType = NodeType.TYPE;
    private final Token token;
    private final String value;
    private final InformationType type;

    public TypeNode(List<Token> inputTokens) {
        token = inputTokens.remove(0);
        value = token.getToken();
        String string = "Double | Integer | String | Boolean";
        if(value.equals("Double")) {
            type = InformationType.DOUBLE;
        }
        else if(value.equals("Integer")){
            type = InformationType.INT;
        }
        else if(value.equals("String")){
            type = InformationType.STRING;
        }
        else if(value.equals("Boolean")){
            type = InformationType.BOOLEAN;
        }
        else{
            //temporary
            type = InformationType.BOOLEAN;
            //error?
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
