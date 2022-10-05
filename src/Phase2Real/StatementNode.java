package Phase2Real;

import Phase1.JottTokenizer;

import java.util.ArrayList;

public class StatementNode extends BodyStatementNode{
    JottTree node;
    public StatementNode(JottTree node) {
        this.node = node;
    }
    public static StatementNode parseStatementNode(ArrayList<Token> tokens) throws Exception{
        JottTree result;
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
        ParserUtils.removeToken(tokens,TokenType.SEMICOLON);
        return new StatementNode(result);
    }

    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    public String convertToJott() {
        return node.convertToJott() + ";";
    }


    /**
     * Will output a string of this tree in Java
     * @return a string representing the Java code of this tree
     */
    public String convertToJava() {        return null;
    }

    /**
     * Will output a string of this tree in C
     * @return a string representing the C code of this tree
     */
    public String convertToC() {        return null;
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
