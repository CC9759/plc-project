package Phase2;
/**
 /**
 * This class is responsible for parsing and translating an Assignment Node in the JottTree
 *
 * @author Jonathon LoTempio, Halle Masaryk, Celina Chen, Kaiming Zhang
 **/

import java.util.*;

public class AssignmentNode implements JottTree {
    String myType;
    final IDKeywordNode myIDKeywordNode;
    final ExpressionNode myExpressionNode;
    public HashMap<String, InformationType> localSymbolTable;
    private AssignmentNode(Token type, IDKeywordNode id, ExpressionNode expression, HashMap<String, InformationType> localSymbolTable){//}, EndStatementNode endStatement){
        if(type != null) {
            this.myType = type.getToken();
        }
        this.myIDKeywordNode = id;
        this.myExpressionNode = expression;
        this.localSymbolTable = localSymbolTable;
    }

    static AssignmentNode parseAssignmentNode(ArrayList<Token> tokens, HashMap<String, InformationType> localSymbolTable) throws Exception{
        String firstTokenAsString = tokens.get(0).getToken();
        Token typeToken = null;
        if(firstTokenAsString.equals("Boolean") ||
                firstTokenAsString.equals("Double") ||
                firstTokenAsString.equals("Integer") ||
                firstTokenAsString.equals("String")) {
            typeToken = tokens.remove(0);
        }

        IDKeywordNode idKeywordNode = IDKeywordNode.parseIdKeyWordNode(tokens);
        ParserUtils.removeToken(tokens, TokenType.ASSIGN);
        ExpressionNode expressionNode = ExpressionNode.parseExpressionNode(tokens, localSymbolTable);
        InformationType informationType = InformationType.VOID;
        if(typeToken != null) {
            if (Objects.equals(typeToken.getToken(), "Boolean")) {
                informationType = InformationType.BOOLEAN;
            } else if (Objects.equals(typeToken.getToken(), "Double")) {
                informationType = InformationType.DOUBLE;
            } else if (Objects.equals(typeToken.getToken(), "Integer")) {
                informationType = InformationType.INT;
            } else if (Objects.equals(typeToken.getToken(), "String")) {
                informationType = InformationType.STRING;
            }
            localSymbolTable.put(idKeywordNode.value, informationType);
        }
        return new AssignmentNode(typeToken, idKeywordNode, expressionNode, localSymbolTable);
    }
    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    public String convertToJott() {
        StringBuilder finalString = new StringBuilder();
        if(myType != null) {
            finalString.append(myType);
            finalString.append(" ");
        }
        finalString.append(myIDKeywordNode.convertToJott());
        finalString.append(" = ");
        finalString.append(myExpressionNode.convertToJott());
        return finalString.toString();
    }

    /**
     * Will output a string of this tree in Java
     * @return a string representing the Java code of this tree
     */
    public String convertToJava() {
        StringBuilder finalString = new StringBuilder();
        if(myType != null) {
            finalString.append(myType);
            finalString.append(" ");
        }
        finalString.append(myIDKeywordNode.convertToJava());
        finalString.append(" = ");
        finalString.append(myExpressionNode.convertToJava());
        return finalString.toString();
    }

    /**
     * Will output a string of this tree in C
     * @return a string representing the C code of this tree
     */
    public String convertToC() {
        StringBuilder finalString = new StringBuilder();
        if(myType != null) {
            finalString.append(myType);
            finalString.append(" ");
        }
        finalString.append(myIDKeywordNode.convertToC());
        finalString.append(" = ");
        finalString.append(myExpressionNode.convertToC());
        return finalString.toString();
    }

    /**
     * Will output a string of this tree in Python
     * @return a string representing the Python code of this tree
     */
    public String convertToPython() {
        StringBuilder finalString = new StringBuilder();
        finalString.append(myIDKeywordNode.convertToPython());
        finalString.append(" = ");
        finalString.append(myExpressionNode.convertToPython());
        return finalString.toString();
    }

    /**
     * This will validate that the tree follows the semantic rules of Jott
     * Errors validating will be reported to System.err
     * @return true if valid Jott code; false otherwise
     */
    public boolean validateTree() throws Exception {
        if(localSymbolTable.containsKey(myIDKeywordNode.value)){
            if(myIDKeywordNode.validateTree() && myExpressionNode.validateTree()){
                return (localSymbolTable.get(myIDKeywordNode.value) == myExpressionNode.WhatAmI());
            }
            else{throw new ParserException(myExpressionNode.token, "Invalid Type being assigned to" + myIDKeywordNode.value, true);}
        } else{ return false;}
    }
}

