package Phase2;
/**
 * This class is responsible for parsing and translating a Return Statement Node in the JottTree
 *
 * @author Jonathon LoTempio, Halle Masaryk, Celina Chen, Kaiming Zhang
 **/

import java.util.ArrayList;
import java.util.HashMap;

public class ReturnStatementNode implements JottTree {

    public HashMap<String, InformationType> localSymbolTable;
    private final ExpressionNode expression;



    public ReturnStatementNode(ExpressionNode expressionNode, HashMap<String, InformationType> localSymbolTable) throws Exception {
        this.expression = expressionNode;
        this.localSymbolTable = localSymbolTable;
    }

    public static ReturnStatementNode ParseReturnStatementNode(ArrayList<Token> inputList, HashMap<String, InformationType> localSymbolTable) throws Exception {
        inputList.remove(0);
        ExpressionNode expressionNode = ExpressionNode.parseExpressionNode(inputList, localSymbolTable);
        ParserUtils.removeToken(inputList,TokenType.SEMICOLON);
        return new ReturnStatementNode(expressionNode, localSymbolTable);
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
    public String convertToJava(){return "return " + expression.convertToJava() + ";";}

    /**
     * Will output a string of this tree in C
     * @return a string representing the C code of this tree
     */
    public String convertToC(){return "return " + expression.convertToC() + ";";}

    /**
     * Will output a string of this tree in Python
     * @return a string representing the Python code of this tree
     */
    public String convertToPython(){return "return " + expression.convertToPython() + "\n";}

    /**
     * This will validate that the tree follows the semantic rules of Jott
     * Errors validating will be reported to System.err
     * @return true if valid Jott code; false otherwise
     */
    public boolean validateTree() throws Exception{return expression.validateTree();}

    public ExpressionNode getExpressionNode() {
        return expression;
    }

    
}
