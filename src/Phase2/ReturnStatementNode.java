package Phase2;
/**
 * This class is responsible for parsing and translating a Return Statement Node in the JottTree
 *
 * @author Jonathon LoTempio, Halle Masaryk, Celina Chen, Kaiming Zhang
 **/

import java.util.ArrayList;

public class ReturnStatementNode implements JottTree {
    private final ExpressionNode expression;



    public ReturnStatementNode(ExpressionNode expressionNode) throws Exception {
        this.expression = expressionNode;
    }
    //TODO implement ParseReturnStatementNode
    public static ReturnStatementNode ParseReturnStatementNode(ArrayList<Token> inputList) throws Exception {
        inputList.remove(0);
        ExpressionNode expressionNode = ExpressionNode.parseExpressionNode(inputList);
        ParserUtils.removeToken(inputList,TokenType.SEMICOLON);
        return new ReturnStatementNode(expressionNode);
    }

    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    public String convertToJott(){return "return " + expression.convertToJott() + ";";}

    /**
     * Will output a string of this tree in Java
     * @return a string representing the Java code of this tree
     */
    public String convertToJava(){return null;}

    /**
     * Will output a string of this tree in C
     * @return a string representing the C code of this tree
     */
    public String convertToC(){return null;}

    /**
     * Will output a string of this tree in Python
     * @return a string representing the Python code of this tree
     */
    public String convertToPython(){return null;}

    /**
     * This will validate that the tree follows the semantic rules of Jott
     * Errors validating will be reported to System.err
     * @return true if valid Jott code; false otherwise
     */
    public boolean validateTree(){return false;}

    
}
