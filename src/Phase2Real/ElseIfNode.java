package Phase2Real;

import java.util.*;

public class ElseIfNode implements JottTree {
    private final BodyNode bodyNode;
    private final ExpressionNode bExpr;
    private final ElseIfNode elseIfNode;

    public ElseIfNode(ArrayList<Token> inputList){
        bExpr = ExpressionNode.parseExpressionNode(inputList);
        // remove right bracket and left brace
        inputList.remove(0);
        inputList.remove(0);
        this.bodyNode = BodyNode.parseBodyNode(inputList);
        // remove right brace
        inputList.remove(0);
        this.elseIfNode = ElseIfNode.parseElseIfNode(inputList);
    }

    public static ElseIfNode parseElseIfNode(ArrayList<Token> inputList){
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
