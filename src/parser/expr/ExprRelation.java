package parser.expr;

import token.*;
import exceptions.*;

import java.util.*;

import javax.lang.model.util.ElementScanner14;

/**
 * the expr is a relation, like 1 > 2.
 */
public class ExprRelation {
    /** * The operator : > less = lesseq >= noteq and | */
    private Token opertor;
    /** * The left of the expr. */
    private Token left;
    /** * The right of the expr. */
    private Token right;

    /**
     * Constructor.
     * Condition:
     *   1. left and right must be the same.
     *   2. left and right must be decimal or boolean.
     * @param _opertor The operator : > less = lesseq >= noteq and |
     * @param _left The left of the expr.
     * @param _right The right of the expr.
     * @throws TypeMismatchedException left != right or not decimal, boolean
     */
    public ExprRelation(Token _opertor, Token _left, Token _right) throws TypeMismatchedException {
        if (_left.getType().equals("decimal") &&
            _right.getType().equals("decimal") &&
            _opertor.getInformation().equals("decimal")) {
            left = new Decimal(_left);
            right = new Decimal(_right);
        } else if (_left.getType().equals("boolean") &&
                   _right.getType().equals("boolean") &&
                   _opertor.getInformation().equals("boolean")) {
            left = new MyBoolean(_left);
            right = new MyBoolean(_right);
        } else
            throw new TypeMismatchedException();

        opertor = new Relation(_opertor);
    }

    /**
     * Boolean expr.
     * The operator is and or |.
     * Condition:
     *   1. left and right must be boolean. 
     * @return the result.
     * @throws ExpressionException violate contidion 1.
     */
    private Token exprBoolean() throws ExpressionException {
        switch (opertor.getValue()) {
            case "&":
                return new MyBoolean(left.getBoolean() && right.getBoolean(), false);
            case "|":
                return new MyBoolean(left.getBoolean() || right.getBoolean(), false);
        }
        throw new MissingOperatorException();
    }

    /**
     * Decimal expr.
     * The operator is > less = lesseq >= noteq.
     * Condition:
     *   1. left and right must be decimal.
     * @return the result.
     * @throws ExpressionException violate condition 1.
     */
    private Token exprDecimal() throws ExpressionException {
        double leftDecimal = left.getDecimal();
        double rightDecimal = right.getDecimal();
        switch (opertor.getValue()) {
            case "<":
                return new MyBoolean(leftDecimal < rightDecimal, false);
            case "<=":
                return new MyBoolean(leftDecimal <= rightDecimal, false);
            case ">":
                return new MyBoolean(leftDecimal > rightDecimal, false);
            case ">=":
                return new MyBoolean(leftDecimal >= rightDecimal, false);
            case "=":
                return new MyBoolean(leftDecimal == rightDecimal, false);
            case "<>":
                return new MyBoolean(leftDecimal != rightDecimal, false);
        }
        throw new MissingOperatorException();
    }


    /**
     * Calc the expr.
     * Choose is and | or less lesseq > >= = noteq.
     * @return the result
     * @throws ExpressionException error happen above
     */
    public Token expr() throws ExpressionException {
        if (opertor.getInformation().equals("boolean"))
            return exprBoolean();
        return exprDecimal();
    }
}
