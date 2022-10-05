package Phase2Real;

import java.util.*;

public class ExpressionNode implements JottTree {
    IDKeywordNode idKeywordNode;
    Token operator;
    ExpressionNode node;

    public ExpressionNode(IDKeywordNode idKeywordNode, Token operator, ExpressionNode node) {
        this.idKeywordNode = idKeywordNode;
        this.operator = operator;
        this.node = node;
    }
    public static ExpressionNode parseExpressionNode(ArrayList<Token> inputList) throws Exception {
        Token firstToken = inputList.get(0);
        if(firstToken.getTokenType() == TokenType.NUMBER) {
            if(firstToken.getToken().contains(".")) {
                return new ExpressionNode_Double(inputList);
            }
            return new ExpressionNode_Integer(inputList);
        }
        else if(firstToken.getTokenType() == TokenType.STRING) {
            return new ExpressionNode_String(inputList);
        }
        else if(firstToken.getTokenType() == TokenType.ID_KEYWORD) {
            IDKeywordNode idKeywordNode = IDKeywordNode.parseIdKeyWordNode(inputList);
            if(inputList.get(0).getTokenType() == TokenType.REL_OP || inputList.get(0).getTokenType() == TokenType.MATH_OP) {
                Token operator = inputList.remove(0);
                ExpressionNode node = ExpressionNode.parseExpressionNode(inputList);
                return new ExpressionNode(idKeywordNode,operator, node);
            }
            else {
                return new ExpressionNode(idKeywordNode, null, null);
            }
            //TODO UNSURE ABOUT THIS, RETURN GENERIC? RETURN SOMETHING? IDK
        }
        return null;
    }
    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    public String convertToJott() {
        //Should be fully overwritten
        String result = idKeywordNode.convertToJott();
        if (operator != null) {
            result += operator.getToken();
            result += node.convertToJott();
        }

        return result;
    }

    /**
     * Will output a string of this tree in Java
     * @return a string representing the Java code of this tree
     */
    public String convertToJava() {
        return null;
    }

    /**
     * Will output a string of this tree in C
     * @return a string representing the C code of this tree
     */
    public String convertToC() {
        return null;
    }

    /**
     * Will output a string of this tree in Python
     * @return a string representing the Python code of this tree
     */
    public String convertToPython() {
        return null;
    }

    /**
     * This will validate that the tree follows the semantic rules of Jott
     * Errors validating will be reported to System.err
     * @return true if valid Jott code; false otherwise
     */
    public boolean validateTree() {
        return true;
    }
}
