package Phase2;

import java.util.ArrayList;

public class ParserUtils {
    /**
     * checks if token is the expected type, if not,
     * throw a ParserException
     * @param tokens list of tokens to check
     * @param type expected type of token
     * @throws ParserException
     */
    public static void removeToken(ArrayList<Token> tokens, TokenType type) throws ParserException {
        Token token = tokens.remove(0);
        if(token.getTokenType() != type) {
            String errorMessage = String.format("Got \"%s\", but expected %s",token.getTokenType().toString(),type.toString());
            throw new ParserException(token,errorMessage);
        }
    }
}
