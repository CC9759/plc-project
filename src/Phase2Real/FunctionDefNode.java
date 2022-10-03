package Phase2Real;

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
    public static FunctionDefNode parseFunctionDefNode(ArrayList<Token> inputTokens) {
        IDKeywordNode myIDKeywordNode = IDKeywordNode.parseIdKeyWordNode(inputTokens);
        //TODO check that types are correct
        inputTokens.remove(0); // [
        FunctionDefParamsNode myFunctionDefParamsNode = FunctionDefParamsNode.parseFunctionDefParamsNode(inputTokens);
        inputTokens.remove(0); // ]
        inputTokens.remove(0); // :
        FunctionReturnNode myReturnNode = FunctionReturnNode.parseFunctionReturnNode(inputTokens);
        inputTokens.remove(0); // {
        BodyNode myBodyNode = BodyNode.parseBodyNode(inputTokens);
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
