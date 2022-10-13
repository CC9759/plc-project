package Phase2;
/**
 * This class is responsible for parsing and translating a Body Node in the JottTree
 *
 * @author Jonathon LoTempio, Halle Masaryk, Celina Chen, Kaiming Zhang
 **/

import java.util.*;

public class BodyNode implements JottTree { 
    final ArrayList<BodyStatementNode> bodyStatements;
    ReturnStatementNode returnStatement;

    private BodyNode() {
        bodyStatements = new ArrayList<BodyStatementNode>();
        returnStatement = null;
    }

    public static BodyNode parseBodyNode(ArrayList<Token> inputList) throws Exception{
        BodyNode bodyNode = new BodyNode();
        while (true){
            Token token = inputList.get(0);
    
            if(token.getToken().equals("return")){
                bodyNode.returnStatement = ReturnStatementNode.ParseReturnStatementNode(inputList);
                break;
            }
            else if(token.getTokenType() == TokenType.R_BRACE){
                break;
                //return null;
            }
            else{
                bodyNode.bodyStatements.add(BodyStatementNode.parseBodyStatementNode(inputList));
            }

        }
        return bodyNode;
    }

    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    public String convertToJott() {
        StringBuilder finalString = new StringBuilder();

        for(BodyStatementNode i: bodyStatements){
            finalString.append(i.convertToJott());
        }
        if(returnStatement != null){
            finalString.append(returnStatement.convertToJott());
        }
        return finalString.toString();
    }

    /**
     * Will output a string of this tree in Java
     * @return a string representing the Java code of this tree
     */
    public String convertToJava() {
        return null;
    }

    /**
     * Will output a string of this tree in C
     * @return a string representing the C code of this tree
     */
    public String convertToC() {
        return null;
    }

    /**
     * Will output a string of this tree in Python
     * @return a string representing the Python code of this tree
     */
    public String convertToPython() {
        return null;
    }

    /**
     * This will validate that the tree follows the semantic rules of Jott
     * Errors validating will be reported to System.err
     * @return true if valid Jott code; false otherwise
     */
    public boolean validateTree() {
        return true;
    }
}

