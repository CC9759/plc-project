package Phase2;
/*
  This class is responsible for parsing and translating an Expression Node in the JottTree

  @author Jonathon LoTempio, Halle Masaryk, Celina, Kaiming Zhang
 */

import java.util.*;

public class ExpressionNode implements JottTree {

    public HashMap<String, InformationType> localSymbolTable;

    public HashMap<String, Boolean> initialized;
    
    public Token token;
    ExpressionNode firstExpressionNode;
    ExpressionNode secondExpressionNode;
    JottTree operationNode;
    FunctionCallNode functionCallNode;
    IDKeywordNode idKeywordNode;
    ConstantNode constantNode;
    public InformationType WhatAmI(){
        try {
            if (idKeywordNode != null) {
                //if(!localSymbolTable.containsKey(idKeywordNode.getValue())){throw new ParserException(token, "Variable " + idKeywordNode.getValue()+ " undefined", true);}
                //if(!initialized.get(idKeywordNode.getValue())){throw new ParserException(token, "Variable " + idKeywordNode.getValue()+ " uninitialized", true);}
                return localSymbolTable.get(idKeywordNode.getValue());
                //return InformationType.VOID; //TODO SAME AS ABOVE
            }
            if (functionCallNode != null) {
                return ProgramNode.globalSymbolTable.get(functionCallNode.id.value).returnType;
                //return InformationType.VOID; //TODO SAME AS ABOVE
            }
            if (constantNode != null) {
                return constantNode.getMyType();
            }
            if (firstExpressionNode.WhatAmI() != secondExpressionNode.WhatAmI()) {
                throw new Exception("Invalid expression types joined by operation");
            }
            return firstExpressionNode.WhatAmI();
        }catch (Exception exception){
          System.err.println(exception.getMessage());
            return null;
        }
    }

    public ExpressionNode(FunctionCallNode input, HashMap<String, InformationType> localSymbolTable, Token token, HashMap<String, Boolean> initialized) {
        this.token = token;
        functionCallNode = input;
        this.localSymbolTable = localSymbolTable;
        this.initialized = initialized;
    }

    public ExpressionNode(IDKeywordNode input, HashMap<String, InformationType> localSymbolTable, Token token, HashMap<String, Boolean> initialized) {
        this.token = token;
        idKeywordNode = input;
        this.localSymbolTable = localSymbolTable;
        this.initialized = initialized;
    }

    public ExpressionNode(ConstantNode input, HashMap<String, InformationType> localSymbolTable, Token token, HashMap<String, Boolean> initialized) {
        this.token = token;
        constantNode = input;
        this.localSymbolTable = localSymbolTable;
        this.initialized = initialized;
    }

    public ExpressionNode(ExpressionNode first, JottTree op, ExpressionNode second, HashMap<String, InformationType> localSymbolTable, Token token, HashMap<String, Boolean> initialized) {
        this.token = token;
        firstExpressionNode = first;
        operationNode = op;
        secondExpressionNode = second;
        this.localSymbolTable = localSymbolTable;
        this.initialized = initialized;
    }

    public static ExpressionNode parseExpressionNode(ArrayList<Token> inputList, HashMap<String, InformationType> localSymbolTable, HashMap<String, Boolean> initialized) throws Exception {
        ExpressionNode firstExpression;
        JottTree op;
        ExpressionNode secondExpression;

        if(inputList.get(0).getTokenType() == TokenType.ID_KEYWORD) {
            if(inputList.get(1).getTokenType() == TokenType.L_BRACKET) {
                firstExpression = new ExpressionNode(FunctionCallNode.parseFunctionCallNode(inputList, localSymbolTable, initialized), localSymbolTable, inputList.get(0), initialized);
            }
            else if(inputList.get(0).getToken().equals("True") ||
                    inputList.get(0).getToken().equals("False")) {
                firstExpression = new ExpressionNode(ConstantNode.parseConstantNode(inputList), localSymbolTable, inputList.get(0), initialized);
            }
            else {
                firstExpression = new ExpressionNode(IDKeywordNode.parseIdKeyWordNode(inputList), localSymbolTable, inputList.get(0), initialized);
            }
        }
        else if(inputList.get(0).getTokenType() == TokenType.NUMBER) {
            firstExpression = new ExpressionNode(ConstantNode.parseConstantNode(inputList), localSymbolTable, inputList.get(0), initialized);
        }
        else if(inputList.get(0).getTokenType() == TokenType.STRING) {
            firstExpression = new ExpressionNode(ConstantNode.parseConstantNode(inputList), localSymbolTable, inputList.get(0), initialized);
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

        secondExpression = ExpressionNode.parseExpressionNode(inputList, localSymbolTable, initialized);
        return new ExpressionNode(firstExpression, op, secondExpression, localSymbolTable, inputList.get(0), initialized);
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
        try {
            if(this.WhatAmI() == InformationType.STRING &&  operationNode.convertToC().equals("+") ){
                return "strcat(" + firstExpressionNode.convertToC() + "," + secondExpressionNode.convertToC() + ")";
            }
        } catch (Exception e) {
            e.printStackTrace();
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
    public boolean validateTree() throws Exception {
        boolean firstExpressionNodeBool = true;
        boolean secondExpressionNodeBool = true;
        boolean functionCallNodeBool = true;
        boolean idKeyWordNodeBool = true;
        boolean constantNodeBool = true;
        InformationType type = null;

        if(firstExpressionNode!=null){
            firstExpressionNodeBool = firstExpressionNode.validateTree();
            if(type != null && type != firstExpressionNode.WhatAmI()) {
                return false;
            }
            else{
                type = firstExpressionNode.WhatAmI();
            }

        }
        if(secondExpressionNode!=null){
            secondExpressionNodeBool = secondExpressionNode.validateTree();
            if(type != null && type != secondExpressionNode.WhatAmI()) {
                return false;
            }
            else{
                type = secondExpressionNode.WhatAmI();
            }
        }
        if(functionCallNode!=null){
            functionCallNodeBool = functionCallNode.validateTree();
            if (!functionCallNodeBool) { return functionCallNodeBool;}
            if(type != null && type != ProgramNode.globalSymbolTable.get(functionCallNode.id.value).returnType) {
                return false;
            }
            else{
                type = ProgramNode.globalSymbolTable.get(functionCallNode.id.value).returnType;
            }
        }
        if(idKeywordNode!=null){
            idKeyWordNodeBool = idKeywordNode.validateTree();
            if(type != null && type != localSymbolTable.get(idKeywordNode.value)) {
                return false;
            }
            else{
                type = localSymbolTable.get(idKeywordNode.value);
            }
        }
        if(constantNode!=null){
            constantNodeBool = constantNode.validateTree();
            if(type != null && type != constantNode.getMyType()) {
                return false;
            }
            else{
                type = constantNode.getMyType();
            }
        }

        return(firstExpressionNodeBool&&secondExpressionNodeBool&&functionCallNodeBool&&idKeyWordNodeBool&&constantNodeBool);

    }
}
