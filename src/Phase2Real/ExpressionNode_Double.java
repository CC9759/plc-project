package Phase2Real;

import java.util.*;

public class ExpressionNode_Double extends ExpressionNode {
    IDKeywordNode myIDKeywordNode;
    ConstantNode myConstantNode;
    OpNode myOpNode;
    ExpressionNode_Double myFirstExpressionNode_Double;
    ExpressionNode_Double mySecondExpressionNode_Double;

    FunctionCallNode myFunctionCallNode;

    public ExpressionNode_Double(ArrayList<Token> inputTokens) {
        if(inputTokens.size() < 2) {
            if(inputTokens.get(0).getTokenType() == TokenType.ID_KEYWORD) {
                myIDKeywordNode = IDKeywordNode.parseIdKeyWordNode(inputTokens);
            }
            else if(inputTokens.get(0).getTokenType() == TokenType.NUMBER) {
                myConstantNode = ConstantNode.parseConstantNode(inputTokens);
            }
        }
        else if(inputTokens.get(1).getTokenType() == TokenType.MATH_OP) {
            myFirstExpressionNode_Double = new ExpressionNode_Double(inputTokens.remove(0));
            myOpNode = OpNode.parseOpNode(inputTokens);
            mySecondExpressionNode_Double = new ExpressionNode_Double(inputTokens);
        }
        else if(inputTokens.get(1).getTokenType() == TokenType.L_BRACKET) {
            FunctionCallNode tempFuncCallNode = FunctionCallNode.parseFunctionCallNode(inputTokens);
            if(inputTokens.get(0).getTokenType() == TokenType.MATH_OP) {
                myFirstExpressionNode_Double = new ExpressionNode_Double(tempFuncCallNode);
                myOpNode = OpNode.parseOpNode(inputTokens);
                mySecondExpressionNode_Double = new ExpressionNode_Double(inputTokens);
            }
            else {
                myFunctionCallNode = tempFuncCallNode;
            }
        }
        else {
            if(inputTokens.get(0).getTokenType() == TokenType.ID_KEYWORD) {
                myIDKeywordNode = IDKeywordNode.parseIdKeyWordNode(inputTokens);
            }
            else if(inputTokens.get(0).getTokenType() == TokenType.NUMBER) {
                myConstantNode = ConstantNode.parseConstantNode(inputTokens);
            }
        }
    }
    public ExpressionNode_Double(Token inputToken) {
        if(inputToken.getTokenType() == TokenType.ID_KEYWORD) {
            myIDKeywordNode = new IDKeywordNode(inputToken);
        }
        else if(inputToken.getTokenType() == TokenType.NUMBER) {
            myConstantNode = new ConstantNode(inputToken);
        }

    }
    public ExpressionNode_Double(FunctionCallNode inputNode) {
        myFunctionCallNode = inputNode;
    }
    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    @Override
    public String convertToJott() {
        if(myOpNode != null) {
            return myFirstExpressionNode_Double.convertToJott() + " " +
                    myOpNode.convertToJott() + " " +
                    mySecondExpressionNode_Double.convertToJott();
        }
        if(myIDKeywordNode != null) {
            return myIDKeywordNode.convertToJott();
        }
        if(myConstantNode != null) {
            return myConstantNode.convertToJott();
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

