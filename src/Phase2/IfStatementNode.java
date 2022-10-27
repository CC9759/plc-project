package Phase2;
/**
 * This class is responsible for parsing and translating an If Statement Node in the JottTree
 *
 * @author Jonathon LoTempio, Halle Masaryk, Celina Chen, Kaiming Zhang
 **/

import java.util.ArrayList;
import java.util.HashMap;

public class IfStatementNode extends BodyStatementNode{


    private final ExpressionNode boolExpressoin;
    private final BodyNode body;

    private final ElseIfNode elsIf;
    private final ElseNode elseStatement;
    public IfStatementNode(ExpressionNode boolExpressionNode, BodyNode bodyNode, ElseIfNode elseIfNode, ElseNode elseNode) throws Exception{
        this.boolExpressoin = boolExpressionNode;
        this.body = bodyNode;
        this.elsIf = elseIfNode;
        this.elseStatement = elseNode;
    }
    public static IfStatementNode parseIfStatementNode(ArrayList<Token> tokens, HashMap<String, String> localSymbolTable) throws Exception{
        tokens.remove(0); //remove if
        ParserUtils.removeToken(tokens,TokenType.L_BRACKET);
        ExpressionNode boolExpressionNode = ExpressionNode.parseExpressionNode(tokens, localSymbolTable);
        ParserUtils.removeToken(tokens, TokenType.R_BRACKET);
        ParserUtils.removeToken(tokens, TokenType.L_BRACE);
        BodyNode bodyNode = BodyNode.parseBodyNode(tokens, localSymbolTable);
        ParserUtils.removeToken(tokens, TokenType.R_BRACE);

        ElseIfNode elseIfNode = null;
        if(!tokens.isEmpty()){
            Token elseifToken = tokens.get(0);

            if(elseifToken.getToken().equals("elseif")) {
                tokens.remove(0);
                elseIfNode = ElseIfNode.parseElseIfNode(tokens, localSymbolTable);
            }
        }

        ElseNode elseNode = null;
        if(!tokens.isEmpty()){
            Token elseToken = tokens.get(0);

            if (elseToken.getToken().equals("else")){
                tokens.remove(0);
                elseNode = ElseNode.parseElseNode(tokens, localSymbolTable);
            }
        }

        return new IfStatementNode(boolExpressionNode, bodyNode, elseIfNode, elseNode);
    }
    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    public String convertToJott() {
        String result = "if [ " + boolExpressoin.convertToJott()+ " ] { "+ body.convertToJott()+" } ";
        if (elsIf != null){
            result += elsIf.convertToJott();
        }
        if (elseStatement != null){
            result += elseStatement.convertToJott();
        }

        return result;

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
        Boolean expressionBool = boolExpressoin.validateTree();
        Boolean bodyBool = body.validateTree();
        Boolean elseIfBool = true;
        Boolean elseBool = true;

        if(elsIf!= null){elseIfBool = elsIf.validateTree();}
        if(elseStatement!=null){ elseBool = elseStatement.validateTree();}

        return (expressionBool && bodyBool && elseIfBool && elseBool);
    }

}
