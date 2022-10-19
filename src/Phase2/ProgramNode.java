package Phase2;
/**
 * This class is responsible for parsing and translating a Program Node in the JottTree
 *
 * @author Jonathon LoTempio, Halle Masaryk, Celina Chen, Kaiming Zhang
 **/

import java.util.ArrayList;
public class ProgramNode implements JottTree{
    private final ArrayList<FunctionDefNode> functionDefs;

    private ProgramNode(ArrayList<FunctionDefNode> functionDefs) {
        this.functionDefs = functionDefs;
    }

    public static ProgramNode parseProgramNode(ArrayList<Token> tokens) {
        ArrayList<FunctionDefNode> functionDefs = new ArrayList<>();
        try {
            while (!tokens.isEmpty()) {
                functionDefs.add(FunctionDefNode.parseFunctionDefNode(tokens));
            }
            return new ProgramNode(functionDefs);
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
