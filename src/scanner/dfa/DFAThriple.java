package scanner.dfa;

import token.*;
import java.util.*;

/**
 * thriple dfa terminal point
 */
public class DFAThriple extends DFAPoint {
    /**
     * Empty constructor.
     */
    DFAThriple() {
        finish = true;
        type = new String("thriple");
        edges = new HashMap <Character, Integer> ();
    }

    /**
     * copy constructor.
     * @param _copy the copy.
     */
    DFAThriple(DFAPoint _copy) {
        finish = _copy.finish;
        type = new String(_copy.type);
        edges = new HashMap <Character, Integer>(_copy.edges);
    }

    /**
     * Return a Thriple token.
     * @param name token value
     * @return a Thriple token.
     */
    public Thriple getToken(String name) {
        return new Thriple(name);
    }
}
