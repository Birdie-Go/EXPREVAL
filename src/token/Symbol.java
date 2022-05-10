package token;

/**
 * Comma token.
 * In fact, it is useless.
 * But it makes the logic clearly.
 */
public class Symbol extends Token {

    /**
     * Constructor with string value and the Comma type.
     * @param _value ','
     */
    public Symbol(String _value) {
        priorityId = Token.priorityIdMap.get(_value);
        switch (_value) {
            case "$":
                type = new String("dollar");
                break;
            case ",":
                type = new String("comma");
                break;
            default:
                type = new String("unknown");
        }
        terminal = true;
        value = new String(_value);
    }

    /**
     * Constructor with another copy.
     * @param _copy the copy.
     */
    public Symbol(Token _copy) {
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
