package Phase2Real;

import Phase1.JottTokenizer;

import java.beans.Expression;
import java.util.*;

public class Phase2Testing {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Input test case:");
        System.out.println("1: ConstantNode");
        System.out.println("2: IDKeywordNode");
        System.out.println("3: ExpressionNode_Boolean");
        System.out.println("4: ExpressionNode_Double");
        System.out.println("5: ExpressionNode_Integer");
        System.out.println("6: ExpressionNode_String");
        System.out.println("7: FunctionDefNode");
        System.out.println("8: Phase 2 test cases");

        String input = in.nextLine();

        int choice = Integer.parseInt(input);
        ArrayList<Token> testList = new ArrayList<Token>();

        switch(choice) {
            case 1:
                System.out.println("Input a number.");
                testList.add(new Token(in.nextLine(), "tester", -1, TokenType.NUMBER));
                ConstantNode testConstantNode = ConstantNode.parseConstantNode(testList);
                System.out.println(testConstantNode.convertToJott());
                System.out.println("Input a string.");
                testList.add(new Token(in.nextLine(), "tester", -1, TokenType.STRING));
                ConstantNode testConstantNode2 = ConstantNode.parseConstantNode(testList);
                System.out.println(testConstantNode2.convertToJott());
                break;
            case 2:
            case 3:
            case 4:
                System.out.println("Testing on the double expression 2.3, using an expressionNode_Double.");
                testList.add(new Token("2.3", TokenType.NUMBER));
                ExpressionNode_Double testExpressionDoubleNodeShortShort = new ExpressionNode_Double(testList);
                System.out.println(testExpressionDoubleNodeShortShort.convertToJott());
                System.out.println("Testing on the double expression 2.3 + 22.0, using an expressionNode_Double.");
                testList.add(new Token("2.3", TokenType.NUMBER));
                testList.add(new Token("+", TokenType.MATH_OP));
                testList.add(new Token("22.0", TokenType.NUMBER));
                ExpressionNode_Double testExpressionDoubleNodeShort = new ExpressionNode_Double(testList);
                System.out.println(testExpressionDoubleNodeShort.convertToJott());
                System.out.println("Testing on the double expression 2.3+22.0-46.2*66.8/11.7, using an expressionNode.");
                testList.add(new Token("2.3", TokenType.NUMBER));
                testList.add(new Token("+", TokenType.MATH_OP));
                testList.add(new Token("22.0", TokenType.NUMBER));
                testList.add(new Token("-", TokenType.MATH_OP));
                testList.add(new Token("46.2", TokenType.NUMBER));
                testList.add(new Token("*", TokenType.MATH_OP));
                testList.add(new Token("66.8", TokenType.NUMBER));
                testList.add(new Token("/", TokenType.MATH_OP));
                testList.add(new Token("11.7", TokenType.NUMBER));
                ExpressionNode testExpressionDoubleNode = ExpressionNode.parseExpressionNode(testList);
                System.out.println(testExpressionDoubleNode.convertToJott());
                System.out.println("Testing on the double expression 2.3+22.0-doublenumber*66.8/anotherdoublenumber, using an expressionNode.");
                testList.add(new Token("2.3", TokenType.NUMBER));
                testList.add(new Token("+", TokenType.MATH_OP));
                testList.add(new Token("22.0", TokenType.NUMBER));
                testList.add(new Token("-", TokenType.MATH_OP));
                testList.add(new Token("doublenumber", TokenType.NUMBER));
                testList.add(new Token("*", TokenType.MATH_OP));
                testList.add(new Token("66.8", TokenType.NUMBER));
                testList.add(new Token("/", TokenType.MATH_OP));
                testList.add(new Token("anotherdoublenumber", TokenType.NUMBER));
                ExpressionNode testExpressionDoubleNodeWords = ExpressionNode.parseExpressionNode(testList);
                System.out.println(testExpressionDoubleNodeWords.convertToJott());
                break;
            case 5:
                System.out.println("Testing on the double expression 2, using an expressionNode_Double.");
                testList.add(new Token("2", TokenType.NUMBER));
                ExpressionNode_Double testExpressionIntegerNodeShortShort = new ExpressionNode_Double(testList);
                System.out.println(testExpressionIntegerNodeShortShort.convertToJott());
                System.out.println("Testing on the double expression 2 + 22, using an expressionNode_Double.");
                testList.add(new Token("2", TokenType.NUMBER));
                testList.add(new Token("+", TokenType.MATH_OP));
                testList.add(new Token("22", TokenType.NUMBER));
                ExpressionNode_Double testExpressionIntegerNodeShort = new ExpressionNode_Double(testList);
                System.out.println(testExpressionIntegerNodeShort.convertToJott());
                System.out.println("Testing on the double expression 2+22-46*66/11, using an expressionNode.");
                testList.add(new Token("2", TokenType.NUMBER));
                testList.add(new Token("+", TokenType.MATH_OP));
                testList.add(new Token("22", TokenType.NUMBER));
                testList.add(new Token("-", TokenType.MATH_OP));
                testList.add(new Token("46", TokenType.NUMBER));
                testList.add(new Token("*", TokenType.MATH_OP));
                testList.add(new Token("66", TokenType.NUMBER));
                testList.add(new Token("/", TokenType.MATH_OP));
                testList.add(new Token("11", TokenType.NUMBER));
                ExpressionNode testExpressionIntegerNode = ExpressionNode.parseExpressionNode(testList);
                System.out.println(testExpressionIntegerNode.convertToJott());
                break;
            case 6:

            case 7:
                System.out.println("testing on the function definition in phase1Example");
                ArrayList<Token> tokens = JottTokenizer.tokenize("C:\\Users\\15164\\IdeaProjects\\plc-project\\tokenizerTestCases\\phase1Example.jott");
                ProgramNode result = ProgramNode.parseProgramNode(tokens);
                System.out.println(result.convertToJott());
                break;
            case 8:
                ArrayList<Token> tokens2 = JottTokenizer.tokenize("C:\\Users\\15164\\IdeaProjects\\plc-project\\parserTestCases\\providedExample5.jott");
                ProgramNode result2 = ProgramNode.parseProgramNode(tokens2);
                System.out.println(result2.convertToJott());

        }
    }
}
