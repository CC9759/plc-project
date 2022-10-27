package Phase2;
/**
 * This class is responsible for parsing and translating a Program Node in the JottTree
 *
 * @author Jonathon LoTempio, Halle Masaryk, Celina Chen, Kaiming Zhang
 **/

import java.util.ArrayList;
import java.util.HashMap;

public class ProgramNode implements JottTree{
    private final ArrayList<FunctionDefNode> functionDefs;

    public HashMap<String, FunctionDefNode> globalSymbolTable;

    private ProgramNode(ArrayList<FunctionDefNode> functionDefs, HashMap<String, FunctionDefNode> table) {
        this.functionDefs = functionDefs;
        this.globalSymbolTable = table;
    }

    public static ProgramNode parseProgramNode(ArrayList<Token> tokens) {
        ArrayList<FunctionDefNode> functionDefs = new ArrayList<>();
        HashMap<String, FunctionDefNode> newTable = new HashMap<>();
        try {
            while (!tokens.isEmpty()) {

                FunctionDefNode function = FunctionDefNode.parseFunctionDefNode(tokens);
                functionDefs.add(function);
                newTable.put(function.myIDKeywordNode.value, function);

            }
            return new ProgramNode(functionDefs, newTable);
        } catch(Exception e) {
          System.err.println(e.getMessage());
          return null;
        }
    }

    @Override
    public String convertToJott() {
        StringBuilder result = new StringBuilder();
        for(FunctionDefNode functionDef: functionDefs) {
            result.append(functionDef.convertToJott());
        }
        return result.toString();
    }

    @Override
    public String convertToJava() {
        return null;
    }

    @Override
    public String convertToC() {
        return null;
    }

    @Override
    public String convertToPython() {
        return null;
    }

    @Override
    public boolean validateTree() {
        for (FunctionDefNode node:  functionDefs
             ) {

            if(!node.validateTree()){
                return false;
            }
        }
        return true;
    }
}
