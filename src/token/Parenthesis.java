package token;

/**
 * Parenthesis token.
 * Information : left or right.
 */
public class Parenthesis extends Token {

    /**
     * Constructor with string value and the Parenthesis type.
     * @param _value string value, like '('
     */
    public Parenthesis(String _value) {
        priorityId = Token.priorityIdMap.get(_value);
        type = new String("parenthesis");
        terminal = true;
        value = new String(_value);
    }

    /**
     * Constructor with another copy.
     * @param _copy the copy.
     */
    public Parenthesis(Token _copy) {
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
     * left or right
     */
    public String getInformation() {
        switch (value) {
            case "(":
                return "left";
            default:
                return "right";
        }
    }
}
