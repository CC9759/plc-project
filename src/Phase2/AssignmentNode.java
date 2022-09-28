package Phase2;

import org.w3c.dom.Node;

import java.util.*;

public class AssignmentNode implements JottTree {
    List<JottTree> children = new ArrayList<>();
    NodeType myType = NodeType.ASSIGNMENT;
    public AssignmentNode() {}
    public AssignmentNode(List<Token> inputTokens) {
        if(inputTokens.get(0).getTokenType() == TokenType.ID_KEYWORD) {
            switch (inputTokens.get(0).getToken()) {
                case "Boolean" -> {
                    children.add(new IDKeywordNode(inputTokens));
                    children.add(new IDKeywordNode(inputTokens));
                    children.add(new OpNode(inputTokens));
                    children.add(new BooleanExpressionNode(inputTokens));
                    children.add(new EndStatementNode(inputTokens));
                }
                case "Double" -> {
                    children.add(new IDKeywordNode(inputTokens));
                    children.add(new IDKeywordNode(inputTokens));
                    children.add(new OpNode(inputTokens));
                    children.add(new DoubleExpressionNode(inputTokens));
                    children.add(new EndStatementNode(inputTokens));
                }
                case "Integer" -> {
                    children.add(new IDKeywordNode(inputTokens));
                    children.add(new IDKeywordNode(inputTokens));
                    children.add(new OpNode(inputTokens));
                    children.add(new IntegerExpressionNode(inputTokens));
                    children.add(new EndStatementNode(inputTokens));
                }
                case "String" -> {
                    children.add(new IDKeywordNode(inputTokens));
                    children.add(new IDKeywordNode(inputTokens));
                    children.add(new OpNode(inputTokens));
                    children.add(new StringExpressionNode(inputTokens));
                    children.add(new EndStatementNode(inputTokens));
                }
                default -> {
                    children.add(new IDKeywordNode(inputTokens));
                    children.add(ExpressionNode.ParseExpressionNode(inputTokens));
                    children.add(new EndStatementNode(inputTokens));
                }
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
