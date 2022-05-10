package scanner.dfa;

import token.*;
import java.util.*;

/**
 * parenthesis dfa terminal point
 */
public class DFAParenthesis extends DFAPoint {
    /**
     * Empty constructor.
     */
    DFAParenthesis() {
        finish = true;
        type = new String("parenthesis");
        edges = new HashMap <Character, Integer> ();
    }

    /**
     * copy constructor.
     * @param _copy the copy.
     */
    DFAParenthesis(DFAPoint _copy) {
        finish = _copy.finish;
        type = new String(_copy.type);
        edges = new HashMap <Character, Integer>(_copy.edges);
    }

    /**
     * Return a Parenthesis token.
     * @param name token value
     * @return a Parenthesis token.
     */
    public Parenthesis getToken(String name) {
        return new Parenthesis(name);
    }
}
