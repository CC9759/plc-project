package Phase2;
/**
 * This class is responsible for parsing and translating a While Loop Node in the JottTree
 *
 * @author Jonathon LoTempio, Halle Masaryk, Celina Chen, Kaiming Zhang
 **/

import java.util.ArrayList;
import java.util.HashMap;

public class WhileLoopNode extends BodyStatementNode{

    public HashMap<String, InformationType> localSymbolTable;

    public final boolean isStatement = false;

    private final ExpressionNode boolExpression;
    private final BodyNode body;

    private WhileLoopNode(ExpressionNode boolExpressionNode, BodyNode bodyNode, HashMap<String, InformationType> localSymbolTable){
        this.boolExpression = boolExpressionNode;
        this.body = bodyNode;
        this.localSymbolTable = localSymbolTable;
    }


    public static WhileLoopNode parseWhileLoopNode(ArrayList<Token> tokens, HashMap<String, InformationType> localSymbolTable) throws Exception{
        //ask about the braces
        tokens.remove(0); //removes while
        ParserUtils.removeToken(tokens, TokenType.L_BRACKET);
        ExpressionNode boolExpressionNode = ExpressionNode.parseExpressionNode(tokens, localSymbolTable);
        ParserUtils.removeToken(tokens,TokenType.R_BRACKET);
        ParserUtils.removeToken(tokens,TokenType.L_BRACE);
        BodyNode bodyNode = BodyNode.parseBodyNode(tokens, localSymbolTable);
        ParserUtils.removeToken(tokens,TokenType.R_BRACE);
        return new WhileLoopNode(boolExpressionNode, bodyNode, localSymbolTable);


    }

    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    //We should add space correct?
    public String convertToJott(){return "while[" + boolExpression.convertToJott() + "] {"+ body.convertToJott() +"}";}

    /**
     * Will output a string of this tree in Java
     * @return a string representing the Java code of this tree
     */
    public String convertToJava(){return "while(" + boolExpression.convertToJava() + "){" + body.convertToJava() +"}";}

    /**
     * Will output a string of this tree in C
     * @return a string representing the C code of this tree
     */
    public String convertToC(){return "while(" + boolExpression.convertToC() + "){" + body.convertToC() +"}";}

    /**
     * Will output a string of this tree in Python
     * @return a string representing the Python code of this tree
     */
    public String convertToPython(){return "while(" + boolExpression.convertToPython() + "):\n\t" + body.convertToPython().replace("\n", "\n\t");}

    /**
     * This will validate that the tree follows the semantic rules of Jott
     * Errors validating will be reported to System.err
     * @return true if valid Jott code; false otherwise
     */
    public boolean validateTree() throws ParserException {return (boolExpression.validateTree() && body.validateTree());}

    /**
     *     private final ExpressionNode boolExpression;
     *     private final BodyNode body;
     */

    public ReturnStatementNode returnable() {
//      return body.returnable();
//      while loops don't have to run, so they can't be used in returnables
        return null;
    }
}
