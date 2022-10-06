package Phase2Real;

import java.util.ArrayList;

public class FunctionDefParamsNode implements JottTree{
    private ArrayList<IDKeywordNode> paramIDs;
    private ArrayList<Token> paramTypes;

    private FunctionDefParamsNode(ArrayList<IDKeywordNode> paramIDs, ArrayList<Token> paramTypes) {
        this.paramIDs = paramIDs;
        this.paramTypes = paramTypes;
    }

    public static FunctionDefParamsNode parseFunctionDefParamsNode(ArrayList<Token> inputTokens) throws Exception{
        ArrayList<IDKeywordNode> paramIDs = new ArrayList<>();
        ArrayList<Token> paramTypes = new ArrayList<>();
        while(inputTokens.get(0).getTokenType() != TokenType.R_BRACKET) {
            paramIDs.add(IDKeywordNode.parseIdKeyWordNode(inputTokens));
            ParserUtils.removeToken(inputTokens,TokenType.COLON);
            paramTypes.add(inputTokens.remove(0));
            if(inputTokens.get(0).getTokenType() == TokenType.COMMA) {
                inputTokens.remove(0); // ,
            } else if (inputTokens.get(0).getTokenType() != TokenType.R_BRACKET) {
                throw new Exception();
            }

        }
        return new FunctionDefParamsNode(paramIDs,paramTypes);
    }

    @Override
    public String convertToJott() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < paramIDs.size(); i++) {
            result.append(paramIDs.get(i).convertToJott());
            result.append(":");
            result.append(paramTypes.get(i).getToken());
            if(i < paramIDs.size() - 1) {
                result.append(",");
            }
        }
        return result.toString();
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
