import java.util.ArrayList;

public class tester {
    private static String digits = "0123456789";
    private static String mathOps = "/+-*";
    private static String relOps = "<>";
    public static void main(String[] args){

        DFA scannerDFA = new DFA();
        scannerDFA.addState("comma", true);
        scannerDFA.addState("assign", true);
        scannerDFA.addState("relOp1", true);
        scannerDFA.addState("relOp2", true);
        scannerDFA.addState("mathOp", true);
        scannerDFA.addState("number", true);
        scannerDFA.addState("decimal",false);
        scannerDFA.addState("float",true);

        scannerDFA.addTransition("start","comma",",");
        scannerDFA.addTransition("start","assign","=");
        scannerDFA.addTransition("start","relOp2",relOps);
        scannerDFA.addTransition("start","number",digits);
        scannerDFA.addTransition("start","mathOp",mathOps);
        scannerDFA.addTransition("start","decimal",".");
        scannerDFA.addTransition("assign", "relOp1", "=");
        scannerDFA.addTransition("decimal", "float", digits);

        ArrayList<String> results = scannerDFA.execute("3+4==7\n");

        for(int i = 0; i < results.size(); i++){
            System.out.println(results.get(i));
        }











    }
}
