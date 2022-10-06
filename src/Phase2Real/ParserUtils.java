package Phase2Real;

import java.util.ArrayList;

public class ParserUtils {
    public static void removeToken(ArrayList<Token> tokens, TokenType type) throws Exception {
        if(tokens.remove(0).getTokenType() != type) {
            throw new Exception();
        }
    }
}
