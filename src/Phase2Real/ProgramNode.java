package Phase2Real;

import java.util.ArrayList;
public class ProgramNode implements JottTree{
    private ArrayList<FunctionDefNode> functionDefs;

    private ProgramNode(ArrayList<FunctionDefNode> functionDefs) {
        this.functionDefs = functionDefs;
    }

    public static ProgramNode parseProgramNode(ArrayList<Token> tokens) {
        ArrayList<FunctionDefNode> functionDefs = new ArrayList<>();
        while (!tokens.isEmpty()) {
            functionDefs.add(FunctionDefNode.parseFunctionDefNode(tokens));
        }
        return  new ProgramNode(functionDefs);
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
        return false;
    }
}
