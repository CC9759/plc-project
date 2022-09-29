package Phase2;
import Phase2Real.JottTree;
import Phase2Real.Token;

import java.util.ArrayList;
import java.util.List;

public class FunctionCallNode implements JottTree {
    private final IDKeywordNode id;
    private final ParamsNode params;


    public FunctionCallNode(List<Token> tokens){
        ArrayList<Token> idList = new ArrayList<>();
        ArrayList<Token> paramsList = (ArrayList<Token>) tokens;
        idList.add(tokens.get(0));
        paramsList.remove(0);

        this.id = new IDKeywordNode(idList);
        this.params = new ParamsNode(paramsList);

    }
    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    public String convertToJott(){return id.convertToJott() + "[" + params.convertToJott() + "]";}

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
