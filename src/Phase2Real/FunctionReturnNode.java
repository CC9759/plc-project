package Phase2Real;

import java.util.ArrayList;

public class FunctionReturnNode implements JottTree{
    Token returnToken;

    private FunctionReturnNode(Token returnToken) {
        this.returnToken = returnToken;
    }

    public static FunctionReturnNode parseFunctionReturnNode(ArrayList<Token> inputtokens) throws Exception {
        return new FunctionReturnNode(inputtokens.remove(0));
    }
    @Override
    public String convertToJott() {
        return returnToken.getToken();
    }

    @Override
    public String convertToJava() {
        return null;
    }

    @Override
    public String convertToC() {
        return null;
    }

    @Override
    public String convertToPython() {
        return null;
    }

    @Override
    public boolean validateTree() {
        return false;
    }
}
