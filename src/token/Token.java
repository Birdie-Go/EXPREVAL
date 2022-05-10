package token;

import java.util.HashMap;

/**
 * Token.
 */
public abstract class Token {
    /** The priority of the token. */
    protected int priorityId;
    /** The type of token, like decimal */
    protected String type;
    /** Is or not terminal */
    protected boolean terminal;
    /** The token self, is a Sring, like 'sin' */
    protected String value;
    /**
     * the priority map used with hashmap.
     * The priority classes:
     * decimal, boolean, (), func, +-, /*, ^, comparation, !, and, |, ?, :, comma, $
     */
    protected static HashMap <String, Integer> priorityIdMap;

    /**
     * return the value
     * @return value, a string
     */
    public String getValue() {
        return value;
    }

    /**
     * return priority
     * @return the priority, an int
     */
    public int getPriority() {
        return priorityId;
    }

    /**
     * return the token type
     * @return the type, a string
     */
    public String getType() {
        return type;
    }

    /**
     * return is or not terminal
     * @return true or false
     */
    public boolean isTermial() {
        return terminal;
    }

    /**
     * set the token a non-terminal
     */
    public void setNonTerminal() {
        terminal = false;
    }

    /**
     * if is decimal, return a decimal value
     * @return a double value
     */
    public abstract double getDecimal();

    /**
     * if is boolean, return boolean value
     * @return a boolean value
     */
    public abstract boolean getBoolean();

    /**
     * return some additional information
     * @return some String additional information
     */
    public abstract String getInformation();

    /**
     * build the priority hash map.
     */
    static {
        priorityIdMap = new HashMap <String, Integer>();
        priorityIdMap.put("(", 2);
        priorityIdMap.put(")", 3);
        priorityIdMap.put("max", 4);
        priorityIdMap.put("min", 4);
        priorityIdMap.put("sin", 4);
        priorityIdMap.put("cos", 4);
        priorityIdMap.put("--", 5);
        priorityIdMap.put("^", 6);
        priorityIdMap.put("*", 7);
        priorityIdMap.put("/", 7);
        priorityIdMap.put("+", 8);
        priorityIdMap.put("-", 8);
        priorityIdMap.put("<", 9);
        priorityIdMap.put("<=", 9);
        priorityIdMap.put(">",9);
        priorityIdMap.put(">=", 9);
        priorityIdMap.put("=", 9);
        priorityIdMap.put("<>", 9);
        priorityIdMap.put("!", 10);
        priorityIdMap.put("&", 11);
        priorityIdMap.put("|", 12);
        priorityIdMap.put("?", 13);
        priorityIdMap.put(":", 14);
        priorityIdMap.put(",", 15);
        priorityIdMap.put("$", 16);
    }
}
