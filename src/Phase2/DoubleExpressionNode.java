package Phase2;

import Phase2Real.JottTree;
import Phase2Real.NodeType;
import Phase2Real.Token;
import Phase2Real.TokenType;

import java.util.*;
public class DoubleExpressionNode extends ExpressionNode {
    public DoubleExpressionNode(List<Token> inputTokens) {
        super();
        this.myType = NodeType.DOUBLEEXPRESSION;
        Token firstToken = inputTokens.get(0);
        if(firstToken.getTokenType() == TokenType.ID_KEYWORD) {
            this.children.add(new IDKeywordNode(inputTokens));
        }
        else if(firstToken.getTokenType() == TokenType.NUMBER) {
            this.children.add(new ConstantNode(inputTokens));
        }
        if (inputTokens.size() > 1) {
            Token secondToken = inputTokens.get(0);
            Token thirdToken = inputTokens.get(1);
            if (secondToken.getTokenType() == TokenType.MATH_OP &&
                    "+-/*".contains(secondToken.getToken()) &&
                    isValidStart(thirdToken)) {
                this.children.add(new RelOpNode(inputTokens));
                this.children.add(new DoubleExpressionNode(inputTokens));
            }
        }
    }

    public String convertToJava() {
        StringBuilder returnMe = new StringBuilder();
        for(JottTree child : children) {
            returnMe.append(child.convertToJava());
        }
        return returnMe.toString();
    }
    public static boolean isValidStart(Token thisToken) {
        if(thisToken.getTokenType() == TokenType.NUMBER) {
            return thisToken.getToken().contains(".");
        }
        return thisToken.getTokenType() == TokenType.ID_KEYWORD;
    }

    public NodeType getMyType() {
        return myType;
    }
}
