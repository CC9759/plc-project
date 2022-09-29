package Phase2Real;

import java.util.*;

public class AssignmentNode implements JottTree {
    String myType = null;
    IDKeywordNode myIDKeywordNode;
    ExpressionNode myExpressionNode;
    EndStatementNode myEndStatementNode;
    public AssignmentNode(ArrayList<Token> inputList) {
        String firstTokenAsString = inputList.get(0).getToken();
        if(firstTokenAsString.equals("Boolean") ||
           firstTokenAsString.equals("Double") ||
           firstTokenAsString.equals("Integer") ||
           firstTokenAsString.equals("String")) {
            myType = firstTokenAsString;
            inputList.remove(0);
        }
        myIDKeywordNode = new IDKeywordNode(inputList);
        myExpressionNode = ExpressionNode.parseExpressionNode(inputList);
        myEndStatementNode = new EndStatementNode(inputList);
    }

    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    public String convertToJott() {
        String returnMe = "";
        if(myType != null) {
            returnMe = returnMe + myType;
        }
        returnMe = returnMe + myIDKeywordNode.convertToJott();
        returnMe = returnMe + myExpressionNode.convertToJott();
        returnMe = returnMe + myEndStatementNode.convertToJott();
        return returnMe;
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

