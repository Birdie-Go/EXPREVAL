package parser.expr;

import token.*;
import exceptions.*;

import java.util.*;

/**
 * The expr is a constant : decimal or boolean
 */
public class ExprConstant {
    /** The constant value. */
    private Token value;

    /**
     * Construct a constant expr.
     * @param _value decimal or boolean expr
     * @throws MissingOperandException if is not decimal or boolean, miss operand.
     */
    public ExprConstant(Token _value) throws MissingOperandException {
        switch (_value.getType()) {
            case "decimal":
                value = new Decimal(_value, false);
                break;
            case "boolean":
                value = new MyBoolean(_value, false);
                break;
            default:
                throw new MissingOperandException();
        }
    }

    /**
     * Calc the expr.
     * @return the decimal or boolean token, is a non-terminal
     */
    public Token expr() {
        return value;
    }
}
