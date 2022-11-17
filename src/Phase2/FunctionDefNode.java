package Phase2;
/**
 * This class is responsible for parsing and translating a Function Def Node in the JottTree
 *
 * @author Jonathon LoTempio, Halle Masaryk, Celina Chen, Kaiming Zhang
 **/

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class FunctionDefNode implements JottTree{

    public HashMap<String, InformationType> localSymbolTable;
    public  ArrayList<InformationType> paramTypes = new ArrayList<>();
    public InformationType returnType;
    final IDKeywordNode myIDKeywordNode;
    final FunctionDefParamsNode myFunctionDefParamsNode;
    final FunctionReturnNode myReturnNode;
    final BodyNode myBodyNode;
    private FunctionDefNode(IDKeywordNode myIDKeywordNode, FunctionDefParamsNode myFunctionDefParamsNode, FunctionReturnNode myReturnNode, BodyNode myBodyNode, HashMap<String, InformationType> table) {
        this.myIDKeywordNode = myIDKeywordNode;
        this.myFunctionDefParamsNode = myFunctionDefParamsNode;
        this.myReturnNode = myReturnNode;
        this.returnType = InformationType.VOID;
        if(Objects.equals(myReturnNode.returnToken.getToken(), "Boolean")){
            this.returnType = InformationType.BOOLEAN;
        }else if(Objects.equals(myReturnNode.returnToken.getToken(), "Double")){
            this.returnType = InformationType.DOUBLE;
        }else if(Objects.equals(myReturnNode.returnToken.getToken(), "Integer")){
            this.returnType = InformationType.INT;
        }else if(Objects.equals(myReturnNode.returnToken.getToken(), "String")){
            this.returnType = InformationType.STRING;
        }
        for (Token temp: myFunctionDefParamsNode.getParamTypes()
             ) {

            if(Objects.equals(temp.getToken(), "Boolean")){
                this.paramTypes.add(InformationType.BOOLEAN);
            }else if(Objects.equals(temp.getToken(), "Double")){
                this.paramTypes.add(InformationType.DOUBLE);
            }else if(Objects.equals(temp.getToken(), "Integer")){
                this.paramTypes.add(InformationType.INT);
            }else if(Objects.equals(temp.getToken(), "String")){
                this.paramTypes.add(InformationType.STRING);
            }else{
                this.paramTypes.add(InformationType.VOID);
            }

        }
        this.myBodyNode = myBodyNode;
        this.localSymbolTable = table;
    }
    public static FunctionDefNode parseFunctionDefNode(ArrayList<Token> inputTokens) throws Exception {
        HashMap<String, InformationType> localSymbolTable = new HashMap<>();
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
        String result = "public static";
        result += myReturnNode.convertToJava() + " ";
        result += myIDKeywordNode.convertToJava();
        result += "(";
        result += myFunctionDefParamsNode.convertToJava();
        result += "){";
        result += myBodyNode.convertToJava();
        result += "}";
        return result;
    }

    @Override
    public String convertToC() {
        String result = "";
        result += myReturnNode.convertToC() + " ";
        result += myIDKeywordNode.convertToC();
        result += "(";
        result += myFunctionDefParamsNode.convertToC();
        result += "){";
        result += myBodyNode.convertToC();
        result += "}";
        return result;
    }

    @Override
    public String convertToPython() {
        String result = "def ";
        result += myReturnNode.convertToPython() + " ";
        result += myIDKeywordNode.convertToPython();
        result += "(";
        result += myFunctionDefParamsNode.convertToPython();
        result += "):\n\t";
        result += myBodyNode.convertToPython().replace("\n", "\n\t");
        result += ("\n");
        return result;
    }

    @Override
    public boolean validateTree() {
        if(returnable() ==null && returnType!=InformationType.VOID){return false;}
        if(returnable() !=null && returnType==InformationType.VOID){return false;}
        return (this.myIDKeywordNode.validateTree() && this.myFunctionDefParamsNode.validateTree() &&
        this.myReturnNode.validateTree() && this.myBodyNode.validateTree());
    }

    public JottTree returnable() {
        return myBodyNode.returnable();
    }
}
