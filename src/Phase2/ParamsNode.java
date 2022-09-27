package Phase2;

import java.util.*;

public class ParamsNode implements JottTree {
    List<JottTree> children = new ArrayList<>();
    public ParamsNode() {}
    public String convertToJott() {
        StringBuilder returnMe = new StringBuilder();
        for(JottTree child : children) {
            returnMe.append(child.convertToJott());
        }
        return returnMe.toString();
    }

    public String convertToJava() {return null;}
    public String convertToC() {return null;}
    public String convertToPython() {return null;}
    public boolean validateTree() {return true;}
    public String toString() {return null;}
}
