package DFA.Classes;

import java.util.ArrayList;
import java.util.HashMap;


/** the DFS class has simple nodes that
 * are either accept, or not, and has a clearly defined branch for each outcome.
 * because of this, checks for invalid placements must occur before running or else
 * it is not a DFS
 *
 */
public class DFA {
    private final State start_state;
    private final HashMap<String,State> states = new HashMap<>();

    public ArrayList<String> Tokens;


    public DFA(){
        this.start_state = new State("start");
        states.put("start",start_state);
    }


    /**
     * executes DFS given a string
     *
     * @param input the string that will be executed
     *
     */
    public ArrayList<String> execute(String input){
        State current_state = start_state;
        Tokens = new ArrayList<>();
        StringBuilder currentString = new StringBuilder();
        for(int i = 0; i < input.length(); i++){
            State previous_state = current_state;
            Character value = input.charAt(i);
            current_state = current_state.next_state(value);
            if(current_state == null){
                if(previous_state.isAccept()) {
                    System.out.println(previous_state.name);
                    Tokens.add(currentString.toString());
                    i -= 1;
                }
                current_state = start_state;
                currentString = new StringBuilder();

            }
            else {
                currentString.append(value);
            }

        }
        return Tokens;
    }

    public void addState(String name, boolean accept){
        states.put(name,new State(name,accept));
    }

    public void addTransition(String state1, String state2, String cond){
        State start = states.get(state1);
        if(start == null){
            return;
        }
        State end = states.get(state2);
        if(end == null){
            return;
        }
        start.add_transition(end,cond);
    }





}
