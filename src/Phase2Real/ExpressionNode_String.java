package Phase2Real;

import java.util.*;

public class ExpressionNode_String extends ExpressionNode {
    IDKeywordNode myIDKeywordNode;
    ConstantNode myConstantNode;
    FunctionCallNode myFunctionCallNode;

    public ExpressionNode_String(ArrayList<Token> inputTokens) {
        if(inputTokens.get(0).getToken().equals("\"")) {
            inputTokens.remove(0);
            myConstantNode = ConstantNode.parseConstantNode(inputTokens);
            inputTokens.remove(0);
        }
        else if(inputTokens.get(1).getTokenType() == TokenType.L_BRACKET) {
            myFunctionCallNode = FunctionCallNode.parseFunctionCallNode(inputTokens);
        }
        else {
            myIDKeywordNode = IDKeywordNode.parseIdKeyWordNode(inputTokens);
        }
    }
    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    public String convertToJott() {
        if(myIDKeywordNode != null) {
            return myIDKeywordNode.convertToJott();
        }
        if(myConstantNode != null) {
            return "\"" + myConstantNode.convertToJott() + "\""; //Unsure if quotes are needed
        }
        return myFunctionCallNode.convertToJott();
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
