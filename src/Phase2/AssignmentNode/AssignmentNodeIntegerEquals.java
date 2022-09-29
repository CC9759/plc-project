package Phase2.AssignmentNode;

import Phase2.*;

import java.lang.reflect.Array;
import java.util.*;

public class AssignmentNodeIntegerEquals extends AssignmentNode {
    private IDKeywordNode myIDKeywordNode = null;
    private IntegerExpressionNode myIntegerExpressionNode = null;
    private EndStatementNode myEndStatementNode = null;
    public AssignmentNodeIntegerEquals(ArrayList<Token> inputList) {
        myIDKeywordNode = new IDKeywordNode(inputList);
        myIntegerExpressionNode = new IntegerExpressionNode(inputList);
        myEndStatementNode = new EndStatementNode(inputList);
    }
    public String convertToJott() {
        return myIDKeywordNode.convertToJott() + myIntegerExpressionNode.convertToJott() + myEndStatementNode.convertToJott();
    }
}