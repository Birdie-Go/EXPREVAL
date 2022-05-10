package parser.expr;

import token.*;
import exceptions.*;

import java.util.*;

/**
 * The expr is a function.
 */
public class ExprFunction {
    /** The function name : sin, cos, min, max */
    private Token func;
    /** The arguments in the function. */
    private ArrayList <Token> args;
    /** The number of the arguments (inclue comma). */
    private int length;

    /**
     * Empyt construction.
     */
    public ExprFunction() {
        args = new ArrayList <Token>();
        length = 0;
    }

    /**
     * Construction.
     * @param _func The function name : sin, cos, min, max
     * @param _args The arguments in the function (inclue comma).
     */
    public ExprFunction(Token _func, ArrayList <Token> _args) {
        func = new Function(_func);
        args = new ArrayList <Token>(_args);
        length = args.size();
    }

    /**
     * To check if args list is approved
     * @throws ExpressionException look the switch case
     */
    private void checkArgs() throws ExpressionException {
        for (int i = 0; i < length; i++) {
            if (i % 2 == 0) {
                Token iValue = args.get(i);
                
                switch (iValue.getType()) {
                    case "boolean":
                        throw new TypeMismatchedException();
                    case "comma":
                        throw new MissingOperandException();
                    case "decimal":
                        break;
                    default:
                        throw new FunctionCallException();
                }

                double nowValue = iValue.getDecimal();
            } else {
                if (!args.get(i).getType().equals("comma"))
                    throw new FunctionCallException();
            }
        }
        if (length % 2 == 0)
            throw new MissingOperandException();
    }

    /**
     * Calc the sin or cos function.
     * Condition:
     *   1. Argument must only one. Else -> FunctionCallException
     *   2. Type of argument must be decimal. Else -> TypeMismatchedException
     *   3. Function must be sin or cos. Else -> FunctionCallException
     * @return the result
     * @throws ExpressionException FunctionCallException, TypeMismatchedException
     */
    private Token exprSinCos() throws ExpressionException {
        if (length == 0)
            throw new MissingOperandException();
        if (length != 1)
            throw new FunctionCallException();
        
        Token value = args.get(0);

        switch (func.getValue()) {
            case "sin":
                return new Decimal(Math.sin(value.getDecimal()), false);
            case "cos":
                return new Decimal(Math.cos(value.getDecimal()), false);
        }
        throw new FunctionCallException();
    }

    /**
     * Calc the max or min function
     * Condition:
     *   1. number of args must be odd, because (comma = decimal - 1),
     *      so total is 2 * decimal - 1, is an odd
     *      Else -> FunctionCallException
     *   2. number of decimal must > 1. Else -> MissingOperandException
     *   3. permulation must be "decimal comma decimal comma ... decimal"
     *      Else -> not decimal but boolean : TypeMismatchedException
     *              lack comma : FunctionCallException
     * @return max / min of args, non-terminal
     * @throws ExpressionException violate the condition
     */
    private Token exprMaxMin() throws ExpressionException {
        
        if (length == 0)
            throw new MissingOperandException();

        Token firstValue = args.get(0);
        double maxValue = firstValue.getDecimal();
        double minValue = maxValue;
        for (int i = 1; i < length; i++) {
            if (i % 2 == 0) {
                Token iValue = args.get(i);

                double nowValue = iValue.getDecimal();
                maxValue = Math.max(nowValue, maxValue);
                minValue = Math.min(nowValue, minValue);
            } else {
                if (!args.get(i).getType().equals("comma"))
                    throw new FunctionCallException();
            }
        }

        if ((length + 1) / 2 <= 1)
            throw new MissingOperandException();

        switch (func.getValue()) {
            case "max":
                return new Decimal(maxValue, false);
            case "min":
                return new Decimal(minValue, false);
        }
        throw new FunctionCallException();
    }

    /**
     * calc the expr.
     * Choose if UnaryFunc or VariablFunc.
     * @return the result of function
     * @throws ExpressionException violate the condition above
     */
    public Token expr() throws ExpressionException {

        checkArgs();

        if (func.getInformation().equals("unary"))
            return exprSinCos();
        else
            return exprMaxMin();
    }
}
