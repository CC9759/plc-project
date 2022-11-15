package Phase2;
/**
 * This class is responsible for parsing and translating a Params Node in the JottTree
 *
 * @author Jonathon LoTempio, Halle Masaryk, Celina Chen, Kaiming Zhang
 **/

import java.util.*;

public class ParamsNode implements JottTree {

    public HashMap<String, InformationType> localSymbolTable;
    final NodeType myType = NodeType.PARAMS;

    public ArrayList<ExpressionNode> expressions;
    /**
     * < params > -> < expr > < params_t > | 
     * < params_t > -> ,< expr > < params_t > | 
     */
    private ParamsNode(ArrayList<ExpressionNode> expressions, HashMap<String, InformationType> localSymbolTable) {
        this.expressions = expressions;
        this.localSymbolTable = localSymbolTable;
    }

    public static ParamsNode parseParamsNode(ArrayList<Token> tokens, HashMap<String, InformationType> localSymbolTable) throws Exception {
        ArrayList<ExpressionNode> expressions = new ArrayList<>();
        if(tokens.get(0).getTokenType() != TokenType.R_BRACKET) {
            ExpressionNode expressionNode = ExpressionNode.parseExpressionNode(tokens, localSymbolTable);
            expressions.add(expressionNode);
        }
        return new ParamsNode(expressions, localSymbolTable);
    }
    public String convertToJott() {
        String result = "";
        for (int i = 0; i < expressions.size(); i ++) {
            ExpressionNode expression = expressions.get(i);
            result += expression.convertToJott();
            if (i  < expressions.size()-1) {
                result +=",";
            }
        }
        return result;
    }

    /**
     * Will output a string of this tree in Java
     * @return a string representing the Java code of this tree
     */
    public String convertToJava() {
        String result = "";
        for (int i = 0; i < expressions.size(); i ++) {
            ExpressionNode expression = expressions.get(i);
            result += expression.convertToJott();
            if (i  < expressions.size()-1) {
                result +=",";
            }
        }
        return result;
    }

    /**
     * Will output a string of this tree in C
     * @return a string representing the C code of this tree
     */
    public String convertToC() {
        String result = "";
        for (int i = 0; i < expressions.size(); i ++) {
            ExpressionNode expression = expressions.get(i);
            result += expression.convertToJott();
            if (i  < expressions.size()-1) {
                result +=",";
            }
        }
        return result;
    }

    /**
     * Will output a string of this tree in Python
     * @return a string representing the Python code of this tree
     */
    public String convertToPython() {
        String result = "";
        for (int i = 0; i < expressions.size(); i ++) {
            ExpressionNode expression = expressions.get(i);
            result += expression.convertToJott();
            if (i  < expressions.size()-1) {
                result +=",";
            }
        }
        return result;
    }

    /**
     * This will validate that the tree follows the semantic rules of Jott
     * Errors validating will be reported to System.err
     * @return true if valid Jott code; false otherwise
     */
    public boolean validateTree() {
        for (ExpressionNode expression:expressions
             ) {
            if(!expression.validateTree()){
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return null;
    }

    public NodeType getMyType() {
        return myType;
    }
}
