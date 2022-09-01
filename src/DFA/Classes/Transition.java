package DFA.Classes;

public class Transition {
    private final State end;
    private final String conditions;


    public Transition(State end, String conditions){
        this.end = end;
        this.conditions = conditions;


    }

    public boolean checkCondition(Character input){
        if (conditions.equals("non-newline")) {
            return !input.equals('\n');
        }
        for (int i = 0; i < conditions.length(); i++) {
            if (input.equals(conditions.charAt(i))) {
                return true;
            }

        }
        return false;
    }

    public State getEnd(){
        return end;
    }

}



