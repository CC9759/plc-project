package Phase2;
/**
 * This class is responsible for parsing and translating an Else Node in the JottTree
 *
 * @author Jonathon LoTempio, Halle Masaryk, Celina Chen, Kaiming Zhang
 **/

import java.util.*;

public class ElseNode implements JottTree {
    private final BodyNode bodyNode;

    public ElseNode(ArrayList<Token> inputList, HashMap<String, String> localSymbolTable) throws Exception{
        this.bodyNode = BodyNode.parseBodyNode(inputList, localSymbolTable);
    }

    public static ElseNode parseElseNode(ArrayList<Token> inputList, HashMap<String, String> localSymbolTable) throws Exception{
        ElseNode elseNode;
        
        if(inputList.get(0).getToken().equals("else")){
            // remove else and left brace
            inputList.remove(0);
            ParserUtils.removeToken(inputList,TokenType.L_BRACE);
            elseNode = new ElseNode(inputList, localSymbolTable);
            // remove right brace
            ParserUtils.removeToken(inputList,TokenType.R_BRACE);
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
            return "else {" + bodyNode.convertToJott() + "}";
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
        return bodyNode.validateTree();
    }
}
