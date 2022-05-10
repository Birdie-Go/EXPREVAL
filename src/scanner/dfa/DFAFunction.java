package scanner.dfa;

import token.*;
import java.util.*;

/**
 * function dfa terminal point
 */
public class DFAFunction extends DFAPoint {
    /**
     * Empty constructor.
     */
    DFAFunction() {
        finish = true;
        type = new String("function");
        edges = new HashMap <Character, Integer> ();
    }

    /**
     * copy constructor.
     * @param _copy the copy.
     */
    DFAFunction(DFAPoint _copy) {
        finish = _copy.finish;
        type = new String(_copy.type);
        edges = new HashMap <Character, Integer>(_copy.edges);
    }

    /**
     * Return a Function token.
     * @param name token value
     * @return a Function token.
     */
    public Function getToken(String name) {
        return new Function(name);
    }
}
