package scanner.dfa;

import token.*;
import java.util.*;

/**
 * unary dfa terminal point
 */
public class DFAUnary extends DFAPoint {
    /**
     * Empty constructor.
     */
    DFAUnary() {
        finish = true;
        type = new String("unary");
        edges = new HashMap <Character, Integer> ();
    }

    /**
     * copy constructor.
     * @param _copy the copy.
     */
    DFAUnary(DFAPoint _copy) {
        finish = _copy.finish;
        type = new String(_copy.type);
        edges = new HashMap <Character, Integer>(_copy.edges);
    }

    /**
     * Return a Unary token.
     * @param name token value
     * @return a Unary token.
     */
    public Unary getToken(String name) {
        return new Unary(name);
    }
}
