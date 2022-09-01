import java.util.ArrayList;


/**
 * State class used in several automata's
 */
public class State {
    public String name;
    private boolean accept;
    private ArrayList<Transition> transitions = new ArrayList<>();

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
     * transition for each automata, which is kinda cringe. Should probably use some
     * inheritance and polymorphism to fix this.
     * @param end
     * @param cond
     * @return
     */
    public boolean add_transition(State end, String cond) {
        //checks that new transition doesn't override any old transition
//        for (int i = 0; i < transitions.size(); i++) {
//            Transition temp = transitions.get(i);
//            if (temp.checkcondition(cond)) {
//                return false;
//            }
//        }
        transitions.add(new Transition(this, end, cond));
        return true;
    }

    /**
     * This uses the stored transitions to find the next state given an input.
     * This implementation once again works fine, but is pretty bad. A better
     * Implementation would be to have the Automata class have a hash map
     * of all states that stores a hash map of all transitions in that state.
     * This new implementation would forgo the multiple for loops, and also
     * allow for much easier access instead of all these nested classes.
     *
     * @param input
     * @return
     */
    public State next_state(Character input){
        for(int i=0; i < transitions.size();i++){
            Transition temp = transitions.get(i);
            if(temp.checkcondition(input)){
                return temp.getEnd();
            }
        }
        return null;
    }

    public boolean isAccept() {
        return accept;
    }
}
