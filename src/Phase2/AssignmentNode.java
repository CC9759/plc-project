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


    public HashMap<String, Boolean> initialized;

    private AssignmentNode(Token type, IDKeywordNode id, ExpressionNode expression, HashMap<String, InformationType> localSymbolTable, HashMap<String, Boolean> initialized){//}, EndStatementNode endStatement){
        if(type != null) {
            this.myType = type.getToken();
        }
        this.myIDKeywordNode = id;
        this.myExpressionNode = expression;
        this.localSymbolTable = localSymbolTable;
        this.initialized = initialized;
    }

    static AssignmentNode parseAssignmentNode(ArrayList<Token> tokens, HashMap<String, InformationType> localSymbolTable, HashMap<String, Boolean> initialized) throws Exception{
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
        ExpressionNode expressionNode = ExpressionNode.parseExpressionNode(tokens, localSymbolTable, initialized);
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
            initialized.put(idKeywordNode.value, true);
        }
        return new AssignmentNode(typeToken, idKeywordNode, expressionNode, localSymbolTable, initialized);
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
        if(myIDKeywordNode.value.equals("while") || myIDKeywordNode.value.equals("print")|| myIDKeywordNode.value.equals("return") || myIDKeywordNode.value.equals("if")|| myIDKeywordNode.value.equals("elif")|| myIDKeywordNode.value.equals("else")){
            throw new ParserException(myExpressionNode.token, "Can't use the keyword " + myIDKeywordNode.value+ " as a variable name", true);
        }
        if(localSymbolTable.containsKey(myIDKeywordNode.value)){
            if(!initialized.get(myIDKeywordNode.value)){throw new ParserException(myExpressionNode.token, "Variable " + myIDKeywordNode.value+ " uninitialized", true);}
            if(myExpressionNode.WhatAmI()==null){return false;}
            if (localSymbolTable.get(myIDKeywordNode.value) != myExpressionNode.WhatAmI()){throw new ParserException(myExpressionNode.token, "Invalid Type being assigned to " + myIDKeywordNode.value, true);}
            if(myIDKeywordNode.validateTree() && myExpressionNode.validateTree()){
                return (localSymbolTable.get(myIDKeywordNode.value) == myExpressionNode.WhatAmI());
            }
            else{return false;}
        } else{ throw new ParserException(myExpressionNode.token, "Variable " + myIDKeywordNode.value+ " undefined", true);}
    }
}

