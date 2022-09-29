package Phase2Real;

import java.util.*;

public class ExpressionNode_Boolean extends ExpressionNode {
    IDKeywordNode myIDKeywordNode;
    ConstantNode myConstantNode;
    RelOpNode myRelOpNode;
    ExpressionNode_Boolean myFirstExpressionNode_Boolean;
    ExpressionNode_Boolean mySecondExpressionNode_Boolean;

    //FunctionCallNode myFunctionCallNode;

    public ExpressionNode_Boolean(ArrayList<Token> inputTokens) {
        if(inputTokens.get(1).getTokenType() == TokenType.REL_OP) {
            myFirstExpressionNode_Boolean = new ExpressionNode_Boolean(inputTokens.remove(0));
            myRelOpNode = new RelOpNode(inputTokens);
            mySecondExpressionNode_Boolean = new ExpressionNode_Boolean(inputTokens);
        }
        if(inputTokens.get(1).getTokenType() == TokenType.L_BRACKET) {
            //TODO FUNCTION CALL CONSTRUCTOR HERE
            //FunctionCallNode tempFuncCallNode = new FunctionCallNode(inputTokens);
            if(inputTokens.get(0).getTokenType() == TokenType.REL_OP) {
                //myFirstExpressionNode_Boolean = new ExpressionNode_Boolean(tempFuncCallNode);
                myRelOpNode = new RelOpNode(inputTokens);
                mySecondExpressionNode_Boolean = new ExpressionNode_Boolean(inputTokens);
            }
            else {
                //myFunctionCallNode = tempFuncCallNode;
            }
        }
    }
    public ExpressionNode_Boolean(Token inputToken) {
        
    }
    /*
    public ExpressionNode_Boolean(FunctionCallNode inputNode) {
        myFunctionCallNode = inputNode;
    }
     */
    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    public String convertToJott() {
        if(myRelOpNode != null) {
            return myFirstExpressionNode_Boolean.convertToJott() +
                    myRelOpNode.convertToJott() +
                    mySecondExpressionNode_Boolean.convertToJott();
        }
        if(myIDKeywordNode != null) {
            return myIDKeywordNode.convertToJott();
        }
        if(myConstantNode != null) {
            return myConstantNode.convertToJott();
        }
        return null; //return myFunctionCallNode.convertToJott();
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
