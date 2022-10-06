package Phase2;

import java.util.ArrayList;

public class FunctionCallNode implements JottTree{

    private final IDKeywordNode id;
    private final ParamsNode params;


    private FunctionCallNode(IDKeywordNode idKeywordNode, ParamsNode paramsNode){
        this.id = idKeywordNode;
        this.params = paramsNode;
    }
    public static FunctionCallNode parseFunctionCallNode(ArrayList<Token> tokens) throws Exception{
        IDKeywordNode idKeywordNode = IDKeywordNode.parseIdKeyWordNode(tokens);
        ParserUtils.removeToken(tokens,TokenType.L_BRACKET);
        ParamsNode paramsNode = ParamsNode.parseParamsNode(tokens);
        ParserUtils.removeToken(tokens,TokenType.R_BRACKET);
        return new FunctionCallNode(idKeywordNode, paramsNode);
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
    public String convertToJava(){return null;}

    /**
     * Will output a string of this tree in C
     * @return a string representing the C code of this tree
     */
    public String convertToC(){return null;}

    /**
     * Will output a string of this tree in Python
     * @return a string representing the Python code of this tree
     */
    public String convertToPython(){return null;}

    /**
     * This will validate that the tree follows the semantic rules of Jott
     * Errors validating will be reported to System.err
     * @return true if valid Jott code; false otherwise
     */
    public boolean validateTree(){return false;}
}