package Phase2;
/**
 * This class is responsible for parsing and translating an If Statement Node in the JottTree
 *
 * @author Jonathon LoTempio, Halle Masaryk, Celina Chen, Kaiming Zhang
 **/

import java.util.ArrayList;
import java.util.HashMap;

public class IfStatementNode extends BodyStatementNode{

    public HashMap<String, InformationType> localSymbolTable;

    public final boolean isStatement = false;

    private final ExpressionNode boolExpression;
    private final BodyNode body;

    private final ElseIfNode elsIf;
    private final ElseNode elseStatement;
    public IfStatementNode(ExpressionNode boolExpressionNode, BodyNode bodyNode, ElseIfNode elseIfNode, ElseNode elseNode, HashMap<String, InformationType> localSymbolTable) throws Exception{
        this.boolExpression = boolExpressionNode;
        this.body = bodyNode;
        this.elsIf = elseIfNode;
        this.elseStatement = elseNode;
        this.localSymbolTable = localSymbolTable;
    }
    public static IfStatementNode parseIfStatementNode(ArrayList<Token> tokens, HashMap<String, InformationType> localSymbolTable) throws Exception{
        tokens.remove(0); //remove if
        ParserUtils.removeToken(tokens,TokenType.L_BRACKET);
        ExpressionNode boolExpressionNode = ExpressionNode.parseExpressionNode(tokens, localSymbolTable);
        ParserUtils.removeToken(tokens, TokenType.R_BRACKET);
        ParserUtils.removeToken(tokens, TokenType.L_BRACE);
        BodyNode bodyNode = BodyNode.parseBodyNode(tokens, localSymbolTable);
        ParserUtils.removeToken(tokens, TokenType.R_BRACE);

        ElseIfNode elseIfNode = null;
        if(!tokens.isEmpty()){
            Token elseifToken = tokens.get(0);

            if(elseifToken.getToken().equals("elseif")) {
                tokens.remove(0);
                elseIfNode = ElseIfNode.parseElseIfNode(tokens, localSymbolTable);
            }
        }

        ElseNode elseNode = null;
        if(!tokens.isEmpty()){
            Token elseToken = tokens.get(0);

            if (elseToken.getToken().equals("else")){
//                tokens.remove(0); we remove else inside the else node as well. Removing it here for now
                elseNode = ElseNode.parseElseNode(tokens, localSymbolTable);
            }
        }

        return new IfStatementNode(boolExpressionNode, bodyNode, elseIfNode, elseNode, localSymbolTable);
    }
    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    public String convertToJott() {
        String result = "if[" + boolExpression.convertToJott()+ "]{"+ body.convertToJott()+"}";
        if (elsIf != null){
            result += elsIf.convertToJott();
        }
        if (elseStatement != null){
            result += elseStatement.convertToJott();
        }

        return result;

    }

    /**
     * Will output a string of this tree in Java
     * @return a string representing the Java code of this tree
     */
    public String convertToJava() {
        String result = "if (" + boolExpression.convertToJava()+ "){ "+ body.convertToJava()+ "}";
        if (elsIf != null){
            result += elsIf.convertToJava();
        }
        if (elseStatement != null){
            result += elseStatement.convertToJava();
        }

        return result;
    }

    /**
     * Will output a string of this tree in C
     * @return a string representing the C code of this tree
     */
    public String convertToC() {
        String result = "if (" + boolExpression.convertToC()+ "){ "+ body.convertToC()+ "}";
        if (elsIf != null){
            result += elsIf.convertToC();
        }
        if (elseStatement != null){
            result += elseStatement.convertToC();
        }

        return result;
    }

    /**
     * Will output a string of this tree in Python
     * @return a string representing the Python code of this tree
     */
    public String convertToPython() {
        String result = "if (" + boolExpression.convertToPython()+ "):\n\t"+ body.convertToPython().replace("\n", "\n\t");
        if (elsIf != null){
            result += elsIf.convertToPython();
        }
        if (elseStatement != null){
            result += elseStatement.convertToPython();
        }

        return result;
    }

    /**
     * This will validate that the tree follows the semantic rules of Jott
     * Errors validating will be reported to System.err
     * @return true if valid Jott code; false otherwise
     */
    public boolean validateTree() throws ParserException {
        boolean expressionBool = boolExpression.validateTree();
        boolean bodyBool = body.validateTree();
        boolean elseIfBool = true;
        boolean elseBool = true;

        if(elsIf!= null){elseIfBool = elsIf.validateTree();}
        if(elseStatement!=null){ elseBool = elseStatement.validateTree();}

        return (expressionBool && bodyBool && elseIfBool && elseBool);
    }

    public ReturnStatementNode returnable() {
        ReturnStatementNode bodyReturnable = null;
        ReturnStatementNode elifReturnable = null;
        ReturnStatementNode elseReturnable = null;
        boolean bodyHasReturn = false;
        boolean elifHasReturn = false;
        boolean elseHasReturn = false;
        if(body != null) {
            bodyReturnable = body.returnable();
            bodyHasReturn = true;
        }
        if(elsIf != null) {
            elifReturnable = elsIf.returnable();
            elifHasReturn = true;
        }
        if(elseStatement != null) {
            elseReturnable = elseStatement.returnable();
            elseHasReturn = true;
        }
        if(bodyHasReturn) {
            if(!elifHasReturn && !elseHasReturn) {
                return bodyReturnable;
            }
            if(elifHasReturn && !elseHasReturn) {
                if(elifReturnable != null && bodyReturnable != null && elifReturnable.getExpressionNode().WhatAmI() == bodyReturnable.getExpressionNode().WhatAmI()) {
                    return elifReturnable;
                }
                return null;
            }
            if(!elifHasReturn && elseHasReturn) {
                if(elseReturnable != null && bodyReturnable != null && elseReturnable.getExpressionNode().WhatAmI() == bodyReturnable.getExpressionNode().WhatAmI()) {
                    return elseReturnable;
                }
                return null;

            }
            if(elifHasReturn && elseHasReturn) {
                if(elifReturnable != null && bodyReturnable != null && elseReturnable != null && elifReturnable.getExpressionNode().WhatAmI() == bodyReturnable.getExpressionNode().WhatAmI() && elseReturnable.getExpressionNode().WhatAmI() == bodyReturnable.getExpressionNode().WhatAmI()) {
                    return bodyReturnable;
                }
                return null;

            }
        }
        return null;
    }

}
