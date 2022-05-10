package token;

/**
 * Boolean Token.
 * Extends of Token.
 */
public class MyBoolean extends Token {
    /**
     * Boolean value of token.
     */
    private boolean booleanValue;

    /**
     * Constructor with string value.
     * @param _value string boolean value.
     */
    public MyBoolean(String _value) {
        priorityId = 0;
        type = new String("boolean");
        terminal = true;
        value = new String(_value);

        if (_value.equals("true"))
            booleanValue = true;
        else
            booleanValue = false;
    }

    /**
     * Constructor with boolean value and is or not terminal.
     * @param _value boolean value
     * @param _terminal is or not terminal
     */
    public MyBoolean(boolean _value, boolean _terminal) {
        priorityId = 0;
        type = new String("boolean");
        terminal = _terminal;
        value = new String(_value ? "true" : "false");

        booleanValue = _value;
    }

    /**
     * Copy constructor of another token.
     * @param _copy the copy.
     */
    public MyBoolean(Token _copy) {
        priorityId = _copy.priorityId;
        type = new String(_copy.type);
        terminal = _copy.terminal;
        value = new String(_copy.value);

        if (value.equals("true"))
            booleanValue = true;
        else
            booleanValue = false;
    }

    /**
     * Copy constructor of another token and is or not termianl.
     * @param _copy the token copy
     * @param _terminal is or not terminal
     */
    public MyBoolean(Token _copy, boolean _terminal) {
        priorityId = _copy.priorityId;
        type = new String(_copy.type);
        terminal = _terminal;
        value = new String(_copy.value);

        if (value.equals("true"))
            booleanValue = true;
        else
            booleanValue = false;
    }

    /**
     * Useless.
     */
    public double getDecimal() {
        return 0;
    }

    /**
     * return boolean value
     */
    public boolean getBoolean() {
        return booleanValue;
    }

    /**
     * useless
     */
    public String getInformation() {
        return value;
    }
}
