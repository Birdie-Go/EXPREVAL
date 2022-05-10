package parser.expr;

import token.*;
import exceptions.*;

import java.util.*;

/**
 * The expr is an operator link, like 1 + 2.
 */
public class ExprOpreator {
    /** * The operator : + - * / and exp */
    private Token opertor;
    /** * left decimal of the expr */
    private Decimal left;
    /** * right decimal of the expr */
    private Decimal right;

    /**
     * Constructor.
     * Left and right must be decimal.
     * @param _opertor The operator : + - * / and exp
     * @param _left left decimal of the expr
     * @param _right right decimal of the expr
     * @throws TypeMismatchedException left or right is not a decimal
     */
    public ExprOpreator(Token _opertor, Token _left, Token _right) throws TypeMismatchedException {
        if (!_left.getType().equals("decimal"))
            throw new TypeMismatchedException();
        if (!_right.getType().equals("decimal"))
            throw new TypeMismatchedException();
        opertor = new Operator(_opertor);
        left = new Decimal(_left);
        right = new Decimal(_right);
    }

    /**
     * calc the expr.
     * @return a+b, a-b, a*b, a/b, or a exp b ; a non-terminal
     * @throws ExpressionException the operator is not + - * / exp
     */
    public Token expr() throws ExpressionException {
        double leftDecimal = left.getDecimal();
        double rightDecimal = right.getDecimal();
        switch (opertor.getValue()) {
            case "+":
                return new Decimal(leftDecimal + rightDecimal, false);
            case "-":
                return new Decimal(leftDecimal - rightDecimal, false);
            case "*":
                return new Decimal(leftDecimal * rightDecimal, false);
            case "/":
                if (rightDecimal == 0)
                    throw new DividedByZeroException();
                return new Decimal(leftDecimal / rightDecimal, false);
            case "^":
                return new Decimal(Math.pow(leftDecimal, rightDecimal), false);
        }
        throw new MissingOperatorException();
    }
}
