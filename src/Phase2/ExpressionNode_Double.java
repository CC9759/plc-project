package Phase2;

import java.util.*;

public class ExpressionNode_Double extends ExpressionNode {
    IDKeywordNode myIDKeywordNode;
    ConstantNode myConstantNode;
    OpNode myOpNode;
    ExpressionNode_Double myFirstExpressionNode_Double;
    ExpressionNode_Double mySecondExpressionNode_Double;

    FunctionCallNode myFunctionCallNode;

    public ExpressionNode_Double(ArrayList<Token> inputTokens) throws Exception {
        super(null,null,null);
        if(inputTokens.size() == 0) {
            System.err.println("ExpressionNode_Double constructor got an input list of 0 tokens.");
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
        //<id>, <func_call>-><id>[params], <id><math_op><double_exp>
        if(inputTokens.get(0).getTokenType() == TokenType.ID_KEYWORD) {
            if(inputTokens.get(1).getTokenType() == TokenType.L_BRACKET) {
                //<func_call>-><id>[params]
                myFirstExpressionNode_Double = new ExpressionNode_Double(FunctionCallNode.parseFunctionCallNode(inputTokens));
                if(inputTokens.get(0).getTokenType() == TokenType.MATH_OP) {
                    myOpNode = OpNode.parseOpNode(inputTokens);
                    if(inputTokens.size() == 0) {
                        System.err.println("ExpressionNode_Double recieved a MATH_OP without a third field.");
                    }
                    else if(!(inputTokens.get(0).getTokenType() == TokenType.ID_KEYWORD) ||
                            inputTokens.get(0).getTokenType() == TokenType.NUMBER) {
                        System.err.println("ExpressionNode_Double expected ID_KEYWORD or NUMBER in third field, but recieved" + inputTokens.get(1).getTokenType() + ".");
                    } else {
                        //third node is correct start
                        mySecondExpressionNode_Double = new ExpressionNode_Double(inputTokens);
                    }
                }
            }
            else {
                //Starts with an id but is not a func call:<id>, <id><math_op><double_exp>
                myFirstExpressionNode_Double = new ExpressionNode_Double(inputTokens.remove(0));
                if(inputTokens.get(0).getTokenType() == TokenType.MATH_OP) {
                    myOpNode = OpNode.parseOpNode(inputTokens);
                    if(inputTokens.size() == 0) {
                        System.err.println("ExpressionNode_Double recieved a MATH_OP without a third field.");
                        throw new Exception();
                    }
                    //[3,infty)
                    else if(!(inputTokens.get(0).getTokenType() == TokenType.ID_KEYWORD) ||
                            inputTokens.get(0).getTokenType() == TokenType.NUMBER) {
                        System.err.println("ExpressionNode_Double expected ID_KEYWORD or NUMBER in third field, but recieved" + inputTokens.get(0).getTokenType() + ".");
                        throw new Exception();
                    } else {
                        //third node is correct start
                        mySecondExpressionNode_Double = new ExpressionNode_Double(inputTokens);
                    }
                }
            }
        }
        //starts with a number
        //<number>, <number><math_op><double_exp>
        else if(inputTokens.get(0).getTokenType() == TokenType.NUMBER) {
            myFirstExpressionNode_Double = new ExpressionNode_Double(inputTokens.remove(0));
            if(inputTokens.get(0).getTokenType() == TokenType.MATH_OP) {
                myOpNode = OpNode.parseOpNode(inputTokens);
                if (inputTokens.size() == 0) {
                    System.err.println("ExpressionNode_Double recieved a MATH_OP without a third field.");
                    throw new Exception();
                }
                //[3,infty)
                else if ((inputTokens.get(0).getTokenType() != TokenType.ID_KEYWORD) &&
                        inputTokens.get(0).getTokenType() != TokenType.NUMBER) {
                    System.err.println("ExpressionNode_Double expected ID_KEYWORD or NUMBER in third field, but recieved" + inputTokens.get(0).getTokenType() + ".");
                    throw new Exception();
                } else {
                    //third node is correct start
                    mySecondExpressionNode_Double = new ExpressionNode_Double(inputTokens);
                }
            }
        }
    }

    public ExpressionNode_Double(Token inputToken) {
        super(null,null,null);
        if(inputToken.getTokenType() == TokenType.ID_KEYWORD) {
            myIDKeywordNode = new IDKeywordNode(inputToken);
        }
        else {
            myConstantNode = new ConstantNode(inputToken);
        }
    }

    public ExpressionNode_Double(FunctionCallNode inputNode) {
        super(null,null,null);
        myFunctionCallNode = inputNode;
    }
    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    @Override
    public String convertToJott() {
        String returnMe = "";
        if(myFirstExpressionNode_Double != null) {
            returnMe = returnMe + myFirstExpressionNode_Double.convertToJott();
        }
        if(myOpNode != null) {
            returnMe = returnMe + myOpNode.convertToJott();
        }
        if(mySecondExpressionNode_Double != null) {
            returnMe = returnMe + mySecondExpressionNode_Double.convertToJott();
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

