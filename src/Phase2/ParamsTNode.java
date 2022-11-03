package Phase2;
/**
 * This class is responsible for parsing and translating a ParamsT Node in the JottTree
 *
 * @author Jonathon LoTempio, Halle Masaryk, Celina Chen, Kaiming Zhang
 **/

import java.util.*;

public class ParamsTNode implements JottTree {

    public HashMap<String, String> localSymbolTable;
    NodeType myType = NodeType.PARAMST;
    final ExpressionNode myExpressionNode;
    final ParamsTNode myParamsTNode;
    private ParamsTNode(ExpressionNode expression, ParamsTNode paramsTNode, HashMap<String, String> localSymbolTable) {
        this.myExpressionNode = expression;
        this.myParamsTNode = paramsTNode;
        this.localSymbolTable = localSymbolTable;
    }

    public static ParamsTNode parseParamsT(ArrayList<Token> tokens, HashMap<String, String> localSymbolTable) throws Exception{

        if(tokens.get(0).getTokenType() != TokenType.R_BRACKET) {
            ParserUtils.removeToken(tokens,TokenType.COMMA);
            ExpressionNode expressionNode = ExpressionNode.parseExpressionNode(tokens, localSymbolTable);
            ParamsTNode paramsTNode = parseParamsT(tokens, localSymbolTable);
            return new ParamsTNode(expressionNode, paramsTNode, localSymbolTable);
        }
        return null;
    }
    public String convertToJott() {
        String result = myExpressionNode.convertToJott();
        if(myParamsTNode != null){
            result += ",";
            result += myParamsTNode.convertToJott();
        }
        return result;
    }

    /**
     * Will output a string of this tree in Java
     * @return a string representing the Java code of this tree
     */
    public String convertToJava() {
        String result = myExpressionNode.convertToJava();
        if (myParamsTNode != null) {
            result +=",";
            result += myParamsTNode.convertToJava();
        }
        return result;
    }

    /**
     * Will output a string of this tree in C
     * @return a string representing the C code of this tree
     */
    public String convertToC() {
        String result = myExpressionNode.convertToC();
        if (myParamsTNode != null) {
            result +=",";
            result += myParamsTNode.convertToC();
        }
        return result;
    }

    /**
     * Will output a string of this tree in Python
     * @return a string representing the Python code of this tree
     */
    public String convertToPython() {
        String result = myExpressionNode.convertToPython();
        if (myParamsTNode != null) {
            result +=",";
            result += myParamsTNode.convertToPython();
        }
        return result;
    }

    /**
     * This will validate that the tree follows the semantic rules of Jott
     * Errors validating will be reported to System.err
     * @return true if valid Jott code; false otherwise
     */
    public boolean validateTree() {
        return true;
    }
}