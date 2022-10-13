/**
 * This class is responsible for parsing and translating a Params Node in the JottTree
 *
 * @author Jonathon LoTempio
 **/
package Phase2;

public class ParserException extends Exception{
    final Token token;
    final String errorMessage;

    /**
     * creates an exception that stores a token and message
     * @param token Token that caused exception
     * @param errorMessage custom error message
     */
    public ParserException(Token token, String errorMessage) {
       this.token = token;
       this.errorMessage = errorMessage;
    }

    /**
     * prints out a message including tokens filename and line number
     * @return Error message
     */
    @Override
    public String getMessage() {
        return String.format("Syntax Error\n%s\n%s:%d",errorMessage,token.getFilename(),token.getLineNum());
    }
}
