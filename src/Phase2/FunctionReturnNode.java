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
        if(returnToken.getToken().equals("Void")){
            return "void";
        }
        return returnToken.getToken();
    }

    @Override
    public String convertToC() {
        switch(returnToken.getToken()){
            case "String": return "char*";
            case "Integer": return "int";
            case "Boolean": return "bool";
            case "Double": return "double";
            case "Void": return "void";
        }
        return returnToken.getToken();
    }

    @Override
    public String convertToPython() {
        return "";
    }

    @Override
    public boolean validateTree() {
        return true;
    }
}
