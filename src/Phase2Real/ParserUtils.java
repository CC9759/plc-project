package Phase2Real;

import java.util.ArrayList;

public class ParserUtils {
    public static void removeToken(ArrayList<Token> tokens, TokenType type) {
        if(tokens.remove(0).getTokenType() != type) {
            System.err.println("error!");
            //System.exit(0);
        }
    }
}
