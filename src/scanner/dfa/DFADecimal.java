package scanner.dfa;

import token.*;
import java.util.*;

/**
 * decimal dfa terminal point
 */
public class DFADecimal extends DFAPoint {
    /**
     * Empty constructor.
     */
    DFADecimal() {
        finish = true;
        type = new String("decimal");
        edges = new HashMap <Character, Integer> ();
    }

    /**
     * copy constructor.
     * @param _copy the copy.
     */
    DFADecimal(DFAPoint _copy) {
        finish = _copy.finish;
        type = new String(_copy.type);
        edges = new HashMap <Character, Integer>(_copy.edges);
    }

    /**
     * Return a Decimal token.
     * @param name token value
     * @return a Decimal token.
     */
    public Decimal getToken(String name) {
        return new Decimal(name);
    }
}
