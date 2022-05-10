package scanner.dfa;

import java.util.*;

import token.*;

/**
 * The point in scanner DFA.
 */
public abstract class DFAPoint {
    /**
     * Is the finish state or not.
     */
    protected boolean finish;
    /**
     * The finsih type, like decimal.
     */
    protected String type;
    /**
     * The out edge from this state.
     * It save with a hashmap, key is the charactor to go.
     * Value is the target to go.
     */
    protected HashMap <Character, Integer> edges;

    /**
     * Empty constructor.
     */
    DFAPoint() {
        finish = false;
        type = new String();
        edges = new HashMap <Character, Integer> ();
    }

    /**
     * Constructor with a type, it means it is a finish state.
     * @param _type the token type of this finish state
     */
    DFAPoint(String _type) {
        finish = true;
        type = new String(_type);
        edges = new HashMap <Character, Integer> ();
    }

    /**
     * copy constructor.
     * @param _copy the copy.
     */
    DFAPoint(DFAPoint _copy) {
        finish = _copy.finish;
        type = new String(_copy.type);
        edges = new HashMap <Character, Integer>(_copy.edges);
    }

    /**
     * return the token on the terminal dfa point
     * @param name the token's content
     * @return a token
     */
    public abstract Token getToken(String name);

    /**
     * Check is the finish state or not.
     * @return is or not.
     */
    public boolean isFinish() {
        return finish;
    }

    /**
     * Get the state type, like decimal.
     * @return the state type.
     */
    public String getType() {
        return type;
    }

    /**
     * Get the out edge of the state.
     * @return the edges in hashmap.
     */
    public HashMap <Character, Integer> getEdges() {
        return edges;
    }

    /**
     * Add an out edge, needs charactor can go, and the target.
     * @param weight the charactor can go
     * @param to the go to target.
     */
    public void addEdge(char weight, int to) {
        edges.put(weight, to);
    }
}
