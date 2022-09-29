package Phase2.AssignmentNode;

import Phase2.*;

import java.util.*;

public abstract class AssignmentNode implements JottTree {
    NodeType myType = NodeType.ASSIGNMENT;
    public AssignmentNode() {}
    public static AssignmentNode ParseAssignmentNode(ArrayList<Token> inputTokens) {
        if(inputTokens.get(0).getTokenType() == TokenType.ID_KEYWORD) {
            switch (inputTokens.get(0).getToken()) {
                case "Boolean":
                    return new AssignmentNodeBooleanEquals(inputTokens);
                case "Double":
                    return new AssignmentNodeDoubleEquals(inputTokens);
                case "Integer":
                    return new AssignmentNodeIntegerEquals(inputTokens);
                case "String":
                    return new AssignmentNodeStringEquals(inputTokens);
                default:
                    return null;
            }
        }
        return null;
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
