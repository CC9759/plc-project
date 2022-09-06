public class IntNode implements JottTree {
    private final Token node;
    private final int value;
    
    public IntNode(Token node){
        this.node = node;
        value = Integer.parseInt(node.getToken());
    }

    public int getValue(){
        return value;
    }

    public String convertToJott(){
        return node.getToken();
    }

    public String convertToJava(){
        return null;
    };

    public String convertToC(){
        return null;
    };

    public String convertToPython(){
        return null;
    };

    public boolean validateTree(){
        return false;
    };
}
