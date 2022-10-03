package Phase2Real;

import java.util.*;
import Phase2.ReturnStatementNode;

/*
 *  body_stmt > -> < if_stmt > | < while_loop > | < stmt >
 * < return_stmt > -> return < expr > < end_stmt >
 * < body > -> < body_stmt > < body > | < return_stmt > | e
 */
public class BodyNode implements JottTree { 
    ArrayList<BodyStatementNode> bodyStatements;
    ReturnStatementNode returnStatement;

    public BodyNode() {
        bodyStatements = new ArrayList<BodyStatementNode>();
        returnStatement = null;
    }

    public static BodyNode parseBodyNode(ArrayList<Token> inputList){
        BodyNode bodyNode = new BodyNode();
        while (true){
            Token token = inputList.get(0);
    
            if(token.getToken().equals("return")){
                bodyNode.returnStatement = ReturnStatementNode.ParseReturnStatementNode(inputList);
                break;
            }
            else if(token.getTokenType() == TokenType.R_BRACE){
                inputList.remove(0);
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
        String finalString = "";

        for(BodyStatementNode i: bodyStatements){
            finalString += i.convertToJott();
        }
        if(returnStatement != null){
            finalString += returnStatement.convertToJott();
        }
        return finalString;
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
