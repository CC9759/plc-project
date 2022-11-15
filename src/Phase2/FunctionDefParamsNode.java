package Phase2;
/**
 * This class is responsible for parsing and translating a Function Def Params Node in the JottTree
 *
 * @author Jonathon LoTempio, Halle Masaryk, Celina Chen, Kaiming Zhang
 **/

import java.util.ArrayList;

public class FunctionDefParamsNode implements JottTree{
    private final ArrayList<IDKeywordNode> paramIDs;
    private final ArrayList<Token> paramTypes;

    private FunctionDefParamsNode(ArrayList<IDKeywordNode> paramIDs, ArrayList<Token> paramTypes) {
        this.paramIDs = paramIDs;
        this.paramTypes = paramTypes;
    }
    public static boolean validType(Token token) {
        String type = token.getToken();
        return type.equals("Boolean") || type.equals("Double") || type.equals("Integer") || type.equals("String");
    }
    public static FunctionDefParamsNode parseFunctionDefParamsNode(ArrayList<Token> inputTokens) throws Exception{
        ArrayList<IDKeywordNode> paramIDs = new ArrayList<>();
        ArrayList<Token> paramTypes = new ArrayList<>();
        while(inputTokens.get(0).getTokenType() != TokenType.R_BRACKET) {
            paramIDs.add(IDKeywordNode.parseIdKeyWordNode(inputTokens));
            ParserUtils.removeToken(inputTokens,TokenType.COLON);
            if(!validType(inputTokens.get(0))) {
                // not a valid type;
                throw new ParserException(inputTokens.get(0), inputTokens.get(0).getToken() + " is not a valid param type");
            }
            paramTypes.add(inputTokens.remove(0));
            if(inputTokens.get(0).getTokenType() == TokenType.COMMA) {
                inputTokens.remove(0); // ,
            } else if (inputTokens.get(0).getTokenType() != TokenType.R_BRACKET) {
                throw new ParserException(inputTokens.get(0),"Expected \"]\"");
            }

        }
        return new FunctionDefParamsNode(paramIDs,paramTypes);
    }

    public ArrayList<Token> getParamTypes(){
        return this.paramTypes;
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
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < paramIDs.size(); i++) {
            result.append(paramTypes.get(i).getToken());
            result.append(" ");
            result.append(paramIDs.get(i).convertToJava());
            if(i < paramIDs.size() - 1) {
                result.append(",");
            }
        }
        return result.toString();
    }

    @Override
    public String convertToC() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < paramIDs.size(); i++) {
            result.append(paramTypes.get(i).getToken());
            result.append(" ");
            result.append(paramIDs.get(i).convertToC());
            if(i < paramIDs.size() - 1) {
                result.append(",");
            }
        }
        return result.toString();
    }

    @Override
    public String convertToPython() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < paramIDs.size(); i++) {
            result.append(paramIDs.get(i).convertToPython());
            if(i < paramIDs.size() - 1) {
                result.append(",");
            }
        }
        return result.toString();
    }

    @Override
    public boolean validateTree() {
        return true;
    }
}
