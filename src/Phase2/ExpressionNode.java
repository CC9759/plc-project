package Phase2;
/*
  This class is responsible for parsing and translating an Expression Node in the JottTree

  @author Jonathon LoTempio, Halle Masaryk, Celina, Kaiming Zhang
 */

import java.util.*;

public class ExpressionNode implements JottTree {

    public HashMap<String, InformationType> localSymbolTable;
    ExpressionNode firstExpressionNode;
    ExpressionNode secondExpressionNode;
    JottTree operationNode;
    FunctionCallNode functionCallNode;
    IDKeywordNode idKeywordNode;
    ConstantNode constantNode;
    public InformationType WhatAmI() throws Exception {
        if(idKeywordNode != null) {
            //return localSymbolTable.get(idKeywordNode.getValue());
            return InformationType.VOID; //TODO SAME AS ABOVE
        }
        if(functionCallNode != null) {
            //return localSymbolTable.get(idKeywordNode.getValue());
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

    public ExpressionNode(FunctionCallNode input, HashMap<String, InformationType> localSymbolTable) {
        functionCallNode = input;
        this.localSymbolTable = localSymbolTable;
    }

    public ExpressionNode(IDKeywordNode input, HashMap<String, InformationType> localSymbolTable) {
        idKeywordNode = input;
        this.localSymbolTable = localSymbolTable;
    }

    public ExpressionNode(ConstantNode input, HashMap<String, InformationType> localSymbolTable) {
        constantNode = input;
        this.localSymbolTable = localSymbolTable;
    }

    public ExpressionNode(ExpressionNode first, JottTree op, ExpressionNode second, HashMap<String, InformationType> localSymbolTable) {
        firstExpressionNode = first;
        operationNode = op;
        secondExpressionNode = second;
        this.localSymbolTable = localSymbolTable;
    }

    public static ExpressionNode parseExpressionNode(ArrayList<Token> inputList, HashMap<String, InformationType> localSymbolTable) throws Exception {
        ExpressionNode firstExpression;
        JottTree op;
        ExpressionNode secondExpression;

        if(inputList.get(0).getTokenType() == TokenType.ID_KEYWORD) {
            if(inputList.get(1).getTokenType() == TokenType.L_BRACKET) {
                firstExpression = new ExpressionNode(FunctionCallNode.parseFunctionCallNode(inputList, localSymbolTable), localSymbolTable);
            }
            else if(inputList.get(0).getToken().equals("True") ||
                    inputList.get(0).getToken().equals("False")) {
                firstExpression = new ExpressionNode(ConstantNode.parseConstantNode(inputList), localSymbolTable);
            }
            else {
                firstExpression = new ExpressionNode(IDKeywordNode.parseIdKeyWordNode(inputList), localSymbolTable);
            }
        }
        else if(inputList.get(0).getTokenType() == TokenType.NUMBER) {
            firstExpression = new ExpressionNode(ConstantNode.parseConstantNode(inputList), localSymbolTable);
        }
        else if(inputList.get(0).getTokenType() == TokenType.STRING) {
            firstExpression = new ExpressionNode(ConstantNode.parseConstantNode(inputList), localSymbolTable);
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

        secondExpression = ExpressionNode.parseExpressionNode(inputList, localSymbolTable);
        return new ExpressionNode(firstExpression, op, secondExpression, localSymbolTable);
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
    // TODO: Why these extra spaces?
    public String convertToJava() {
        if(idKeywordNode != null) {
            return " " + idKeywordNode.convertToJava() + " ";
        }
        if(constantNode != null) {
            return " " + constantNode.convertToJava() + " ";
        }
        if(functionCallNode != null) {
            return " " + functionCallNode.convertToJava() + " ";
        }
        return firstExpressionNode.convertToJava() + " " + operationNode.convertToJava() + " " + secondExpressionNode.convertToJava();
    }

    /**
     * Will output a string of this tree in C
     * @return a string representing the C code of this tree
     */
    public String convertToC() {
        if(idKeywordNode != null) {
            return " " + idKeywordNode.convertToC() + " ";
        }
        if(constantNode != null) {
            return " " + constantNode.convertToC() + " ";
        }
        if(functionCallNode != null) {
            return " " + functionCallNode.convertToC() + " ";
        }
        return firstExpressionNode.convertToC() + " " + operationNode.convertToC() + " " + secondExpressionNode.convertToC();
    }

    /**
     * Will output a string of this tree in Python
     * @return a string representing the Python code of this tree
     */
    public String convertToPython() {
        if(idKeywordNode != null) {
            return " " + idKeywordNode.convertToPython() + " ";
        }
        if(constantNode != null) {
            return " " + constantNode.convertToPython() + " ";
        }
        if(functionCallNode != null) {
            return " " + functionCallNode.convertToPython() + " ";
        }
        return firstExpressionNode.convertToPython() + " " + operationNode.convertToPython() + " " + secondExpressionNode.convertToPython();
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
