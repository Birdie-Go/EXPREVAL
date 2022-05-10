package token;

/**
 * Relation token.
 * Information : compare with decimal or boolean.
 */
public class Relation extends Token {

    /**
     * Constructor with string value and the Relation type.
     * @param _value string value, like '='
     */
    public Relation(String _value) {
        priorityId = Token.priorityIdMap.get(_value);
        type = new String("relation");
        terminal = true;
        value = new String(_value);
    }

    /**
     * Constructor with another copy.
     * @param _copy the copy.
     */
    public Relation(Token _copy) {
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
     * decimal relation or boolean relation
     */
    public String getInformation() {
        switch (value) {
            case "<":
            case "<=":
            case ">":
            case ">=":
            case "=":
            case "<>":
                return "decimal";
            default:
                return "boolean";
        }
    }
}
