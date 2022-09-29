package Phase2.AssignmentNode;

import Phase2.*;

import java.lang.reflect.Array;
import java.util.*;

public class AssignmentNodeStringEquals extends AssignmentNode {
    private IDKeywordNode myIDKeywordNode = null;
    private StringExpressionNode myStringExpressionNode = null;
    private EndStatementNode myEndStatementNode = null;
    public AssignmentNodeStringEquals(ArrayList<Token> inputList) {
        myIDKeywordNode = new IDKeywordNode(inputList);
        myStringExpressionNode = new StringExpressionNode(inputList);
        myEndStatementNode = new EndStatementNode(inputList);
    }
    public String convertToJott() {
        return myIDKeywordNode.convertToJott() + myStringExpressionNode.convertToJott() + myEndStatementNode.convertToJott();
    }
}