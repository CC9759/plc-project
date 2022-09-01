package DFA.Classes;

import java.util.ArrayList;

public class Tester {
    private static String digits = "0123456789";
    private static String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String mathOps = "/+-*";
    private static String relOps = "<>";


    public static void main(String[] args){

        DFA scannerDFA = new DFA();
        scannerDFA.addState("comment", false);

        scannerDFA.addState("comma", true);
        scannerDFA.addState("rbracket", true);
        scannerDFA.addState("lbracket", true);
        scannerDFA.addState("rbrace", true);
        scannerDFA.addState("lbrace", true);

        scannerDFA.addState("assign", true);
        scannerDFA.addState("relOp1", true);
        scannerDFA.addState("relOp2", true);

        scannerDFA.addState("mathOp", true);
        scannerDFA.addState("semiColon", true);


        scannerDFA.addState("number", true);
        scannerDFA.addState("decimal",false);
        scannerDFA.addState("float",true);

        scannerDFA.addState("id,keyword", true);
        scannerDFA.addState("colon", true);

        scannerDFA.addState("!", false);
        scannerDFA.addState("notEquals", true);

        scannerDFA.addState("string1", false);
        scannerDFA.addState("string2", true);


        scannerDFA.addTransition("start","comma",",");
        scannerDFA.addTransition("start","rbracket","]");
        scannerDFA.addTransition("start","lbracket","[");
        scannerDFA.addTransition("start","rbrace","}");
        scannerDFA.addTransition("start","lbrace","{");

        scannerDFA.addTransition("start","assign","=");
        scannerDFA.addTransition("assign", "relOp1", "=");
        scannerDFA.addTransition("start","relOp2",relOps);
        scannerDFA.addTransition("relOp2", "relOp1", "=");

        scannerDFA.addTransition("start","mathOp",mathOps);
        scannerDFA.addTransition("start","semiColon",";");


        scannerDFA.addTransition("start","decimal",".");
        scannerDFA.addTransition("decimal", "float", digits);

        scannerDFA.addTransition("start","number",digits);
        scannerDFA.addTransition("number","number",digits);

        scannerDFA.addTransition("number","float",".");
        scannerDFA.addTransition("float","float",digits);


        scannerDFA.addTransition("start","id,keyword",letters);
        scannerDFA.addTransition("id,keyword","id,keyword",letters + digits);

        scannerDFA.addTransition("start","colon",":");

        scannerDFA.addTransition("start","!","!");
        scannerDFA.addTransition("!","notEquals","=");

        scannerDFA.addTransition("start","string1","\"");
        scannerDFA.addTransition("string1","string1",letters+digits+" ");
        scannerDFA.addTransition("string1","string2","\"");


        ArrayList<String> results = scannerDFA.execute("3+4==7foo1bar\nprint[\"hello\"]\n");

        for(int i = 0; i < results.size(); i++){
            System.out.println(results.get(i));
        }

    }
}
