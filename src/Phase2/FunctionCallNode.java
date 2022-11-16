package Phase2;
/**
 * This class is responsible for parsing and translating a Function Call Node in the JottTree
 *
 * @author Jonathon LoTempio, Halle Masaryk, Celina Chen, Kaiming Zhang
 **/

import java.util.ArrayList;
import java.util.HashMap;

public class FunctionCallNode implements JottTree{

    public final IDKeywordNode id;
    private final ParamsNode params;
    public HashMap<String, InformationType> localSymbolTable;

    private FunctionCallNode(IDKeywordNode idKeywordNode, ParamsNode paramsNode, HashMap<String, InformationType> localSymbolTable){
        this.id = idKeywordNode;
        this.params = paramsNode;
        this.localSymbolTable = localSymbolTable;
    }
    public static FunctionCallNode parseFunctionCallNode(ArrayList<Token> tokens, HashMap<String, InformationType> localSymbolTable) throws Exception{
        IDKeywordNode idKeywordNode = IDKeywordNode.parseIdKeyWordNode(tokens);
        ParserUtils.removeToken(tokens,TokenType.L_BRACKET);
        ParamsNode paramsNode = ParamsNode.parseParamsNode(tokens, localSymbolTable);
        ParserUtils.removeToken(tokens,TokenType.R_BRACKET);
        return new FunctionCallNode(idKeywordNode, paramsNode, localSymbolTable);
    }

    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    //How are we dealing with parameters again?
    public String convertToJott(){return id.convertToJott() + "[" + params.convertToJott() + "]";}

    /**
     * Will output a string of this tree in Java
     * @return a string representing the Java code of this tree
     */
    public String convertToJava(){return id.convertToJava() + "(" + params.convertToJava() + ")";}

    /**
     * Will output a string of this tree in C
     * @return a string representing the C code of this tree
     */
    public String convertToC(){
        if(id.convertToC().equals("printf")){
            StringBuilder result = new StringBuilder();

            result.append("printf");
            result.append("(\"");
            try {
                switch(params.expressions.get(0).WhatAmI()){
                    case BOOLEAN: result.append("%b"); break;
                    case DOUBLE: result.append("%f"); break;
                    case INT: result.append("%d"); break;
                    case STRING: result.append("%s"); break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            result.append("\n\"");

            result.append(",");
            result.append(params.expressions.get(0).convertToC());
            result.append(")");
        }
        return id.convertToC() + "(" + params.convertToC() + ")";
    }

    /**
     * Will output a string of this tree in Python
     * @return a string representing the Python code of this tree
     */
    public String convertToPython(){return id.convertToPython() + "(" + params.convertToPython() + ")";}

    /**
     * This will validate that the tree follows the semantic rules of Jott
     * Errors validating will be reported to System.err
     * @return true if valid Jott code; false otherwise
     */
    public boolean validateTree(){
        if(id.value.equals("print")) {
            if (params.expressions.size() == 1) {
                return true;
            }
            return false;
        }
        if (ProgramNode.globalSymbolTable.containsKey(id.value)){
            FunctionDefNode function = ProgramNode.globalSymbolTable.get(id.value);
            if(function.paramTypes.size() != params.expressions.size()){
                return false;
            }
            for (int i = 0; i <function.paramTypes.size(); i ++){
                if(!(params.expressions.get(i).WhatAmI() == function.paramTypes.get(i))){
                    return false;
                }
            }
            return (id.validateTree() && params.validateTree());
        }
        return false;
    }
}