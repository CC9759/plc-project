package Phase2;

import java.util.ArrayList;

public class VarDeclaration implements JottTree{
    private final TypeNode type;
    private final IDKeywordNode id;
    private final EndStatementNode endStatement;


    public VarDeclaration(ArrayList <Token>tokens){
        ArrayList<Token> typeList = new ArrayList<>();
        ArrayList<Token> idList = new ArrayList<>();
        ArrayList<Token> endStatementList = new ArrayList<>();

        typeList.add(tokens.get(0));
        idList.add(tokens.get(1));
        endStatementList.add(tokens.get(2));

        this.type = new TypeNode(typeList);
        this.id = new IDKeywordNode(idList);
        this.endStatement = new EndStatementNode(endStatementList);
    }

    public String getType(){return type.getValue();}

    public String getId(){return id.getValue();}

    public String getEndStatement(){return endStatement.getValue();}

    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    public String convertToJott(){return getType()+getId()+getEndStatement();}

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
