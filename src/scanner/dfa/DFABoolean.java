package scanner.dfa;

import token.*;
import java.util.*;

/**
 * Boolean dfa point
 */
public class DFABoolean extends DFAPoint {
    /**
     * Empty constructor.
     */
    DFABoolean() {
        finish = true;
        type = new String("boolean");
        edges = new HashMap <Character, Integer> ();
    }

    /**
     * copy constructor.
     * @param _copy the copy.
     */
    DFABoolean(DFAPoint _copy) {
        finish = _copy.finish;
        type = new String(_copy.type);
        edges = new HashMap <Character, Integer>(_copy.edges);
    }

    /**
     * Return a boolean token.
     * @param name token value
     * @return a boolean token.
     */
    public MyBoolean getToken(String name) {
        return new MyBoolean(name);
    }
}
