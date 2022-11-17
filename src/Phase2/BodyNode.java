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
    public HashMap<String, InformationType> localSymbolTable;

    private BodyNode(HashMap<String, InformationType> localSymbolTable) {
        bodyStatements = new ArrayList<BodyStatementNode>();
        returnStatement = null;
        this.localSymbolTable = localSymbolTable;
    }

    public static BodyNode parseBodyNode(ArrayList<Token> inputList, HashMap<String, InformationType> localSymbolTable) throws Exception{
        BodyNode bodyNode = new BodyNode(localSymbolTable);
        while (true){
            Token token = inputList.get(0);
    
            if(token.getToken().equals("return")){
                bodyNode.returnStatement = ReturnStatementNode.ParseReturnStatementNode(inputList, localSymbolTable);
                break;
            }
            else if(token.getTokenType() == TokenType.R_BRACE){
                break;
                //return null;
            }
            else{
                bodyNode.bodyStatements.add(BodyStatementNode.parseBodyStatementNode(inputList, localSymbolTable));

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
        StringBuilder finalString = new StringBuilder();

        for(BodyStatementNode i: bodyStatements){
            finalString.append(i.convertToJava());
        }
        if(returnStatement != null){
            finalString.append(returnStatement.convertToJava());
        }
        return finalString.toString();
    }

    /**
     * Will output a string of this tree in C
     * @return a string representing the C code of this tree
     */
    public String convertToC() {
        StringBuilder finalString = new StringBuilder();

        for(BodyStatementNode i: bodyStatements){
            finalString.append(i.convertToC());
        }
        if(returnStatement != null){
            finalString.append(returnStatement.convertToC());
        }
        return finalString.toString();
    }

    /**
     * Will output a string of this tree in Python
     * @return a string representing the Python code of this tree
     */
    public String convertToPython() {
        StringBuilder finalString = new StringBuilder();

        for(BodyStatementNode i: bodyStatements){
            finalString.append(i.convertToPython());
        }
        finalString.append("\n");
        if(returnStatement != null){
            finalString.append(returnStatement.convertToPython());
        }
        return finalString.toString();
    }

    /**
     * This will validate that the tree follows the semantic rules of Jott
     * Errors validating will be reported to System.err
     * @return true if valid Jott code; false otherwise
     */
    public boolean validateTree() throws ParserException {
        for (BodyStatementNode bodyStatement: bodyStatements
             ) {
            if(!bodyStatement.validateTree()){
                return false;
            }
        }
        Boolean returnBool = true;

        if(returnStatement!=null){
            returnBool = returnStatement.validateTree();
        }

        return returnBool;
    }

    public ReturnStatementNode returnable() {
        if(returnStatement != null) {
            return returnStatement;
        }
        ReturnStatementNode returnable = null;
        for(int i = 0; i < bodyStatements.size(); i ++) {
            if(!bodyStatements.get(i).isStatement) {
                returnable = bodyStatements.get(i).returnable();
            }
        }
        return returnable;
    }
}

