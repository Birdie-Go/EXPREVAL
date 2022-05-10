package parser.expr;

import token.*;
import exceptions.*;

import java.util.*;

import javax.lang.model.util.ElementScanner14;

/**
 * Thriple expr.
 * A ? B : C
 */
public class ExprThriple {
    /** The condition : A of A ? B : C. */
    private MyBoolean condition;
    /** The choose 1 : B of A ? B : C. */
    private Token one;
    /** The choose 2: C of A ? B : C. */
    private Token two;

    /**
     * Constructor.
     * Condition:
     *   1. _condition must be boolean.
     *   2. choose must be boolean or decimal.
     * @param _condition The condition : A of A ? B : C.
     * @param _one The choose 1 : B of A ? B : C.
     * @param _two The choose 2: C of A ? B : C.
     * @throws ExpressionException violate 1 : TypeMismatchedException; else MissingOperandException
     */
    public ExprThriple(Token _condition, Token _one, Token _two) throws ExpressionException{
        if (!_condition.getType().equals("boolean"))
            throw new TypeMismatchedException();
        condition = new MyBoolean(_condition);
        
        if (_one.getType().equals("decimal"))
            one = new Decimal(_one);
        else if (_one.getType().equals("boolean"))
            one = new MyBoolean(_one);
        else
            throw new MissingOperandException();
        
        if (_two.getType().equals("decimal"))
            two = new Decimal(_two);
        else if (_two.getType().equals("boolean"))
            two = new MyBoolean(_two);
        else
            throw new MissingOperandException();
    }

    /**
     * calc the expr.
     * if choose then one else two.
     * @return the result : one or two
     * @throws ExpressionException see up.
     */
    public Token expr() throws ExpressionException {
        boolean choose = condition.getBoolean();

        if (choose) {
            if (one.getType().equals("decimal"))
                return new Decimal(one, false);
            else
                return new MyBoolean(one, false);
        }

        if (two.getType().equals("decimal"))
            return new Decimal(two, false);
        else
            return new MyBoolean(two, false);
    }
}
