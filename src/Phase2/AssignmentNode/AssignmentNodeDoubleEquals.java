package Phase2.AssignmentNode;

import Phase2.*;

import java.lang.reflect.Array;
import java.util.*;

public class AssignmentNodeDoubleEquals extends AssignmentNode {
    private IDKeywordNode myIDKeywordNode = null;
    private DoubleExpressionNode myDoubleExpressionNode = null;
    private EndStatementNode myEndStatementNode = null;
    public AssignmentNodeDoubleEquals(ArrayList<Token> inputList) {
        myIDKeywordNode = new IDKeywordNode(inputList);
        myDoubleExpressionNode = new DoubleExpressionNode(inputList);
        myEndStatementNode = new EndStatementNode(inputList);
    }
    public String convertToJott() {
        return myIDKeywordNode.convertToJott() + myDoubleExpressionNode.convertToJott() + myEndStatementNode.convertToJott();
    }
}
