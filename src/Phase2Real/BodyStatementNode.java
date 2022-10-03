package Phase2Real;

import java.util.*;

/*
 * < body_stmt > -> < if_stmt > | < while_loop > | < stmt >
 * < if_stmt > -> if [ b_expr ]{ < body > } < elseif_lst > < else >
 * < while_loop > -> while [ b_expr ]{ < body> }
 * < stmt > -> < asmt > | < var_dec > | < func_call > < end_stmt >
 *  
 */
public abstract class BodyStatementNode implements JottTree{
    public static BodyStatementNode parseBodyStatementNode(ArrayList<Token> tokens){
        Token check = tokens.get(0);
        if(check.getToken().equals("if")){
            return null; //insert if statement node parse here
        }
        else if(check.getToken().equals("while")){
            return null; //insert while loop parse node
        }
        else{
            return StatementNode.parseStatementNode(tokens); //insert statement node parse here
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
    public abstract boolean validateTree();
}
