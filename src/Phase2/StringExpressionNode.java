package Phase2;

import java.util.*;

public class StringExpressionNode extends ExpressionNode {
    public StringExpressionNode(List<Token> inputTokens) {
        super();
        Token firstToken = inputTokens.get(0);
        if(firstToken.getTokenType() == TokenType.ID_KEYWORD) {
            //TODO CHECK FNC CALL WHEN IMPLEMENTED
            this.children.add(new IDKeywordNode(inputTokens));
        }
        else if(firstToken.getTokenType() == TokenType.STRING) {
            this.children.add(new ConstantNode(inputTokens));
        }
    }
    public String convertToJava() {
        StringBuilder returnMe = new StringBuilder();
        for(JottTree child : children) {
            returnMe.append(child.convertToJava());
        }
        return returnMe.toString();
    }

    public NodeType getMyType() {
        return myType;
    }
}
