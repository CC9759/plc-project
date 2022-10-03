package Phase2Real;

import java.util.ArrayList;

public class FunctionDefParamsNode implements JottTree{
    private ArrayList<IDKeywordNode> paramIDs;
    private ArrayList<Token> paramTypes;

    public FunctionDefParamsNode(ArrayList<IDKeywordNode> paramIDs, ArrayList<Token> paramTypes) {
        this.paramIDs = paramIDs;
        this.paramTypes = paramTypes;
    }

    public static FunctionDefParamsNode parseFunctionDefParamsNode(ArrayList<Token> inputTokens) {
        ArrayList<IDKeywordNode> paramIDs = new ArrayList<>();
        ArrayList<Token> paramTypes = new ArrayList<>();
        while(inputTokens.get(0).getTokenType() != TokenType.R_BRACKET) {
            paramIDs.add(IDKeywordNode.parseIdKeyWordNode(inputTokens));
            paramTypes.add(inputTokens.remove(0));
        }
        return new FunctionDefParamsNode(paramIDs,paramTypes);
    }

    @Override
    public String convertToJott() {
        String result = "";
        for (int i = 0; i < paramIDs.size(); i++) {
            result += paramIDs.get(i).convertToJott();
            result += ":";
            result += paramTypes.get(i).getToken();
            if(i < paramIDs.size() - 1) {
                result += ",";
            }
        }
        return result;
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
