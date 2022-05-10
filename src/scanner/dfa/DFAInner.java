package scanner.dfa;

import token.*;
import java.util.*;

/**
 * inner point on dfa
 */
public class DFAInner extends DFAPoint {
    /**
     * Empty constructor.
     */
    DFAInner() {
        finish = false;
        type = new String("inner");
        edges = new HashMap <Character, Integer> ();
    }

    /**
     * copy constructor.
     * @param _copy the copy.
     */
    DFAInner(DFAPoint _copy) {
        finish = _copy.finish;
        type = new String(_copy.type);
        edges = new HashMap <Character, Integer>(_copy.edges);
    }

    /**
     * In fact, will not happen.
     * @param name meaningless
     * @return meaningless.
     */
    public Token getToken(String name) {
        return new Decimal(name);
    }
}
