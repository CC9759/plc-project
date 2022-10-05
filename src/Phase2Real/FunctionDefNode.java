package Phase2Real;

import javax.swing.text.html.parser.Parser;
import java.util.ArrayList;

public class FunctionDefNode implements JottTree{
    IDKeywordNode myIDKeywordNode;
    FunctionDefParamsNode myFunctionDefParamsNode;
    FunctionReturnNode myReturnNode;
    BodyNode myBodyNode;
    private FunctionDefNode(IDKeywordNode myIDKeywordNode, FunctionDefParamsNode myFunctionDefParamsNode, FunctionReturnNode myReturnNode, BodyNode myBodyNode) {
        this.myIDKeywordNode = myIDKeywordNode;
        this.myFunctionDefParamsNode = myFunctionDefParamsNode;
        this.myReturnNode = myReturnNode;
        this.myBodyNode = myBodyNode;
    }
    public static FunctionDefNode parseFunctionDefNode(ArrayList<Token> inputTokens) throws Exception {
        IDKeywordNode myIDKeywordNode = IDKeywordNode.parseIdKeyWordNode(inputTokens);
        //TODO check that types are correct
        ParserUtils.removeToken(inputTokens, TokenType.L_BRACKET);
        FunctionDefParamsNode myFunctionDefParamsNode = FunctionDefParamsNode.parseFunctionDefParamsNode(inputTokens);
        ParserUtils.removeToken(inputTokens, TokenType.R_BRACKET);
        ParserUtils.removeToken(inputTokens, TokenType.COLON);
        FunctionReturnNode myReturnNode = FunctionReturnNode.parseFunctionReturnNode(inputTokens);
        ParserUtils.removeToken(inputTokens,TokenType.L_BRACE);
        BodyNode myBodyNode = BodyNode.parseBodyNode(inputTokens);
        ParserUtils.removeToken(inputTokens, TokenType.R_BRACE);
        return new FunctionDefNode(myIDKeywordNode,myFunctionDefParamsNode,myReturnNode,myBodyNode);
    }
    @Override
    public String convertToJott() {
        String result = "";
        result += myIDKeywordNode.convertToJott();
        result += "[";
        result += myFunctionDefParamsNode.convertToJott();
        result += "]:";
        result += myReturnNode.convertToJott();
        result += "{";
        result += myBodyNode.convertToJott();
        result += "}";
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
