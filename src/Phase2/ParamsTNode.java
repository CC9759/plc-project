package Phase2;

import org.w3c.dom.Node;

import java.util.*;
public class ParamsTNode implements JottTree {
    NodeType myType = NodeType.PARAMST;
    List<JottTree> children = new ArrayList<>();
    public ParamsTNode(List<Token> inputTokens) {
        if(inputTokens.get(0).getTokenType() != TokenType.R_BRACKET) {
            inputTokens.remove(0);  //Strip comma
            children.add(ExpressionNode.ParseExpressionNode(inputTokens));
            children.add(new ParamsTNode(inputTokens));
        }
    }
    public String convertToJott() {
        StringBuilder returnMe = new StringBuilder();
        returnMe.append(",");
        for(JottTree child : children) {
            returnMe.append(child.convertToJott());
        }
        return returnMe.toString();
    }

    public NodeType getMyType() {
        return myType;
    }

    public String convertToJava() {return null;}
    public String convertToC() {return null;}
    public String convertToPython() {return null;}
    public boolean validateTree() {return true;}
    public String toString() {return null;}
}
