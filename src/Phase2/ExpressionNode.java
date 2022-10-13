package Phase2;
/**
 * This class is responsible for parsing and translating an Expression NodeIf Node in the JottTree
 *
 * @author Jonathon LoTempio, Halle Masaryk, Celina, Kaiming Zhang
 **/

import java.beans.Expression;
import java.util.*;

public class ExpressionNode implements JottTree {
    ExpressionNode firstExpressionNode;
    ExpressionNode secondExpressionNode;
    JottTree operationNode;
    FunctionCallNode functionCallNode;
    IDKeywordNode idKeywordNode;
    ConstantNode constantNode;
    public InformationType WhatAmI() throws Exception {
        if(idKeywordNode != null) {
            return InformationType.VOID; //TODO PHASE 3 SYMBOL TABLE
        }
        if(functionCallNode != null) {
            return InformationType.VOID; //TODO SAME AS ABOVE
        }
        if(constantNode != null) {
            return constantNode.getMyType();
        }
        if(firstExpressionNode.WhatAmI() != secondExpressionNode.WhatAmI()) {
            throw new Exception("Invalid expression types joined by operation");
        }
        return firstExpressionNode.WhatAmI();
    }

    public ExpressionNode(FunctionCallNode input) {
        functionCallNode = input;
    }

    public ExpressionNode(IDKeywordNode input) {
        idKeywordNode = input;
    }

    public ExpressionNode(ConstantNode input) {
        constantNode = input;
    }

    public ExpressionNode(ExpressionNode first, JottTree op, ExpressionNode second) {
        firstExpressionNode = first;
        operationNode = op;
        secondExpressionNode = second;
    }

    public static ExpressionNode parseExpressionNode(ArrayList<Token> inputList) throws Exception {
        ExpressionNode firstExpression = null;
        JottTree op = null;
        ExpressionNode secondExpression = null;

        if(inputList.get(0).getTokenType() == TokenType.ID_KEYWORD) {
            if(inputList.get(1).getTokenType() == TokenType.L_BRACKET) {
                firstExpression = new ExpressionNode(FunctionCallNode.parseFunctionCallNode(inputList));
            }
            else if(inputList.get(0).getToken().equals("True") ||
                    inputList.get(0).getToken().equals("False")) {
                firstExpression = new ExpressionNode(ConstantNode.parseConstantNode(inputList));
            }
            else {
                firstExpression = new ExpressionNode(IDKeywordNode.parseIdKeyWordNode(inputList));
            }
        }
        else if(inputList.get(0).getTokenType() == TokenType.NUMBER) {
            firstExpression = new ExpressionNode(ConstantNode.parseConstantNode(inputList));
        }
        else if(inputList.get(0).getTokenType() == TokenType.STRING) {
            firstExpression = new ExpressionNode(ConstantNode.parseConstantNode(inputList));
        }
        else {
            throw new Exception("Invalid start of expression. Expected ID or Number but got " + inputList.get(0).getTokenType());
        }

        if(inputList.get(0).getTokenType() == TokenType.MATH_OP) {
            op = OpNode.parseOpNode(inputList);
        }
        else if(inputList.get(0).getTokenType() == TokenType.REL_OP) {
            op = RelOpNode.parseRelOpNode(inputList);
        }
        else {
            return firstExpression;
        }

        secondExpression = ExpressionNode.parseExpressionNode(inputList);
        return new ExpressionNode(firstExpression, op, secondExpression);
    }

    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    public String convertToJott() {
        if(idKeywordNode != null) {
            return " " + idKeywordNode.convertToJott() + " ";
        }
        if(constantNode != null) {
            return " " + constantNode.convertToJott() + " ";
        }
        if(functionCallNode != null) {
            return " " + functionCallNode.convertToJott() + " ";
        }
        return firstExpressionNode.convertToJott() + " " + operationNode.convertToJott() + " " + secondExpressionNode.convertToJott();
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
