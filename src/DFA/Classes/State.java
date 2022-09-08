package DFA.Classes;

import java.util.ArrayList;


/**
 * State class used in several automata's
 */
public class State {
    public final String name;
    private final boolean accept;
    private final ArrayList<Transition> transitions = new ArrayList<>();

    public State(String name, boolean accept){
        this.name = name;
        this.accept = accept;
    }

    /**
     * @param end the end state
     * @param cond a string of possible characters that will cause the transition
     */
    public void add_transition(State end, String cond) {
        transitions.add(new Transition(end, cond));
    }

    /**
     *
     *
     * @param input Character used to check transition
     * @return Returns State if transition successful, otherwise returns null
     */
    public State next_state(Character input){
        for (Transition temp : transitions) {
            if (temp.checkCondition(input)) {
                return temp.getEnd();
            }
        }
        return null;
    }

    public boolean isAccept() {
        return accept;
    }
}
