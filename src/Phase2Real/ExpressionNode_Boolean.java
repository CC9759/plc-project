package Phase2Real;

import java.util.*;

public class ExpressionNode_Boolean extends ExpressionNode {
    IDKeywordNode myIDKeywordNode;
    ConstantNode myConstantNode;
    OpNode myOpNode;
    ExpressionNode_Boolean myFirstExpressionNode_Boolean;
    ExpressionNode_Boolean mySecondExpressionNode_Boolean;

    FunctionCallNode myFunctionCallNode;

    public ExpressionNode_Boolean(ArrayList<Token> inputTokens) {
        if(inputTokens.size() == 0) {
            System.err.println("ExpressionNode_Boolean constructor got an input list of 0 tokens.");
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
        //<id>, <func_call>-><id>[params], <id><rel_op><boolean_exp>
        if(inputTokens.get(0).getTokenType() == TokenType.ID_KEYWORD) {
            if(inputTokens.get(1).getTokenType() == TokenType.L_BRACKET) {
                //<func_call>-><id>[params]
                myFirstExpressionNode_Boolean = new ExpressionNode_Boolean(FunctionCallNode.parseFunctionCallNode(inputTokens));
                if(inputTokens.get(0).getTokenType() == TokenType.REL_OP) {
                    myOpNode = OpNode.parseOpNode(inputTokens);
                    if(inputTokens.size() == 0) {
                        System.err.println("ExpressionNode_Boolean recieved a REL_OP without a third field.");
                    }
                    else if(!(inputTokens.get(2).getTokenType() == TokenType.ID_KEYWORD) ||
                            inputTokens.get(2).getTokenType() == TokenType.NUMBER) {
                        System.err.println("ExpressionNode_Boolean expected ID_KEYWORD or NUMBER in third field, but recieved" + inputTokens.get(1).getTokenType() + ".");
                    } else {
                        //third node is correct start
                        mySecondExpressionNode_Boolean = new ExpressionNode_Boolean(inputTokens);
                    }
                }
            }
            else {
                //Starts with an id but is not a func call:<id>, <id><rel_op><boolean_exp>
                myFirstExpressionNode_Boolean = new ExpressionNode_Boolean(inputTokens.remove(0));
                if(inputTokens.get(0).getTokenType() == TokenType.REL_OP) {
                    myOpNode = OpNode.parseOpNode(inputTokens);
                    if(inputTokens.size() == 0) {
                        System.err.println("ExpressionNode_Boolean recieved a REL_OP without a third field.");
                    }
                    //[3,infty)
                    else if(!(inputTokens.get(0).getTokenType() == TokenType.ID_KEYWORD) ||
                            inputTokens.get(0).getTokenType() == TokenType.NUMBER) {
                        System.err.println("ExpressionNode_Boolean expected ID_KEYWORD or NUMBER in third field, but recieved" + inputTokens.get(1).getTokenType() + ".");
                    } else {
                        //third node is correct start
                        mySecondExpressionNode_Boolean = new ExpressionNode_Boolean(inputTokens);
                    }
                }
            }
        }
        //starts with a number
        //<number>, <number><rel_op><boolean_exp>
        else if(inputTokens.get(0).getTokenType() == TokenType.NUMBER) {
            myFirstExpressionNode_Boolean = new ExpressionNode_Boolean(inputTokens.remove(0));
            if(inputTokens.get(0).getTokenType() == TokenType.REL_OP) {
                myOpNode = OpNode.parseOpNode(inputTokens);
                if (inputTokens.size() == 0) {
                    System.err.println("ExpressionNode_Boolean recieved a REL_OP without a third field.");
                }
                //[3,infty)
                else if ((inputTokens.get(0).getTokenType() != TokenType.ID_KEYWORD) &&
                        inputTokens.get(0).getTokenType() != TokenType.NUMBER) {
                    System.err.println("ExpressionNode_Boolean expected ID_KEYWORD or NUMBER in third field, but recieved" + inputTokens.get(1).getTokenType() + ".");
                } else {
                    //third node is correct start
                    mySecondExpressionNode_Boolean = new ExpressionNode_Boolean(inputTokens);
                }
            }
        }
    }

    public ExpressionNode_Boolean(Token inputToken) {
        if(inputToken.getTokenType() == TokenType.ID_KEYWORD) {
            myIDKeywordNode = new IDKeywordNode(inputToken);
        }
        else {
            myConstantNode = new ConstantNode(inputToken);
        }
    }

    public ExpressionNode_Boolean(FunctionCallNode inputNode) {
        myFunctionCallNode = inputNode;
    }
    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    @Override
    public String convertToJott() {
        String returnMe = "";
        if(myFirstExpressionNode_Boolean != null) {
            returnMe = returnMe + myFirstExpressionNode_Boolean.convertToJott();
        }
        if(myOpNode != null) {
            returnMe = returnMe + myOpNode.convertToJott();
        }
        if(mySecondExpressionNode_Boolean != null) {
            returnMe = returnMe + mySecondExpressionNode_Boolean.convertToJott();
        }
        if(myIDKeywordNode != null) {
            returnMe = returnMe + myIDKeywordNode.convertToJott();
        }
        if(myFunctionCallNode != null) {
            returnMe = returnMe + myFunctionCallNode.convertToJott();
        }
        if(myConstantNode != null) {
            returnMe = returnMe + myConstantNode.convertToJott();
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

