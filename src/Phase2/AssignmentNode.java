/*package Phase2;

import Phase2Real.JottTree;
import Phase2Real.NodeType;
import Phase2Real.Token;
import Phase2Real.TokenType;

import java.util.*;

public class AssignmentNode implements JottTree {
    IDKeywordNode myIDKeywordNode = null;
    ExpressionNode myExpressionNode = null;
    EndStatementNode myEndStatementNode = null;
    NodeType myType = NodeType.ASSIGNMENT;
    public AssignmentNode() {}
    public AssignmentNode(ArrayList<Token> inputTokens) {
        if(inputTokens.get(0).getTokenType() == TokenType.ID_KEYWORD) {
            switch (inputTokens.get(0).getToken()) {
                case "Boolean":
                    inputTokens.remove(0);
                    myExpressionNode = ExpressionNode.ParseExpressionNode(inputTokens);
                    break;
                case "Double":
                    children.add(new IDKeywordNode(inputTokens));
                    children.add(new IDKeywordNode(inputTokens));
                    children.add(new OpNode(inputTokens));
                    children.add(new DoubleExpressionNode(inputTokens));
                    children.add(new EndStatementNode(inputTokens));
                    break;
                case "Integer":
                    children.add(new IDKeywordNode(inputTokens));
                    children.add(new IDKeywordNode(inputTokens));
                    children.add(new OpNode(inputTokens));
                    children.add(new IntegerExpressionNode(inputTokens));
                    children.add(new EndStatementNode(inputTokens));
                    break;
                case "String":
                    children.add(new IDKeywordNode(inputTokens));
                    children.add(new IDKeywordNode(inputTokens));
                    children.add(new OpNode(inputTokens));
                    children.add(new StringExpressionNode(inputTokens));
                    children.add(new EndStatementNode(inputTokens));
                    break;
                default:
                    children.add(new IDKeywordNode(inputTokens));
                    children.add(ExpressionNode.ParseExpressionNode(inputTokens));
                    children.add(new EndStatementNode(inputTokens));
                    break;
            }
        }
    }

    public static AssignmentNode ParseAssignmentNode(List<Token> inputList) {
        return new AssignmentNode(inputList);
    }

    public String convertToJott() {
        StringBuilder returnMe = new StringBuilder();
        for(JottTree child : children) {
            returnMe.append(child.convertToJott());
        }
        return returnMe.toString();
    }
    public String convertToJava() {return null;}
    public String convertToC() {return null;}
    public String convertToPython() {return null;}
    public boolean validateTree() {return true;}
    public String toString() {return null;}

    public NodeType getMyType() {
        return myType;
    }
}

 */
