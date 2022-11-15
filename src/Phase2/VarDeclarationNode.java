package Phase2;
/**
 * This class is responsible for parsing and translating a Variable Declaration Node in the JottTree
 *
 * @author Jonathon LoTempio, Halle Masaryk, Celina Chen, Kaiming Zhang
 **/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class VarDeclarationNode implements JottTree{

    public HashMap<String, InformationType> localSymbolTable;
    private final String type;
    private final IDKeywordNode id;


    private VarDeclarationNode(Token typeToken, IDKeywordNode idKeywordNode, HashMap<String, InformationType> localSymbolTable){
        this.type = typeToken.getToken();
        this.id = idKeywordNode;
        this.localSymbolTable = localSymbolTable;
    }

    static VarDeclarationNode parseVariableDeclarationNode(ArrayList<Token> tokens, HashMap<String, InformationType> localSymbolTable) throws Exception{
        Token typeToken = tokens.remove(0);
        IDKeywordNode idNode = IDKeywordNode.parseIdKeyWordNode(tokens);
        InformationType informationType = InformationType.VOID;
        if(Objects.equals(typeToken.getToken(), "Boolean")){
            informationType = InformationType.BOOLEAN;
        }else if(Objects.equals(typeToken.getToken(), "Double")){
            informationType = InformationType.DOUBLE;
        }else if(Objects.equals(typeToken.getToken(), "Integer")){
            informationType = InformationType.INT;
        }else if(Objects.equals(typeToken.getToken(), "String")){
            informationType = InformationType.STRING;
        }
        localSymbolTable.put(idNode.value, informationType);
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
    public String convertToC(){
        if(type.equals("String")){
            return "char " + id.convertToC() + "[];";
        }
        return type + " " + id.convertToC();
    }

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
