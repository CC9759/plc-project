public class ConstantNode implements JottTree {

    enum ConstantTypes {
        BOOLEAN, DOUBLE, INT, STRING        
    }

    private Token token;
    private ConstantType type;
    private String value;

    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    public ConstantNode(Token inputToken) {
        this.token = inputToken;
        this.value = this.token.getToken();

        //Is this a boolean?
        if(this.value.equals("True") ||
           this.value.euqlas("False")) {
            this.type = BOOLEAN;
        }

        //If not, is this an Int/Double?
        else if(this.token.getTokenType() == NUMBER) {
            if(this.value.indexOf(".") != -1) {
                this.type = DOUBLE;
            }
            else {
                this.type = INT;
            }
        }

        //This should be a String
        else {
            this.type = STRING;
        }
    }

    public String convertToJott() {
        return this.value;
    }

    /**
     * Will output a string of this tree in Java
     * @return a string representing the Java code of this tree
     */
    public String convertToJava() {
        return this.value;
    }

    /**
     * Will output a string of this tree in C
     * @return a string representing the C code of this tree
     */
    public String convertToC() {
        return this.value;
    }

    /**
     * Will output a string of this tree in Python
     * @return a string representing the Python code of this tree
     */
    public String convertToPython() {
        return this.value;
    }

    /**
     * This will validate that the tree follows the semantic rules of Jott
     * Errors validating will be reported to System.err
     * @return true if valid Jott code; false otherwise
     */
    public boolean validateTree() {
        return true;
    }

    public String toString() {
        return this.value;
    }

}