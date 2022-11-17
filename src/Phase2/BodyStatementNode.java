package Phase2;
/**
 * This class is responsible for parsing and translating a Body Statement Node in the JottTree
 *
 * @author Jonathon LoTempio, Halle Masaryk, Celina Chen, Kaiming Zhang
 **/

import java.util.*;

public abstract class BodyStatementNode implements JottTree{
    public static boolean isStatement;

    public static BodyStatementNode parseBodyStatementNode(ArrayList<Token> tokens, HashMap<String, InformationType> localSymbolTable) throws Exception{
        Token check = tokens.get(0);
        if(check.getToken().equals("if")){
            return IfStatementNode.parseIfStatementNode(tokens, localSymbolTable);
        }
        else if(check.getToken().equals("while")){
            return WhileLoopNode.parseWhileLoopNode(tokens, localSymbolTable);
        }
        else{
            return StatementNode.parseStatementNode(tokens, localSymbolTable); //insert statement node parse here
        }
    }

    
    public abstract String convertToJott();

    /**
     * Will output a string of this tree in Java
     * @return a string representing the Java code of this tree
     */
    public abstract String convertToJava();

    /**
     * Will output a string of this tree in C
     * @return a string representing the C code of this tree
     */
    public abstract String convertToC();
    /**
     * Will output a string of this tree in Python
     * @return a string representing the Python code of this tree
     */
    public abstract String convertToPython();

    /**
     * This will validate that the tree follows the semantic rules of Jott
     * Errors validating will be reported to System.err
     * @return true if valid Jott code; false otherwise
     */
    public abstract boolean validateTree() throws ParserException, Exception;

    public abstract ReturnStatementNode returnable();
}
