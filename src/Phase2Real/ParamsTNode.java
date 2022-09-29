package Phase2Real;
;
import java.util.ArrayList;
import java.util.List;

public class ParamsTNode implements JottTree {
    NodeType myType = NodeType.PARAMST;
    ExpressionNode myExpressionNode = null;
    ParamsTNode myParamsTNode = null;
    public ParamsTNode(ArrayList<Token> inputTokens) {
        if(inputTokens.get(0).getTokenType() != TokenType.R_BRACKET) {
            inputTokens.remove(0);  //Strip comma
            myExpressionNode = ExpressionNode.parseExpressionNode(inputTokens);
            myParamsTNode = new ParamsTNode(inputTokens);
        }
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