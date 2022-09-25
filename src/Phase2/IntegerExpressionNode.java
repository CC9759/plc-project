package Phase2;

import java.util.*;


public class IntegerExpressionNode extends ExpressionNode{
    public IntegerExpressionNode(List<Token> inputTokens) {
        super();
        Token firstToken = inputTokens.get(0);
        if(firstToken.getTokenType() == TokenType.ID_KEYWORD) {
            this.children.add(new IDKeywordNode(inputTokens));
            inputTokens.remove(0);
        }
        else if(firstToken.getTokenType() == TokenType.NUMBER) {
            this.children.add(new ConstantNode(inputTokens));
            inputTokens.remove(0);
        }
        if (inputTokens.size() > 1) {
            Token secondToken = inputTokens.get(0);
            Token thirdToken = inputTokens.get(1);
            if (secondToken.getTokenType() == TokenType.REL_OP &&
                    "+-/*".contains(secondToken.getToken()) &&
                    isValidStart(thirdToken)) {
                this.children.add(new RelOpNode(inputTokens));
                inputTokens.remove(0);
                this.children.add(new DoubleExpressionNode(inputTokens));
            }
        }
    }
    public static boolean isValidStart(Token thisToken) {
        if (thisToken.getTokenType() == TokenType.NUMBER) {
            if (!thisToken.getToken().contains(".")) {
                return true;
            }
        }
        return thisToken.getTokenType() == TokenType.ID_KEYWORD;
    }
}
