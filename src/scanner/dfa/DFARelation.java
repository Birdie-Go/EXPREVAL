package scanner.dfa;

import token.*;
import java.util.*;

/**
 * relation dfa terminal point
 */
public class DFARelation extends DFAPoint {
    /**
     * Empty constructor.
     */
    DFARelation() {
        finish = true;
        type = new String("relation");
        edges = new HashMap <Character, Integer> ();
    }

    /**
     * copy constructor.
     * @param _copy the copy.
     */
    DFARelation(DFAPoint _copy) {
        finish = _copy.finish;
        type = new String(_copy.type);
        edges = new HashMap <Character, Integer>(_copy.edges);
    }

    /**
     * Return a Relation token.
     * @param name token value
     * @return a Relation token.
     */
    public Relation getToken(String name) {
        return new Relation(name);
    }
}
