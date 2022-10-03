package Phase2Real;

import java.util.ArrayList;

public class FunctionDefNode implements JottTree{
    IDKeywordNode myIDKeywordNode;
    ParamsNode myParamsNode;
    FunctionReturnNode myReturnNode;
    BodyNode myBodyNode;
    public FunctionDefNode(IDKeywordNode myIDKeywordNode, ParamsNode myParamsNode, FunctionReturnNode myReturnNode, BodyNode myBodyNode) {
        this.myIDKeywordNode = myIDKeywordNode;
        this.myParamsNode = myParamsNode;
        this.myReturnNode = myReturnNode;
        this.myBodyNode = myBodyNode;
    }
    public static FunctionDefNode parseFunctionDefNode(ArrayList<Token> inputTokens) {
        IDKeywordNode myIDKeywordNode = IDKeywordNode.parseIdKeyWordNode(inputTokens);
        //TODO check that types are correct
        inputTokens.remove(0); // [
        ParamsNode myParamsNode = ParamsNode.parseParamsNode(inputTokens);
        inputTokens.remove(0); // ]
        inputTokens.remove(0); // :
        FunctionReturnNode myReturnNode = FunctionReturnNode.parseFunctionReturnNode(inputTokens);
        inputTokens.remove(0); // {
        BodyNode myBodyNode = BodyNode.parseBodyNode(inputTokens);
        return new FunctionDefNode(myIDKeywordNode,myParamsNode,myReturnNode,myBodyNode);
    }
    @Override
    public String convertToJott() {
        String result = "";
        result += myIDKeywordNode.convertToJott();
        result += "[";
        result += myParamsNode.convertToJott();
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
