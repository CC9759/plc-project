package Phase2;

public class ParserException extends Exception{
    final Token token;
    final String errorMessage;

    public ParserException(Token token, String errorMessage) {
       this.token = token;
       this.errorMessage = errorMessage;
    }
    @Override
    public String getMessage() {
        return String.format("Syntax Error\n%s\n%s:%d",errorMessage,token.getFilename(),token.getLineNum());
    }
}
