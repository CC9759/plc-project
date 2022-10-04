package Phase2Real;

import java.util.*;

public class ElseNode implements JottTree {
    private final BodyNode bodyNode;

    public ElseNode(ArrayList<Token> inputList){
        this.bodyNode = BodyNode.parseBodyNode(inputList);
    }

    public static ElseNode parseElseNode(ArrayList<Token> inputList){
        ElseNode elseNode;
        
        if(inputList.get(0).getToken().equals("else")){
            // remove else and right brace
            inputList.remove(0);
            inputList.remove(0);
            elseNode = new ElseNode(inputList);
            // remove left brace
            inputList.remove(0);
        }
        else{
            elseNode = null;
        }

        return elseNode;
    }

    public String convertToJott() {
        if(bodyNode == null){
            return "";
        }
        else{
            return "else {" + bodyNode.toString() + "}";
        }
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
