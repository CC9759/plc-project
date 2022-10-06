package Phase2;

import java.util.ArrayList;

public class ParserUtils {
    public static void removeToken(ArrayList<Token> tokens, TokenType type) throws Exception {
        if(tokens.remove(0).getTokenType() != type) {
            System.err.println("error!");
            //System.exit(0);
            throw new Exception();
        }
    }
}
