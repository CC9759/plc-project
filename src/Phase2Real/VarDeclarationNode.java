package Phase2Real;

import java.util.ArrayList;

public class VarDeclarationNode implements JottTree{
    private final String type;
    private final IDKeywordNode id;
    //private final EndStatementNode endStatement;


    private VarDeclarationNode(Token typeToken, IDKeywordNode idKeywordNode){ //EndStatementNode endStatementNode){
        this.type = typeToken.getToken();
        this.id = idKeywordNode;
        //this.endStatement = endStatementNode;
    }

    static VarDeclarationNode parseVariableDeclarationNode(ArrayList<Token> tokens) throws Exception{
        Token typeToken = tokens.remove(0);
        IDKeywordNode idNode = IDKeywordNode.parseIdKeyWordNode(tokens);
        return new VarDeclarationNode(typeToken, idNode);
        //EndStatementNode endStatementNode = EndStatementNode.parseEndExpressionNode(tokens);
        //return new VarDeclarationNode(typeToken, idNode, endStatementNode);
    }

    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    //We should add space correct?
    public String convertToJott(){return type + " " + id.convertToJott();}

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
