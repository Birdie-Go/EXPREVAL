package token;

/**
 * Unary token.
 * Information : with decimal or boolean
 */
public class Unary extends Token {

    /**
     * Constructor with string value and the Unary type.
     * @param _value string value, like '!'
     */
    public Unary(String _value) {
        type = new String("unary");
        terminal = true;
        if (_value.equals("-"))
            value = new String("--");
        else
            value = new String("!");
        priorityId = Token.priorityIdMap.get(value);
    }

    /**
     * Constructor with another copy.
     * @param _copy the copy.
     */
    public Unary(Token _copy) {
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
        switch (value) {
            case "!":
                return "boolean";
            default:
                return "decimal";
        }
    }
}
