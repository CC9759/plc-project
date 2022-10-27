package Phase2;
/**
 * This class is responsible for parsing and translating a Function Def Node in the JottTree
 *
 * @author Jonathon LoTempio, Halle Masaryk, Celina Chen, Kaiming Zhang
 **/

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class FunctionDefNode implements JottTree{

    public HashMap<String, String> localSymbolTable;
    public  ArrayList<String> paramTypes;
    public String returnType;
    final IDKeywordNode myIDKeywordNode;
    final FunctionDefParamsNode myFunctionDefParamsNode;
    final FunctionReturnNode myReturnNode;
    final BodyNode myBodyNode;
    private FunctionDefNode(IDKeywordNode myIDKeywordNode, FunctionDefParamsNode myFunctionDefParamsNode, FunctionReturnNode myReturnNode, BodyNode myBodyNode, HashMap<String, String> table) {
        this.myIDKeywordNode = myIDKeywordNode;
        this.myFunctionDefParamsNode = myFunctionDefParamsNode;
        this.myReturnNode = myReturnNode;
        this. returnType = myReturnNode.returnToken.getToken();
        for (Token temp: myFunctionDefParamsNode.getParamTypes()
             ) {
            this.paramTypes.add(temp.getToken());

        }
        this.myBodyNode = myBodyNode;
        this.localSymbolTable = table;
    }
    public static FunctionDefNode parseFunctionDefNode(ArrayList<Token> inputTokens) throws Exception {
        HashMap<String, String> localSymbolTable = new HashMap<>();
        IDKeywordNode myIDKeywordNode = IDKeywordNode.parseIdKeyWordNode(inputTokens);
        //TODO check that types are correct
        ParserUtils.removeToken(inputTokens, TokenType.L_BRACKET);
        FunctionDefParamsNode myFunctionDefParamsNode = FunctionDefParamsNode.parseFunctionDefParamsNode(inputTokens);
        ParserUtils.removeToken(inputTokens, TokenType.R_BRACKET);
        ParserUtils.removeToken(inputTokens, TokenType.COLON);
        FunctionReturnNode myReturnNode = FunctionReturnNode.parseFunctionReturnNode(inputTokens);
        ParserUtils.removeToken(inputTokens,TokenType.L_BRACE);
        BodyNode myBodyNode = BodyNode.parseBodyNode(inputTokens, localSymbolTable);
        ParserUtils.removeToken(inputTokens, TokenType.R_BRACE);
        return new FunctionDefNode(myIDKeywordNode,myFunctionDefParamsNode,myReturnNode,myBodyNode, localSymbolTable);
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
        return (this.myIDKeywordNode.validateTree() && this.myFunctionDefParamsNode.validateTree() &&
        this.myReturnNode.validateTree() && this.myBodyNode.validateTree());
    }

    public boolean returnable() {
        return myBodyNode.returnable();
    }
}
