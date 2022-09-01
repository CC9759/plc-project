import java.util.ArrayList;

public class Transition {
    private State start;
    private State end;
    private String conditions;


    public Transition(State start, State end, String conditions){
        this.start = start;
        this.end = end;
        this.conditions = conditions;


    }
    public void setCondition(String condition) {
        this.conditions = conditions;
    }

    public boolean checkcondition(Character input){

        for(int i = 0; i < conditions.length(); i++) {
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



