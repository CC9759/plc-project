package DFA.Classes;

import java.util.ArrayList;


/**
 * State class used in several automata's
 */
public class State {
    public final String name;
    private final boolean accept;
    private final ArrayList<Transition> transitions = new ArrayList<>();

    public State(String name){
        this.name = name;
        accept = false;

    }
    public State(String name, boolean accept){
        this.name = name;
        this.accept = accept;
    }

    /**
     * adds a transition within the state. Again this works pretty well for DFA
     * but the current implementation would force us to create a custom state and
     * transition for each automaton, which is kinda cringe. Should probably use some
     * inheritance and polymorphism to fix this.
     * @param end the end state
     * @param cond a string of possible characters that will cause the transition
     */
    public void add_transition(State end, String cond) {
        //TODO checks that new transition doesn't override any old transition
        transitions.add(new Transition(end, cond));
    }

    /**
     * This uses the stored transitions to find the next state given an input.
     * This implementation once again works fine, but is pretty bad. A better
     * Implementation would be to have the Automata class have a hash map
     * of all states that stores a hash map of all transitions in that state.
     * This new implementation would forgo the multiple for loops, and also
     * allow for much easier access instead of all these nested classes.
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
