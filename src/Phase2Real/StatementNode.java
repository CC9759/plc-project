package Phase2Real;

import java.util.ArrayList;

public abstract class StatementNode extends BodyStatementNode{

    public static StatementNode parseStatementNode(ArrayList<Token> tokens){
        StatementNode result;
        Token firstToken = tokens.get(0);
        String firstTokenValue = firstToken.getToken();
        Token secondToken = tokens.get(1);
        String secondTokenValue = secondToken.getToken();
        Token thirdToken = tokens.get(2);
        String thirdTokenValue = thirdToken.getToken();
        boolean b = firstTokenValue.equals("Boolean") || firstTokenValue.equals("Integer") ||
                firstTokenValue.equals("String") || firstTokenValue.equals("Double");
        if (b && thirdTokenValue.equals(";")){
                result =  VarDeclarationNode.parseVariableDeclarationNode(tokens);

        } else if ((b && thirdTokenValue.equals("=")) || secondTokenValue.equals("=")){
            result = AssignmentNode.pasrseAssignmentNode(tokens);
        }else{
            result = FunctionCallNode.parseFunctionCallNode(tokens);
        }
        tokens.remove(0); //;
        return result;
    }

    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
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
    public abstract boolean validateTree();
}
