package parser.expr;

import token.*;
import exceptions.*;

import java.util.*;

import javax.swing.DebugGraphics;

/**
 * The expr is Unary operation, - or !.
 */
public class ExprUnary {
    /** The decimal need to be negative or be not. */
    private Token value;

    /**
     * Constructor.
     * @param _value a decimal or boolean need to be expr.
     * @throws TypeMismatchedException if is not decimal or boolean
     */
    public ExprUnary(Token _value) throws TypeMismatchedException {
        if (_value.getType().equals("decimal"))
            value = new Decimal(-_value.getDecimal(), false);
        else if (_value.getType().equals("boolean"))
            value = new MyBoolean(_value.getBoolean(), false);
        else
            throw new TypeMismatchedException();
    }

    /**
     * get -value or !boolean.
     * @return decimal->-decimal ; true->false; false->true;
     */
    public Token expr() {
        return value;
    }
}
