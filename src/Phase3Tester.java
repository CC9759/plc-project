/*
  Jott parser tester. This will test the parsing phase of the Jott
  project.

  This tester assumes a working and valid tokenizer.
 */

import Phase1.JottParser;
import Phase1.JottTokenizer;
import Phase2.JottTree;
import Phase2.Token;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Phase3Tester {
    ArrayList<TestCase> testCases;

    private static class TestCase{
        String testName;
        String fileName;
        boolean error;

        public TestCase(String testName, String fileName, boolean error) {
            this.testName = testName;
            this.fileName = fileName;
            this.error = error;
        }
    }

    private boolean tokensEqualNoFileData(Token t1, Token t2){
        return t1.getTokenType() == t2.getTokenType() &&
                t1.getToken().equals(t2.getToken());
    }

    private void createTestCases(){
        this.testCases = new ArrayList<>();
        testCases.add(new TestCase("provided writeup example1", "providedExample1.jott", false ));
        testCases.add(new TestCase("hello world", "helloWorld.jott", false ));
        testCases.add(new TestCase("return <id> type mismatch", "returnId.jott", true ));
//        testCases.add(new TestCase("type:var error (error)", "paramOrderSwapped.jott", true ));
//        testCases.add(new TestCase("missing expr (error)", "missingExp.jott", true ));
//        testCases.add(new TestCase("missingBrace (error)", "missingBrace.jott", true ));
//        testCases.add(new TestCase("elseif without if (error)", "elseIfNoIf.jott", true ));
        testCases.add(new TestCase("missing return", "missingReturn.jott", true ));
//        testCases.add(new TestCase("Void not valid param type (error)", "voidParam.jott", true ));
        testCases.add(new TestCase("function has wrong param types", "funcWrongParamType.jott", true ));
        testCases.add(new TestCase("return inside if statement", "ifStmtReturns.jott", false ));
        testCases.add(new TestCase("large valid vild", "largerValid.jott", false ));
        testCases.add(new TestCase("missing function params", "missingFuncParams.jott", true ));
        testCases.add(new TestCase("missing return in if statement", "noReturnIf.jott", true ));
        testCases.add(new TestCase("no Return in while loop", "noReturnWhile.jott", true ));
        testCases.add(new TestCase("return in void function should be invalid", "voidReturn.jott", true ));



        testCases.add(new TestCase("function not defined", "funcNotDefined.jott", true ));
        testCases.add(new TestCase("mismatch return type", "mismatchedReturn.jott", true ));
        testCases.add(new TestCase("function call param type not matching", "funcCallParamInvalid.jott", true ));
//        testCases.add(new TestCase("single expression program (error)", "singleExpr.jott", true ));
        testCases.add(new TestCase("valid while loop", "validLoop.jott", false ));
        testCases.add(new TestCase("missing main", "missingMain.jott", true ));
        testCases.add(new TestCase("main must be integer", "mainReturnNotInt.jott", true ));
        testCases.add(new TestCase("i_expr relop d_expr function return", "funcReturnInExpr.jott", true ));
//        testCases.add(new TestCase("invalid asmt stmt (error)", "invalidAsmtStmt.jott", true ));
//        testCases.add(new TestCase("missing comma in func_def_params (error)", "missingCommaParams.jott", true ));
        testCases.add(new TestCase("while is keyword, cannot be used as id", "whileKeyword.jott", true ));
//        testCases.add(new TestCase("expr by itself (error)", "loneExpr.jott", true ));
//        testCases.add(new TestCase("code after return (error)", "codeAfterReturn.jott", true ));
//        testCases.add(new TestCase("lone minus (error)", "loneMinus.jott", true ));
//        testCases.add(new TestCase("else without if (error)", "elseNoIf.jott", true ));
//        testCases.add(new TestCase("missing closing } (error)", "missingClosing.jott", true ));
    }

    private boolean parserTest(TestCase test, String orginalJottCode){
        try {
            ArrayList<Token> tokens = JottTokenizer.tokenize("phase3TestCases/" + test.fileName);

            if (tokens == null) {
                System.err.println("\tFailed Test: " + test.testName);
                System.err.println("\t\tExpected a list of tokens, but got null");
                System.err.println("\t\tPlease verify your tokenizer is working properly");
                return false;
            }
            System.out.println(tokenListString(tokens));
            ArrayList<Token> cpyTokens = new ArrayList<>(tokens);
            JottTree root = JottParser.parse(tokens);

//            if (!test.error && root == null) {
//                System.err.println("\tFailed Test: " + test.testName);
//                System.err.println("\t\tExpected a JottTree and got null");
//                return false;
//            } else if (test.error && root == null) {
//                return true;
//            } else if (test.error) {
//                System.err.println("\tFailed Test: " + test.testName);
//                System.err.println("\t\tExpected a null and got JottTree");
//                return false;
//            }



            System.out.println("Orginal Jott Code:\n");
            System.out.println(orginalJottCode);
            System.out.println();

            String jottCode = root.convertToJott();
            System.out.println("Resulting Jott Code:\n");
            System.out.println(jottCode);
            boolean valid = root.validateTree();
            try {
                FileWriter writer = new FileWriter("phase3TestCases/parserTestTemp.jott");
                if (jottCode == null) {
                    System.err.println("\tFailed Test: " + test.testName);
                    System.err.println("Expected a program string; got null");
                    return false;
                }
                writer.write(jottCode);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ArrayList<Token> newTokens = JottTokenizer.tokenize("phase3TestCases/parserTestTemp.jott");

            if (newTokens == null) {
                System.err.println("\tFailed Test: " + test.testName);
                System.err.println("Tokenization of files dot not match.");
                System.err.println("Similar files should have same tokenization.");
                System.err.println("Expected: " + tokenListString(tokens));
                System.err.println("Got: null");
                return false;
            }

            if (newTokens.size() != cpyTokens.size()) {
                System.err.println("\tFailed Test: " + test.testName);
                System.err.println("Tokenization of files dot not match.");
                System.err.println("Similar files should have same tokenization.");
                System.err.println("Expected: " + tokenListString(cpyTokens));
                System.err.println("Got:    : " + tokenListString(newTokens));
                return false;
            }

            for (int i = 0; i < newTokens.size(); i++) {
                Token n = newTokens.get(i);
                Token t = cpyTokens.get(i);

                if (!tokensEqualNoFileData(n, t)) {
                    System.err.println("\tFailed Test: " + test.testName);
                    System.err.println("Token mismatch: Tokens do not match.");
                    System.err.println("Similar files should have same tokenization.");
                    System.err.println("Expected: " + tokenListString(cpyTokens));
                    System.err.println("Got     : " + tokenListString(newTokens));
                    return false;
                }
            }
            if(test.error && valid) {
                System.err.println("\tTest should have failed, but it passed as valid");
                return false;
            }
            else if (test.error && !valid) {
                System.out.println("\tTest is correctly invalid");
                return true;
            }
            return valid;
        }catch (Exception e){
            System.err.println("\tFailed Test: " + test.testName);
            System.err.println("Unknown Exception occured.");
            e.printStackTrace();
            return false;
        }
    }

    private String tokenListString(ArrayList<Token> tokens){
        StringBuilder sb = new StringBuilder();
        for (Token t: tokens) {
            sb.append(t.getToken());
            sb.append(":");
            sb.append(t.getTokenType().toString());
            sb.append(" ");
        }
        return sb.toString();
    }

    private boolean runTest(TestCase test){
        System.out.println("Running Test: " + test.testName);
        String orginalJottCode;
        try {
            orginalJottCode = new String(
                    Files.readAllBytes(Paths.get("phase3TestCases/" + test.fileName)));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return parserTest(test, orginalJottCode);

    }

    public static void main(String[] args) {
        System.out.println("NOTE: System.err may print at the end. This is fine.");
        Phase3Tester tester = new Phase3Tester();

        int numTests = 0;
        int passedTests = 0;
        tester.createTestCases();
        for(Phase3Tester.TestCase test: tester.testCases){
            numTests++;
            if(tester.runTest(test)){
                passedTests++;
                System.out.println("\tPassed\n");
            }
            else{
                System.out.println("\tFailed\n");
            }
        }

        System.out.printf("Passed: %d/%d%n", passedTests, numTests);
    }
}
