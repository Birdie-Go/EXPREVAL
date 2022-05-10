package scanner.dfa;

import token.*;
import java.util.*;

/**
 * operator dfa terminal point
 */
public class DFAOperator extends DFAPoint {
    /**
     * Empty constructor.
     */
    DFAOperator() {
        finish = true;
        type = new String("osperator");
        edges = new HashMap <Character, Integer> ();
    }

    /**
     * copy constructor.
     * @param _copy the copy.
     */
    DFAOperator(DFAPoint _copy) {
        finish = _copy.finish;
        type = new String(_copy.type);
        edges = new HashMap <Character, Integer>(_copy.edges);
    }

    /**
     * Return a Operator token.
     * @param name token value
     * @return a Operator token.
     */
    public Operator getToken(String name) {
        return new Operator(name);
    }
}
