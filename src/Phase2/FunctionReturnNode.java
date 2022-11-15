package Phase2;
/**
 * This class is responsible for parsing and translating a Function Return Node in the JottTree
 *
 * @author Jonathon LoTempio, Halle Masaryk, Celina Chen, Kaiming Zhang
 **/

import java.util.ArrayList;

public class FunctionReturnNode implements JottTree{
    final Token returnToken;

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
        return returnToken.getToken();
    }

    @Override
    public String convertToC() {
        return returnToken.getToken();
    }

    @Override
    public String convertToPython() {
        return returnToken.getToken();
    }

    @Override
    public boolean validateTree() {
        return true;
    }
}
