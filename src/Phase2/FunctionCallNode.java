package Phase2;
/**
 * This class is responsible for parsing and translating a Function Call Node in the JottTree
 *
 * @author Jonathon LoTempio, Halle Masaryk, Celina Chen, Kaiming Zhang
 **/

import java.util.ArrayList;
import java.util.HashMap;

public class FunctionCallNode implements JottTree{

    private final IDKeywordNode id;
    private final ParamsNode params;
    public HashMap<String, String> localSymbolTable;

    private FunctionCallNode(IDKeywordNode idKeywordNode, ParamsNode paramsNode, HashMap<String, String> localSymbolTable){
        this.id = idKeywordNode;
        this.params = paramsNode;
        this.localSymbolTable = localSymbolTable;
    }
    public static FunctionCallNode parseFunctionCallNode(ArrayList<Token> tokens, HashMap<String, String> localSymbolTable) throws Exception{
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
    public String convertToC(){return id.convertToC() + "(" + params.convertToC() + ")";}

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
    public boolean validateTree(){return (
            id.validateTree() && params.validateTree());}
}