package DFAClasses;

public class TokenTree {
    private static final String digits = "0123456789";
    private static final String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String mathOps = "/+-*";
    private static final String relOps = "<>";

    public static DFA createTokenTree(){

        DFA newTokenTree =  new DFA();

        newTokenTree.addState("comment", false);

        newTokenTree.addState("comma", true);
        newTokenTree.addState("rbracket", true);
        newTokenTree.addState("lbracket", true);
        newTokenTree.addState("rbrace", true);
        newTokenTree.addState("lbrace", true);

        newTokenTree.addState("assign", true);
        newTokenTree.addState("relOp1", true);
        newTokenTree.addState("relOp2", true);

        newTokenTree.addState("mathOp", true);
        newTokenTree.addState("semiColon", true);


        newTokenTree.addState("number", true);
        newTokenTree.addState("decimal",false);
        newTokenTree.addState("float",true);

        newTokenTree.addState("id,keyword", true);
        newTokenTree.addState("colon", true);

        newTokenTree.addState("!", false);
        newTokenTree.addState("notEquals", true);

        newTokenTree.addState("string1", false);
        newTokenTree.addState("string2", true);


        newTokenTree.addTransition("start","comment","#");
        newTokenTree.addTransition("comment","comment","non-newline");
        newTokenTree.addTransition("comment","start","\n");

        newTokenTree.addTransition("start","comma",",");
        newTokenTree.addTransition("start","rbracket","]");
        newTokenTree.addTransition("start","lbracket","[");
        newTokenTree.addTransition("start","rbrace","}");
        newTokenTree.addTransition("start","lbrace","{");

        newTokenTree.addTransition("start","assign","=");
        newTokenTree.addTransition("assign", "relOp1", "=");
        newTokenTree.addTransition("start","relOp2",relOps);
        newTokenTree.addTransition("relOp2", "relOp1", "=");

        newTokenTree.addTransition("start","mathOp",mathOps);
        newTokenTree.addTransition("start","semiColon",";");


        newTokenTree.addTransition("start","decimal",".");
        newTokenTree.addTransition("decimal", "float", digits);

        newTokenTree.addTransition("start","number",digits);
        newTokenTree.addTransition("number","number",digits);

        newTokenTree.addTransition("number","float",".");
        newTokenTree.addTransition("float","float",digits);


        newTokenTree.addTransition("start","id,keyword",letters);
        newTokenTree.addTransition("id,keyword","id,keyword",letters + digits);

        newTokenTree.addTransition("start","colon",":");

        newTokenTree.addTransition("start","!","!");
        newTokenTree.addTransition("!","notEquals","=");

        newTokenTree.addTransition("start","string1","\"");
        newTokenTree.addTransition("string1","string1",letters+digits+" ");
        newTokenTree.addTransition("string1","string2","\"");

        return newTokenTree;
    }
}
