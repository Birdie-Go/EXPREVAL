package parser;

import token.*;
import scanner.*;
import exceptions.*;

import java.util.*;

/**
 * Parser.
 * Give the input buffer (expr), use opp to parse.
 */
public class Parser {
    /** input buffer (expr). */
    private ArrayList <Token> buffer;
    /** stack of opp to parse. */
    private ArrayList <Token> stack;
    /**
     * static opp table.
     * It is used to give the action.
     * 0 : shift
     * 1 : reduce
     * 2 : accept
     * -1 : miss operator
     * -2 : miss operand
     * -3 : miss (
     * -4 : miss )
     * -5 : thriple error
     * -6 : function error
     */
    private static int [][]table;

    /**
     * constructor.
     * Buffer is the token list of expression
     * Stack is empty.
     * Add a $ at the end of expression.
     * @param expression expr
     * @throws ExpressionException scanner will throw
     */
    Parser(String expression) throws ExpressionException {
        buffer = new ArrayList <Token>(new MyScanner(expression).scan());
        stack = new ArrayList <Token>();
        buffer.add(new Symbol("$"));
    }

    /**
     * Get the first terminal from the stack top
     * @return the terminal token
     */
    private Token getStackTop() {
        int stackLength = stack.size();
        int i = stackLength - 1;
        for (; i >= 0; i--) {
            if (stack.get(i).isTermial())
                break;
            }
        return stack.get(i);
    }

    /**
     * The new token need to add in the stack.
     * @param lookahead the token need to add
     * @return the real token type.
     *         It means change token to the like decimal or boolean.
     * @throws IllegalSymbolException not the defined type
     */
    private Token addInStack(Token lookahead) throws IllegalSymbolException {
        switch (lookahead.getType()) {
            case "decimal":
                return new Decimal(lookahead);
            case "boolean":
                return new MyBoolean(lookahead);
            case "function":
                return new Function(lookahead);
            case "operator":
                return new Operator(lookahead);
            case "parenthesis":
                return new Parenthesis(lookahead);
            case "relation":
                return new Relation(lookahead);
            case "thriple":
                return new Thriple(lookahead);
            case "unary":
                return new Unary(lookahead);
            case "comma":
            case "dollar":
                return new Symbol(lookahead);
            default:
                throw new IllegalSymbolException();
        }
    }

    /**
     * Get the expr answer from the stack.
     * @return The expr answer, a token.
     * @throws TypeMismatchedException The answer must be a decimal.
     */
    private double getAnswer() throws TypeMismatchedException {
        if (stack.size() == 2) {
            Token top = stack.get(stack.size() - 1);
            if (top.getType().equals("decimal"))
                return top.getDecimal();
            else
                throw new TypeMismatchedException();
        }
        return 0;
    }

    /**
     * Reduce.
     * @throws ExpressionException excepiton in reducer.
     */
    private void reduce() throws ExpressionException {
        Token stackTop = getStackTop();
        Token lookahead = buffer.get(0);
        int action = table[stackTop.getPriority()][lookahead.getPriority()];
        while (action == 1) {
            stack = new Reducer(stack).calculate(stackTop.getType());
            stackTop = getStackTop();
            action = table[stackTop.getPriority()][lookahead.getPriority()];
        }
    }

    /**
     * Shift.
     * Add the token to stack, and remove from buffer.
     * @param lookahead The token need to shift.
     * @throws IllegalSymbolException error in addInStack
     */
    private void shift(Token lookahead) throws IllegalSymbolException {
        stack.add(addInStack(lookahead));
        buffer.remove(0);
    }

    /**
     * The opp.
     * Compare the stack top and buffer top.
     * Accroding opp table to make action.
     * 0 for shift, 1 for reduce, 2 for accept.
     * And negative for error.
     * @return the result.
     * @throws ExpressionException exception in the table.
     */
    public double opp() throws ExpressionException {
        stack.add(new Symbol("$"));
        while (true) {
            Token stackTop = getStackTop();
            Token lookahead = buffer.get(0);
            int action = table[stackTop.getPriority()][lookahead.getPriority()];

            switch (action) {
                case 0:
                    shift(lookahead);
                    break;
                case 1:
                    reduce();
                    break;
                case 2:
                    double answer = getAnswer();
                    return answer;
                case -1:
                    throw new MissingOperatorException();
                case -2:
                    throw new MissingOperandException();
                case -3:
                    throw new MissingLeftParenthesisException();
                case -4:
                    throw new MissingRightParenthesisException();
                case -5:
                    throw new TrinaryOperationException();
                case -6:
                    throw new FunctionCallException();
            }
        }
    }

    /**
     * Opp table build.
     */
    static {
        table = new int[][]{
            /*         b   d   (   )   f   -   ^   *   +   r   !   &   |   ?   :   ,   &  */
            /* b */ { -1, -1, -1, 1 , -1, 1 , 1 , 1 , 1 , 1 , -1, 1 , 1 , 1 , 1 , 1 , 1 },
            /* d */ { -1, -1, -1, 1 , -1, 1 , 1 , 1 , 1 , 1 , -1, 1 , 1 , 1 , 1 , 1 , 1 },
            /* ( */ { 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , -5, 0 , -4},
            /* ) */ { -1, -1, -1, 1 , -1, 1 , 1 , 1 , 1 , 1 , -1, 1 , 1 , 1 , 1 , 1 , 1 },
            /* f */ { -6, -6, 0 , -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3},
            /* - */ { 0 , 0 , 0 , 1 , 0 , 0 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 },
            /* ^ */ { 0 , 0 , 0 , 1 , 0 , 0 , 0 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 },
            /* * */ { 0 , 0 , 0 , 1 , 0 , 0 , 0 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 },
            /* + */ { 0 , 0 , 0 , 1 , 0 , 0 , 0 , 0 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 },
            /* r */ { 0 , 0 , 0 , 1 , 0 , 0 , 0 , 0 , 0 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 },
            /* ! */ { 0 , 0 , 0 , 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 1 , 1 , 1 , 1 , 1 , 1 },
            /* & */ { 0 , 0 , 0 , 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 1 , 1 , 1 , 1 , 1 , 1 },
            /* | */ { 0 , 0 , 0 , 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 1 , 1 , 1 , 1 , 1 },
            /* ? */ { 0 , 0 , 0 , -2, 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , -5},
            /* : */ { 0 , 0 , 0 , 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 1 , 1 , 1 },
            /* , */ { 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 1 },
            /* $ */ { 0 , 0 , 0 , -3, 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , -5, 0 , 2 }
        };
    }
}
