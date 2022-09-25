import java.util.*;
class ExpressionNode {
    List<JottTree> children = new ArrayList<>();
    private InformationType type;

    public ExpressionNode(List<Token> inputTokens) {
        //TODO ADD THIS WHEN SYMBOL TABLES ARE ADDED
        //GENERIC EXPRESSIONNODE WILL BE GENERATED
        Token firstToken = inputTokens.get(0);
        if(firstToken.getTokenType() == TokenType.ID_KEYWORD) {

        }
    }

    public String convertToJott() {
        String returnMe = "";
        for(int i = 0; i < children.size(); i++) {
            returnMe += children.get(i);
        }
        return returnMe;
    }

    public String convertToJava() {return null;}
    public String convertToC() {return null;}
    public String convertToPython() {return null;}
    public boolean validateTree() {return true;}
    public String toString() {return null;}
}