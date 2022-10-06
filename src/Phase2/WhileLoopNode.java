package Phase2;
/**
 * This class is responsible for parsing and translating a While Loop Node in the JottTree
 *
 * @author Jonathon LoTempio, Halle Masaryk, Celina Chen, Kaiming Zhang
 **/

import java.util.ArrayList;

public class WhileLoopNode extends BodyStatementNode{

    private final ExpressionNode boolExpression;
    private final BodyNode body;

    private WhileLoopNode(ExpressionNode boolExpressionNode, BodyNode bodyNode){
        this.boolExpression = boolExpressionNode;
        this.body = bodyNode;
    }


    public static WhileLoopNode parseWhileLoopNode(ArrayList<Token> tokens) throws Exception{
        //ask about the braces
        tokens.remove(0); //removes while
        ParserUtils.removeToken(tokens, TokenType.L_BRACKET);
        ExpressionNode boolExpressionNode = ExpressionNode.parseExpressionNode(tokens);
        ParserUtils.removeToken(tokens,TokenType.R_BRACKET);
        ParserUtils.removeToken(tokens,TokenType.L_BRACE);
        BodyNode bodyNode = BodyNode.parseBodyNode(tokens);
        ParserUtils.removeToken(tokens,TokenType.R_BRACE);
        return new WhileLoopNode(boolExpressionNode, bodyNode);


    }

    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    //We should add space correct?
    public String convertToJott(){return "while [ " + boolExpression.convertToJott() + " ] { "+body.convertToJott()+" }";}

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
