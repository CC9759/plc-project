package Phase2;

import java.util.*;

public class ExpressionNode_Integer extends ExpressionNode {
    IDKeywordNode myIDKeywordNode;
    ConstantNode myConstantNode;
    OpNode myOpNode;
    ExpressionNode_Integer myFirstExpressionNode_Integer;
    ExpressionNode_Integer mySecondExpressionNode_Integer;

    FunctionCallNode myFunctionCallNode;

    public ExpressionNode_Integer(ArrayList<Token> inputTokens) throws Exception {
        super(null,null,null);
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
                myFirstExpressionNode_Integer = new ExpressionNode_Integer(FunctionCallNode.parseFunctionCallNode(inputTokens));
                if(inputTokens.get(0).getTokenType() == TokenType.MATH_OP) {
                    Token testToken = inputTokens.get(0);
                    myOpNode = OpNode.parseOpNode(inputTokens);
                    if(inputTokens.size() == 0) {
                        throw new ParserException(testToken, "ExpressionNode_Integer recieved a MATH_OP without a third field.");
                    }
                    else if(!(inputTokens.get(0).getTokenType() == TokenType.ID_KEYWORD) ||
                            inputTokens.get(0).getTokenType() == TokenType.NUMBER) {
                        throw new ParserException(inputTokens.get(0),"ExpressionNode_Integer expected ID_KEYWORD or NUMBER in third field, but recieved" + inputTokens.get(0).getTokenType() + ".");
                    } else {
                        //third node is correct start
                        mySecondExpressionNode_Integer = new ExpressionNode_Integer(inputTokens);
                    }
                }
            }
            else {
                //Starts with an id but is not a func call:<id>, <id><math_op><integer_exp>
                myFirstExpressionNode_Integer = new ExpressionNode_Integer(inputTokens.remove(0));
                if(inputTokens.get(0).getTokenType() == TokenType.MATH_OP) {
                    Token testToken = inputTokens.get(0);
                    myOpNode = OpNode.parseOpNode(inputTokens);
                    if(inputTokens.size() == 0) {
                        throw new ParserException(testToken,"ExpressionNode_Integer recieved a MATH_OP without a third field.");
                    }
                    //[3,infty)
                    else if(!(inputTokens.get(0).getTokenType() == TokenType.ID_KEYWORD) ||
                            inputTokens.get(0).getTokenType() == TokenType.NUMBER) {
                        throw new ParserException(testToken, "ExpressionNode_Integer expected ID_KEYWORD or NUMBER in third field, but recieved"+inputTokens.get(0).getTokenType() + ".");
                    } else {
                        //third node is correct start
                        mySecondExpressionNode_Integer = new ExpressionNode_Integer(inputTokens);
                    }
                }
            }
        }
        //starts with a number
        //<number>, <number><math_op><integer_exp>
        else if(inputTokens.get(0).getTokenType() == TokenType.NUMBER) {
            myFirstExpressionNode_Integer = new ExpressionNode_Integer(inputTokens.remove(0));
            if(inputTokens.get(0).getTokenType() == TokenType.MATH_OP) {
                Token testToken = inputTokens.get(0);
                myOpNode = OpNode.parseOpNode(inputTokens);
                if (inputTokens.size() == 0) {
                    throw new ParserException(testToken, "ExpressionNode_Integer recieved a MATH_OP without a third field.");
                }
                //[3,infty)
                else if ((inputTokens.get(0).getTokenType() != TokenType.ID_KEYWORD) &&
                        inputTokens.get(0).getTokenType() != TokenType.NUMBER) {
                    throw new ParserException(testToken, "ExpressionNode_Integer expected ID_KEYWORD or NUMBER in third field, but recieved" + inputTokens.get(0).getTokenType() + ".");
                } else {
                    //third node is correct start
                    mySecondExpressionNode_Integer = new ExpressionNode_Integer(inputTokens);
                }
            }
        }
    }

    public ExpressionNode_Integer(Token inputToken) {
        super(null,null,null);
        if(inputToken.getTokenType() == TokenType.ID_KEYWORD) {
            myIDKeywordNode = new IDKeywordNode(inputToken);
        }
        else {
            myConstantNode = new ConstantNode(inputToken);
        }
    }

    public ExpressionNode_Integer(FunctionCallNode inputNode) {
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
        if(myFirstExpressionNode_Integer != null) {
            returnMe = returnMe + myFirstExpressionNode_Integer.convertToJott();
        }
        if(myOpNode != null) {
            returnMe = returnMe + myOpNode.convertToJott();
        }
        if(mySecondExpressionNode_Integer != null) {
            returnMe = returnMe + mySecondExpressionNode_Integer.convertToJott();
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

