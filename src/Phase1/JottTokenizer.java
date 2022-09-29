package Phase1; /**
 * This class is responsible for tokenizing Jott code.
 * 
 * @author Jonathon LoTempio, Halle Masaryk, Celina, Kaiming Zhang
 **/

import DFAClasses.DFA;
import DFAClasses.TokenTree;
import Phase2Real.Token;
import Phase2Real.TokenType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class JottTokenizer {
    /**
     * Takes in a file object and returns the lines as a list of strings
     *
     * @param filename a file object to be parsed
     * @return an ArrayList of Strings
     */
    public static ArrayList<String> getLines(String filename) throws FileNotFoundException{
        ArrayList<String> returnMe = new ArrayList<>();

        Scanner myReader = null;
        myReader = new Scanner(new File(filename));

        while (myReader.hasNextLine()) {
            returnMe.add(myReader.nextLine());
        }
        myReader.close();
        return returnMe;
    }

    /**
     * Takes in a filename and tokenizes that file into Tokens
     * based on the rules of the Jott Language
     *
     * @param filename the name of the file to tokenize; can be relative or absolute path
     * @return an ArrayList of Jott Tokens
     */
    public static ArrayList<Token> tokenize(String filename) {
        try {
            ArrayList<String> fileLines = getLines(filename);
            HashMap<String, TokenType> tokenTypeMap = createTokenTypeMap();
            DFA tokenTree = TokenTree.createTokenTree();
            ArrayList<Token> tokens = new ArrayList<>();
            for (int i = 0; i < fileLines.size(); i++) {

                ArrayList<String> stringTokens = tokenTree.execute(fileLines.get(i), i + 1, filename);
                if (stringTokens == null) {
                    return null;
                }
                for (String stringToken : stringTokens) {
                    String[] tokenValues = stringToken.split(" ", 2);
                    Token newToken = new Token(tokenValues[1], filename, i + 1, tokenTypeMap.get(tokenValues[0]));
                    tokens.add(newToken);
                }
            }
            return tokens;
        } catch (RuntimeException e) {
            System.err.println(e);
            return null;
        } catch (FileNotFoundException e) {
            System.err.println("File: " + filename + " not found");
            return null;
        }
    }

    private static HashMap<String, TokenType> createTokenTypeMap() {

        HashMap<String, TokenType> tokenTypeMap = new HashMap<>();
        tokenTypeMap.put("comma", TokenType.COMMA);
        tokenTypeMap.put("rbracket", TokenType.R_BRACKET);
        tokenTypeMap.put("lbracket", TokenType.L_BRACKET);
        tokenTypeMap.put("rbrace", TokenType.R_BRACE);
        tokenTypeMap.put("lbrace", TokenType.L_BRACE);
        tokenTypeMap.put("assign", TokenType.ASSIGN);
        tokenTypeMap.put("relOp1", TokenType.REL_OP);
        tokenTypeMap.put("relOp2", TokenType.REL_OP);
        tokenTypeMap.put("notEquals", TokenType.REL_OP);
        tokenTypeMap.put("mathOp", TokenType.MATH_OP);
        tokenTypeMap.put("semiColon", TokenType.SEMICOLON);
        tokenTypeMap.put("number", TokenType.NUMBER);
        tokenTypeMap.put("float", TokenType.NUMBER);
        tokenTypeMap.put("id,keyword", TokenType.ID_KEYWORD);
        tokenTypeMap.put("colon", TokenType.COLON);
        tokenTypeMap.put("string2", TokenType.STRING);

        return tokenTypeMap;
    }
}

