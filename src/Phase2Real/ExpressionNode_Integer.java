package Phase2Real;

import java.util.*;

public class ExpressionNode_Integer extends ExpressionNode {
    IDKeywordNode myIDKeywordNode;
    ConstantNode myConstantNode;
    OpNode myOpNode;
    ExpressionNode_Integer myExpressionNode_Integer;

    FunctionCallNode myFunctionCallNode;

    public ExpressionNode_Integer(ArrayList<Token> inputTokens) {
        if(inputTokens.size() == 0) {
            System.err.println("ExpressionNode_Integer constructor got an input list of 0 tokens.");
            return;
        }
        //[1,infty)
        if(inputTokens.size() == 1) {
            if(inputTokens.get(0).getTokenType() == TokenType.ID_KEYWORD) {
                myIDKeywordNode = IDKeywordNode.parseIdKeyWordNode(inputTokens);
            }
            else if(inputTokens.get(0).getTokenType() == TokenType.NUMBER) {
                myConstantNode = ConstantNode.parseConstantNode(inputTokens);
            }
            return;
        }
        //[2,infty)
        //starts with an id
        //<id>, <func_call>-><id>[params], <id><math_op><integer_exp>
        if(inputTokens.get(0).getTokenType() == TokenType.ID_KEYWORD) {
            if(inputTokens.get(1).getTokenType() == TokenType.L_BRACKET) {
                //<func_call>-><id>[params]
                myFunctionCallNode = FunctionCallNode.parseFunctionCallNode(inputTokens);
            }
            else {
                //Starts with an id but is not a func call:<id>, <id><math_op><integer_exp>
                myIDKeywordNode = IDKeywordNode.parseIdKeyWordNode(inputTokens);
                if(inputTokens.get(1).getTokenType() == TokenType.MATH_OP) {
                    myOpNode = OpNode.parseOpNode(inputTokens);
                    if(inputTokens.size() == 2) {
                        System.err.println("ExpressionNode_Integer recieved a MATH_OP without a third field.");
                    }
                    //[3,infty)
                    else if(!(inputTokens.get(2).getTokenType() == TokenType.ID_KEYWORD) ||
                            inputTokens.get(2).getTokenType() == TokenType.NUMBER) {
                        System.err.println("ExpressionNode_Integer expected ID_KEYWORD or NUMBER in third field, but recieved" + inputTokens.get(1).getTokenType() + ".");
                    } else {
                        //third node is correct start
                        myExpressionNode_Integer = new ExpressionNode_Integer(inputTokens);
                    }
                }
            }
        }
        //starts with a number
        //<number>, <number><math_op><integer_exp>
        else if(inputTokens.get(0).getTokenType() == TokenType.NUMBER) {
            myConstantNode = ConstantNode.parseConstantNode(inputTokens);
            if(inputTokens.get(1).getTokenType() == TokenType.MATH_OP) {
                myOpNode = OpNode.parseOpNode(inputTokens);
                if (inputTokens.size() == 2) {
                    System.err.println("ExpressionNode_Integer recieved a MATH_OP without a third field.");
                }
                //[3,infty)
                else if (!(inputTokens.get(2).getTokenType() == TokenType.ID_KEYWORD) ||
                        inputTokens.get(2).getTokenType() == TokenType.NUMBER) {
                    System.err.println("ExpressionNode_Integer expected ID_KEYWORD or NUMBER in third field, but recieved" + inputTokens.get(1).getTokenType() + ".");
                } else {
                    //third node is correct start
                    myExpressionNode_Integer = new ExpressionNode_Integer(inputTokens);
                }
            }
        }
    }
    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    @Override
    public String convertToJott() {
        if(myFunctionCallNode != null) {
            return myFunctionCallNode.convertToJott();
        }
        String returnMe = null;
        if(myIDKeywordNode != null) {
            returnMe = myIDKeywordNode.convertToJott();
        }
        else {
            returnMe = myConstantNode.convertToJott();
        }

        if(myOpNode == null) {
            return returnMe;
        }
        else {
            returnMe = returnMe + " " + myOpNode.convertToJott() + myExpressionNode_Integer.convertToJott();
        }
        return returnMe;
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

