package Phase2;
/**
 * This class is responsible for parsing and translating a Boolean Expression Node in the JottTree
 *
 * @author Jonathon LoTempio, Halle Masaryk, Celina Chen, Kaiming Zhang
 **/

import java.util.*;

public class ExpressionNode_Boolean extends ExpressionNode {
    IDKeywordNode myIDKeywordNode;
    ConstantNode myConstantNode;
    RelOpNode myRelOpNode;
    ExpressionNode myFirstExpressionNode_Boolean;
    ExpressionNode mySecondExpressionNode_Boolean;

    FunctionCallNode myFunctionCallNode;

    public ExpressionNode_Boolean(ArrayList<Token> inputTokens) throws Exception {
        super(null,null,null);
        if(inputTokens.size() == 0) {
            Token testToken = inputTokens.get(0);
            throw new ParserException(testToken, "ExpressionNode_Boolean constructor got an input list of 0 tokens.");
        }
        //[1,infty)
        if(inputTokens.size() == 1) {
            if(inputTokens.get(0).getTokenType() == TokenType.ID_KEYWORD) {
                if(inputTokens.get(0).getToken().equals("True") ||
                        inputTokens.get(0).getToken().equals("False")) {
                    myConstantNode = ConstantNode.parseConstantNode(inputTokens);
                }
                else {
                    myIDKeywordNode = IDKeywordNode.parseIdKeyWordNode(inputTokens);
                }
            }
            return;
        }
        if(inputTokens.get(0).getToken().equals("True") ||
                inputTokens.get(0).getToken().equals("False")) {
            myConstantNode = ConstantNode.parseConstantNode(inputTokens);
        }
        else {
            myFirstExpressionNode_Boolean = ExpressionNode.parseExpressionNode(inputTokens);
            myRelOpNode = RelOpNode.parseRelOpNode(inputTokens);
            mySecondExpressionNode_Boolean = ExpressionNode.parseExpressionNode(inputTokens);
        }
    }

    public ExpressionNode_Boolean(Token inputToken) {
        super(null,null,null);
        if(inputToken.getTokenType() == TokenType.ID_KEYWORD) {
            myIDKeywordNode = new IDKeywordNode(inputToken);
        }
        else {
            myConstantNode = new ConstantNode(inputToken);
        }
    }
    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    @Override
    public String convertToJott() {
        String returnMe = "";
        if(myFirstExpressionNode_Boolean != null) {
            returnMe = returnMe + myFirstExpressionNode_Boolean.convertToJott();
        }
        if(myRelOpNode != null) {
            returnMe = returnMe + myRelOpNode.convertToJott();
        }
        if(mySecondExpressionNode_Boolean != null) {
            returnMe = returnMe + mySecondExpressionNode_Boolean.convertToJott();
        }
        if(myIDKeywordNode != null) {
            returnMe = returnMe + myIDKeywordNode.convertToJott();
        }
        if(myFunctionCallNode != null) {
            returnMe = returnMe + myFunctionCallNode.convertToJott();
        }
        if(myConstantNode != null) {
            returnMe = returnMe + myConstantNode.convertToJott();
        }
        return returnMe;
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

