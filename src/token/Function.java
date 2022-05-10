package token;

/**
 * Function token.
 * Information : unary or not.
 */
public class Function extends Token {

    /**
     * Constructor with string value and the Function type.
     * @param _value string value, like 'sin'
     */
    public Function(String _value) {
        priorityId = Token.priorityIdMap.get(_value);
        type = new String("function");
        terminal = true;
        value = new String(_value);
    }

    /**
     * Constructor with another copy.
     * @param _copy the copy.
     */
    public Function(Token _copy) {
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
     * unary or not.
     */
    public String getInformation() {
        switch (value) {
            case "sin":
                return "unary";
            case "cos":
                return "unary";
            default:
                return "variabl";
        }
    }
}
