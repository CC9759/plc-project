package Phase2;
/**
 * This class is responsible for parsing and translating a Constant Node in the JottTree
 *
 * @author Jonathon LoTempio, Halle Masaryk, Celina Chen, Kaiming Zhang
 **/

import java.util.*;

public class ConstantNode implements JottTree {
    final NodeType myType = NodeType.CONSTANT;
    private InformationType type;
    private final String value;

    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */

    public static ConstantNode parseConstantNode(ArrayList<Token> inputTokens) throws Exception {
        return new ConstantNode(inputTokens.remove(0));
    }
    public ConstantNode(Token inputToken) {
        this.value = inputToken.getToken();

        //Is this a string?
        if(inputToken.getTokenType() == TokenType.STRING) {
            this.type = InformationType.STRING;
        }

        //Is this a boolean?
        else if(this.value.equals("True") ||
                this.value.equals("False")) {
            this.type = InformationType.BOOLEAN;
        }

        //Is this an Int/Double?
        else if(inputToken.getTokenType() == TokenType.NUMBER) {
            if(this.value.contains(".")) {
                this.type = InformationType.DOUBLE;
            }
            else {
                this.type = InformationType.INT;
            }
        }
    }

    public String convertToJott() {
        return this.value;
    }

    /**
     * Will output a string of this tree in Java
     * @return a string representing the Java code of this tree
     */
    public String convertToJava() {
        return this.value;
    }

    /**
     * Will output a string of this tree in C
     * @return a string representing the C code of this tree
     */
    public String convertToC() {
        return this.value;
    }

    /**
     * Will output a string of this tree in Python
     * @return a string representing the Python code of this tree
     */
    public String convertToPython() {
        return this.value;
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
        return this.value;
    }

    public InformationType getMyType() {
        return type;
    }

}
