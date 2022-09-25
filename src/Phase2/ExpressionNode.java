package Phase2;

import java.util.*;
class ExpressionNode implements JottTree{
    List<JottTree> children = new ArrayList<>();
    private InformationType type;
    public ExpressionNode() {}

    public ExpressionNode ExpressionNode(List<Token> inputTokens) {
        //TODO ADD THIS WHEN SYMBOL TABLES ARE ADDED
        //GENERIC EXPRESSIONNODE WILL BE GENERATED
        Token firstToken = inputTokens.get(0);
        if(firstToken.getTokenType() == TokenType.ID_KEYWORD) {
            //CHECK ID TYPE FROM SYMBOL TABLE, EMPTY FOR NOW
            //COULD ALSO BE FUNCTION CALL WITH PARAMS, NOT IMPLEMENTED
            return null;
        }
        else if(firstToken.getTokenType() == TokenType.NUMBER) {
            if(firstToken.getToken().contains(".")) {
                return new DoubleExpressionNode(inputTokens);
            }
            else {
                return new IntegerExpressionNode(inputTokens);
            }
        }
        else if(firstToken.getToken().equals("True") ||
                firstToken.getToken().equals("False")) {
            //TODO return new BooleanExpressionNode(inputTokens);
        }
        //TODO return new StringExpressionNode(inputTokens);
        return null;
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