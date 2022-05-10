package scanner.dfa;

import token.*;
import java.util.*;

/**
 * comma dfa terminal point
 */
public class DFAComma extends DFAPoint {
    /**
     * Empty constructor.
     */
    DFAComma() {
        finish = true;
        type = new String("comma");
        edges = new HashMap <Character, Integer> ();
    }

    /**
     * copy constructor.
     * @param _copy the copy.
     */
    DFAComma(DFAPoint _copy) {
        finish = _copy.finish;
        type = new String(_copy.type);
        edges = new HashMap <Character, Integer>(_copy.edges);
    }

    /**
     * Return a Comma token.
     * @param name token value
     * @return a Comma token.
     */
    public Symbol getToken(String name) {
        return new Symbol(name);
    }
}
