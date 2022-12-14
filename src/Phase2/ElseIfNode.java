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

    public ElseIfNode(ArrayList<Token> inputList) throws Exception{
        bExpr = ExpressionNode.parseExpressionNode(inputList);
        // remove right bracket and left brace
        ParserUtils.removeToken(inputList, TokenType.R_BRACKET);
        ParserUtils.removeToken(inputList, TokenType.L_BRACE);
        this.bodyNode = BodyNode.parseBodyNode(inputList);
        // remove right brace
        ParserUtils.removeToken(inputList,TokenType.R_BRACE);
        this.elseIfNode = ElseIfNode.parseElseIfNode(inputList);
    }

    public static ElseIfNode parseElseIfNode(ArrayList<Token> inputList) throws Exception{
        ElseIfNode elseIfNode;
        
        if(inputList.get(0).getToken().equals("elseif")){
            // remove elseif and left bracket
            inputList.remove(0);
            inputList.remove(0);
            elseIfNode = new ElseIfNode(inputList);
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
        return null;
    }

    public String convertToC() {
        return null;
    }

    public String convertToPython() {
        return null;
    }

    public boolean validateTree() {
        return false;
    }   
}
