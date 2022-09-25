import java.util.*;
public class DoubleExpressionNode extends ExpressionNode {
    public DoubleExpressionNode(List<Token> inputTokens) {
        super(inputTokens);
        Token firstToken = inputTokens.get(0);
        if(firstToken.getTokenType() == TokenType.ID_KEYWORD) {
            this.children.add(new IDKeywordNode(firstToken));
        }

    }
}
