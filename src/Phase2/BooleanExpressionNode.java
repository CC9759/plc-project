package Phase2;

import org.w3c.dom.Node;

import java.util.*;

public class BooleanExpressionNode extends ExpressionNode {
    public BooleanExpressionNode(List<Token> inputTokens) {
        super();
        this.myType = NodeType.BOOLEANEXPRESSION;
        Token firstToken = inputTokens.get(0);
        if(firstToken.getTokenType() == TokenType.ID_KEYWORD) {
            if(firstToken.getToken().equals("True") ||
               firstToken.getToken().equals(("False"))) {
                this.children.add(new ConstantNode(inputTokens));
            }
            this.children.add(new IDKeywordNode(inputTokens));
        }
        if(inputTokens.size() > 1) {
            Token secondToken = inputTokens.get(0);
            Token thirdToken = inputTokens.get(1);
            if (secondToken.getTokenType() == TokenType.REL_OP &&
                    "+-/*".contains(secondToken.getToken()) &&
                    isValidStart(thirdToken)) {
                this.children.add(new RelOpNode(inputTokens));
                this.children.add(new BooleanExpressionNode(inputTokens));
            }
        }
    }

    public String convertToJava() {
        StringBuilder returnMe = new StringBuilder();
        for (JottTree child : children) {
            returnMe.append(child.convertToJava());
        }
        return returnMe.toString();
    }

    public static boolean isValidStart(Token thisToken) {
        return thisToken.getTokenType() == TokenType.ID_KEYWORD;
    }


    public NodeType getMyType() {
        return myType;
    }
}
