public class ConstantNode implements JottTree {
    private Token token;
    private TokenType type;
    private String value;

    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    public ConstantNode(Token inputToken) {
        this.token = inputToken;
        this.type = inputToken.getTokenType();
        this.value = token.getToken();
    }

    public String convertToJott() {
        return value;
    }

    /**
     * Will output a string of this tree in Java
     * @return a string representing the Java code of this tree
     */
    public String convertToJava() {
        return value;
    }

    /**
     * Will output a string of this tree in C
     * @return a string representing the C code of this tree
     */
    public String convertToC() {
        return value;
    }

    /**
     * Will output a string of this tree in Python
     * @return a string representing the Python code of this tree
     */
    public String convertToPython() {
        return value;
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
