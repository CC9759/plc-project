package Phase2.AssignmentNode;

import Phase2.*;

import java.lang.reflect.Array;
import java.util.*;

public class AssignmentNodeBooleanEquals extends AssignmentNode {
    private IDKeywordNode myIDKeywordNode = null;
    private BooleanExpressionNode myBooleanExpressionNode = null;
    private EndStatementNode myEndStatementNode = null;
    public AssignmentNodeBooleanEquals(ArrayList<Token> inputList) {
        myIDKeywordNode = new IDKeywordNode(inputList);
        myBooleanExpressionNode = new BooleanExpressionNode(inputList);
        myEndStatementNode = new EndStatementNode(inputList);
    }
    public String convertToJott() {
        return myIDKeywordNode.convertToJott() + myBooleanExpressionNode.convertToJott() + myEndStatementNode.convertToJott();
    }
}