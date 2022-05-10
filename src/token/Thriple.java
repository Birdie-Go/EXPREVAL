package token;

/**
 * Thriple token.
 * In fact, it is useless.
 * But it makes the logic clearly.
 */
public class Thriple extends Token {

    /**
     * Constructor with string value and the Thriple type.
     * @param _value string value, like '?'
     */
    public Thriple(String _value) {
        priorityId = Token.priorityIdMap.get(_value);
        type = new String("thriple");
        terminal = true;
        value = new String(_value);
    }

    /**
     * Constructor with another copy.
     * @param _copy the copy.
     */
    public Thriple(Token _copy) {
        priorityId = _copy.priorityId;
        type = new String(_copy.type);
        terminal = _copy.terminal;
        value = _copy.value;
    }

    /**
     * Useless.
     */
    public double getDecimal() {
        return 0;
    }

    /**
     * useless
     */
    public boolean getBoolean() {
        return false;
    }

    /**
     * useless
     */
    public String getInformation() {
        return value;
    }
}
