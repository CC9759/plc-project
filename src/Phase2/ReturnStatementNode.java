package Phase2;

import java.util.ArrayList;
import java.util.List;

public class ReturnStatementNode implements JottTree{

    private final ExpressionNode expression;

    private final EndStatementNode endStatement;


    public ReturnStatementNode(List<Token> tokens) {
        ArrayList<Token> expressionList = (ArrayList<Token>) tokens;
        expressionList.remove(expressionList.size() -1);

        ArrayList<Token> endStatementList = new ArrayList<>();
        endStatementList.add(tokens.get(tokens.size()-1));

        this.expression = ExpressionNode.ParseExpressionNode(expressionList);
        this.endStatement = new EndStatementNode(endStatementList);
    }

    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    public String convertToJott(){return "return" + expression.convertToJott() + endStatement.convertToJott();}

    /**
     * Will output a string of this tree in Java
     * @return a string representing the Java code of this tree
     */
    public String convertToJava(){return null;}

    /**
     * Will output a string of this tree in C
     * @return a string representing the C code of this tree
     */
    public String convertToC(){return null;}

    /**
     * Will output a string of this tree in Python
     * @return a string representing the Python code of this tree
     */
    public String convertToPython(){return null;}

    /**
     * This will validate that the tree follows the semantic rules of Jott
     * Errors validating will be reported to System.err
     * @return true if valid Jott code; false otherwise
     */
    public boolean validateTree(){return false;}
}
