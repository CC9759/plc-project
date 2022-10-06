package Phase2;

import java.util.*;

public class AssignmentNode implements JottTree {
    String myType;
    IDKeywordNode myIDKeywordNode;
    ExpressionNode myExpressionNode;
    private AssignmentNode(Token type, IDKeywordNode id, ExpressionNode expression){//}, EndStatementNode endStatement){
        if(type != null) {
            this.myType = type.getToken();
        }
        this.myIDKeywordNode = id;
        this.myExpressionNode = expression;
    }

    static AssignmentNode parseAssignmentNode(ArrayList<Token> tokens) throws Exception{
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
        ExpressionNode expressionNode = ExpressionNode.parseExpressionNode(tokens);
        return new AssignmentNode(typeToken, idKeywordNode, expressionNode);
    }
    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    public String convertToJott() {
        String returnMe = "";
        if(myType != null) {
            returnMe = returnMe + myType;
            returnMe = returnMe + " ";
        }
        returnMe = returnMe + myIDKeywordNode.convertToJott();
        returnMe = returnMe + " = ";
        returnMe = returnMe + myExpressionNode.convertToJott();
        returnMe = returnMe + " ";
        returnMe = returnMe;//myEndStatementNode.convertToJott();
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

