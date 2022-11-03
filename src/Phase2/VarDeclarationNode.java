package Phase2;
/**
 * This class is responsible for parsing and translating a Variable Declaration Node in the JottTree
 *
 * @author Jonathon LoTempio, Halle Masaryk, Celina Chen, Kaiming Zhang
 **/

import java.util.ArrayList;
import java.util.HashMap;

public class VarDeclarationNode implements JottTree{

    public HashMap<String, String> localSymbolTable;
    private final String type;
    private final IDKeywordNode id;


    private VarDeclarationNode(Token typeToken, IDKeywordNode idKeywordNode, HashMap<String, String> localSymbolTable){
        this.type = typeToken.getToken();
        this.id = idKeywordNode;
        this.localSymbolTable = localSymbolTable;
    }

    static VarDeclarationNode parseVariableDeclarationNode(ArrayList<Token> tokens, HashMap<String, String> localSymbolTable) throws Exception{
        Token typeToken = tokens.remove(0);
        IDKeywordNode idNode = IDKeywordNode.parseIdKeyWordNode(tokens);
        localSymbolTable.put(idNode.value, typeToken.getToken());
        return new VarDeclarationNode(typeToken, idNode, localSymbolTable);
    }

    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    public String convertToJott(){return type + " " + id.convertToJott();}

    /**
     * Will output a string of this tree in Java
     * @return a string representing the Java code of this tree
     */
    public String convertToJava(){return type + " " + id.convertToJava();}

    /**
     * Will output a string of this tree in C
     * @return a string representing the C code of this tree
     */
    public String convertToC(){return type + " " + id.convertToC();}

    /**
     * Will output a string of this tree in Python
     * @return a string representing the Python code of this tree
     */
    public String convertToPython(){return id.convertToPython();}

    /**
     * This will validate that the tree follows the semantic rules of Jott
     * Errors validating will be reported to System.err
     * @return true if valid Jott code; false otherwise
     */
    public boolean validateTree(){return true;}

}
