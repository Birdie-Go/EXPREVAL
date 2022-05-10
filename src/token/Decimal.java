package token;

/**
 * Decimal Token.
 * Extends of Token.
 */
public class Decimal extends Token {
    /** The double value of the decimal.*/
    private double decimalValue;

    /**
     * Constructor with a string value.
     * @param _value string decimal value
     */
    public Decimal(String _value) {
        priorityId = 1;
        type = new String("decimal");
        terminal = true;
        value = new String(_value);

        decimalValue = Double.parseDouble(_value);
    }

    /**
     * Constructor with double value and is or not terminal
     * @param _value double value
     * @param _terminal is or not terminal
     */
    public Decimal(double _value, boolean _terminal) {
        priorityId = 1;
        type = new String("decimal");
        terminal = _terminal;
        value = new String(String.valueOf(_value));

        decimalValue = Double.parseDouble(value);
    }

    /**
     * Copy constructor with another token.
     * @param _copy copy of the token
     */
    public Decimal(Token _copy) {
        priorityId = _copy.priorityId;
        type = new String(_copy.type);
        terminal = _copy.terminal;
        value = new String(_copy.value);

        decimalValue = Double.parseDouble(value);
    }

    /**
     * Copy constructor with a copy and is or not termianl.
     * @param _copy the copy
     * @param _terminal is or not terminal.
     */
    public Decimal(Token _copy, boolean _terminal) {
        priorityId = _copy.priorityId;
        type = new String(_copy.type);
        terminal = _terminal;
        value = new String(_copy.value);

        decimalValue = Double.parseDouble(value);
    }

    /**
     * get the double value
     */
    public double getDecimal() {
        return decimalValue;
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
