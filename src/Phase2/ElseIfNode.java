package Phase2;
/**
 * This class is responsible for parsing and translating an Else If Node in the JottTree
 *
 * @author Jonathon LoTempio, Halle Masaryk, Celina Chen, Kaiming Zhang
 **/

import java.util.*;

public class ElseIfNode implements JottTree {
    private final BodyNode bodyNode;
    private final ExpressionNode bExpr;
    private final ElseIfNode elseIfNode;
    public HashMap<String, String> localSymbolTable;

    public ElseIfNode(ArrayList<Token> inputList, HashMap<String, String> localSymbolTable) throws Exception{
        bExpr = ExpressionNode.parseExpressionNode(inputList, localSymbolTable);
        // remove right bracket and left brace
        ParserUtils.removeToken(inputList, TokenType.R_BRACKET);
        ParserUtils.removeToken(inputList, TokenType.L_BRACE);
        this.bodyNode = BodyNode.parseBodyNode(inputList, localSymbolTable);
        // remove right brace
        ParserUtils.removeToken(inputList,TokenType.R_BRACE);
        this.elseIfNode = ElseIfNode.parseElseIfNode(inputList, localSymbolTable);
        this.localSymbolTable = localSymbolTable;
    }

    public static ElseIfNode parseElseIfNode(ArrayList<Token> inputList, HashMap<String, String> localSymbolTable) throws Exception{
        ElseIfNode elseIfNode;
        
        if(inputList.get(0).getToken().equals("elseif")){
            // remove elseif and left bracket
            inputList.remove(0);
            inputList.remove(0);
            elseIfNode = new ElseIfNode(inputList, localSymbolTable);
        }
        else{
            return null;
        }

        return elseIfNode;
    }

    public String convertToJott() {
        return "elseif [" + bExpr.convertToJott() + "]{" + bodyNode.convertToJott() + "}" + elseIfNode.convertToJott(); 
    }

    public String convertToJava() {
        return "else if (" + bExpr.convertToJava() + "){" + bodyNode.convertToJava() + "}" + elseIfNode.convertToJava(); 
    }

    public String convertToC() {
        return "else if (" + bExpr.convertToC() + "){" + bodyNode.convertToC() + "}" + elseIfNode.convertToC(); 
    }

    public String convertToPython() {
        String finalString = "elif (" + bExpr.convertToPython() + "):\n\t";
        
        finalString += bodyNode.convertToPython().replace("\n", "\n\t");
        finalString += "\n" + elseIfNode.convertToPython();
        return finalString;
    }

    public boolean validateTree() {
        Boolean expressionBool = bExpr.validateTree();
        Boolean bodyBool = bodyNode.validateTree();
        Boolean elseIfBool = true;

        if(elseIfNode!= null){elseIfBool = elseIfNode.validateTree();}

        return (expressionBool && bodyBool && elseIfBool);
    }

    /**
     *     private final BodyNode bodyNode;
     *     private final ExpressionNode bExpr;
     *     private final ElseIfNode elseIfNode;
     */

    public boolean returnable() {
        if(elseIfNode == null) {
            return bodyNode.returnable();
        }
        return elseIfNode.returnable() && bodyNode.returnable();
    }
}
