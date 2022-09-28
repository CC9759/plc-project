package Phase2;

import java.util.*;

class ExpressionNode implements JottTree{
    NodeType myType = NodeType.EXPRESSION;
    List<JottTree> children = new ArrayList<>();
    public ExpressionNode() {}

    public static ExpressionNode ParseExpressionNode(List<Token> inputTokens) {
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
            return new BooleanExpressionNode(inputTokens);
        }
        return new StringExpressionNode(inputTokens);
    }

    public String convertToJott() {
        StringBuilder returnMe = new StringBuilder();
        for(JottTree child : children) {
            returnMe.append(child.convertToJott());
        }
        return returnMe.toString();
    }

    public String convertToJava() {return null;}
    public String convertToC() {return null;}
    public String convertToPython() {return null;}
    public boolean validateTree() {return true;}
    public String toString() {return null;}

    public NodeType getMyType() {
        return myType;
    }

}