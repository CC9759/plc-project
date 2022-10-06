package Phase2;

import java.util.ArrayList;

public class ParserUtils {
    public static void removeToken(ArrayList<Token> tokens, TokenType type) throws Exception {
        Token token = tokens.remove(0);
        if(token.getTokenType() != type) {
            String errorMessage = String.format("Got \"%s\", but expected %s",token.getTokenType().toString(),type.toString());
            throw new ParserException(token,errorMessage);
        }
    }
}
