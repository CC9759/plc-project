public class DoubleNode implements JottTree{
    private final Token node;

    private final double value;


    public DoubleNode(Token node){
        this.node = node;
        this.value = Double.parseDouble(node.getToken());
    }

    public double getValue(){
        return this.value;
    }

    public String convertToJott(){
        return this.node.getToken();
    }

    public String convertToJava(){
        return null;
    }

    public String convertToC(){
        return null;
    }

    public String convertToPython(){
        return null;
    }

    public boolean validateTree(){
        return false;
    }







}
