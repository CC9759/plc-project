package Phase2Real;

import java.util.*;

public class ParamsTNode implements JottTree {
    NodeType myType = NodeType.PARAMST;
    ExpressionNode myExpressionNode = null;
    ParamsTNode myParamsTNode = null;
    public ParamsTNode(ExpressionNode expression, ParamsTNode paramsTNode) {
        this.myExpressionNode = expression;
        this.myParamsTNode = paramsTNode;
    }

    public static ParamsTNode parseParamsT(ArrayList<Token> tokens){

        if(tokens.get(0).getTokenType() != TokenType.R_BRACKET) {
            tokens.remove(0);  //Strip comma
            ExpressionNode expressionNode = ExpressionNode.parseExpressionNode(tokens);
            ParamsTNode paramsTNode = parseParamsT(tokens);
            return new ParamsTNode(expressionNode, paramsTNode);
        }
        return null;
    }
    public String convertToJott() {
        return null;
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
}