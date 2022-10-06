package Phase2;
/**
 * This class is responsible for parsing and translating a Params Node in the JottTree
 *
 * @author Jonathon LoTempio, Halle Masaryk, Celina Chen, Kaiming Zhang
 **/

import java.util.*;

public class ParamsNode implements JottTree {
    final NodeType myType = NodeType.PARAMS;
    final ExpressionNode myExpressionNode;
    final ParamsTNode myParamsTNode;
    /**
     * < params > -> < expr > < params_t > | 
     * < params_t > -> ,< expr > < params_t > | 
     */
    private ParamsNode(ExpressionNode expressionNode, ParamsTNode paramsTNode) {
        this.myExpressionNode = expressionNode;
        this.myParamsTNode = paramsTNode;
    }

    public static ParamsNode parseParamsNode(ArrayList<Token> tokens) throws Exception {
        if(tokens.get(0).getTokenType() != TokenType.R_BRACKET) {
            ExpressionNode expressionNode = ExpressionNode.parseExpressionNode(tokens);
            ParamsTNode paramsTNode = ParamsTNode.parseParamsT(tokens);
            return new ParamsNode(expressionNode, paramsTNode);
        }
        return null;
    }
    public String convertToJott() {
        String result = myExpressionNode.convertToJott();
        if (myParamsTNode != null) {
            result +=",";
            result += myParamsTNode.convertToJott();
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
        return true;
    }

    public String toString() {
        return null;
    }

    public NodeType getMyType() {
        return myType;
    }

}
