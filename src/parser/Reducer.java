package parser;

import token.*;
import exceptions.*;
import parser.expr.*;

import java.util.*;

/**
 * Give the stack, and reduce some of the top.
 */
public class Reducer {
    /** The whole opp stack. */
    private ArrayList <Token> stack;
    /** Length of stack. */
    private int length;

    /**
     * empty constructor.
     */
    public Reducer() {
        stack = new ArrayList <Token>();
        length = 0;
    }

    /**
     * Constructor with stack.
     * @param _stack the opp stack
     */
    public Reducer(ArrayList <Token> _stack) {
        stack = new ArrayList <Token>(_stack);
        length = stack.size();
    }

    /**
     * Find the index of the first terminal from the top of stack.
     * @param last The start index to find.
     * @return the index.
     * @throws MissingOperatorException if not found, is MissingOperatorException.
     */
    private int getTerminalLocation(int last) throws MissingOperatorException {
        for (int i = last; i >= 0; i--)
            if (stack.get(i).isTermial())
                return i;
        throw new MissingOperatorException();
    }

    /**
     * Find the first left parenthesis for the stack top.
     * @return the index.
     * @throws MissingLeftParenthesisException if not found.
     */
    private int findLeftParenthesis() throws MissingLeftParenthesisException {
        for (int i = length - 1; i >= 0; i--)
            if (stack.get(i).getValue().equals("("))
                return i;
        throw new MissingLeftParenthesisException();
    }

    /**
     * Replace from the stack.
     * reduce index [location, location + times) from the stack.
     * And add a new token(result) to index location.
     * @param location the remove statr index
     * @param times the remove numbers
     * @param result the result need to replace
     */
    private void reduce(int location, int times, Token result) {
        for (int i = 0; i < times; i++)
            stack.remove(location);
        stack.add(location, result);
    }

    /**
     * Accroding the expr type, to calculate the result.
     * There are some types:
     *   1. constant
     *   2. operator like + - * /
     *   3. unary type, like negative and not
     *   4. parenthesis, include constant and function
     *   5. relation, like > >=
     *   6. thriple, is ?:
     * @param type the expr type, like operator
     * @return the new stack after reduce.
     * @throws ExpressionException main from the each expr calculate.
     */
    public ArrayList <Token> calculate(String type) throws ExpressionException {
        int i = getTerminalLocation(stack.size() - 1);

        if (type.equals("decimal") || type.equals("boolean")) {

            Token result = new ExprConstant(stack.get(i)).expr();

            reduce(i, 1, result);

        } else if (type.equals("operator")) {

            if (i - 1 < 0 || i + 1 >= length)
                throw new MissingOperandException();
            if (stack.get(i - 1).isTermial() || stack.get(i + 1).isTermial())
                throw new MissingOperandException();
            Token result = new ExprOpreator(stack.get(i), stack.get(i - 1), stack.get(i + 1)).expr();

            reduce(i - 1, 3, result);

        } else if (type.equals("unary")) {

            if (i + 1 >= length)
                throw new MissingOperandException();
            Token result = new ExprUnary(stack.get(i + 1)).expr();

            reduce(i, 2, result);

        } else if (type.equals("parenthesis")) {

            int left = findLeftParenthesis();
            ArrayList <Token> args = new ArrayList <Token> ();
            for (int j = left + 1; j < i; j++)
                args.add(stack.get(j));
            if (left > 0 && stack.get(left - 1).getType().equals("function")) {
                Token result = new ExprFunction(stack.get(left - 1), args).expr();
                reduce(left - 1, i - left + 2, result);
            }
            else {
                if (args.size() <= 0)
                    throw new MissingOperandException();
                if (args.size() > 1)
                    throw new SemanticException();
                if (args.get(0).isTermial())
                    throw new MissingOperandException();
                    
                Token result = new ExprConstant(args.get(0)).expr();
                reduce(left, 3, result);
            }

        } else if (type.equals("relation")) {

            if (i - 1 < 0 || i + 1 >= length)
                throw new MissingOperandException();
            if (stack.get(i - 1).isTermial() || stack.get(i + 1).isTermial())
                throw new MissingOperandException();

            Token result = new ExprRelation(stack.get(i), stack.get(i - 1), stack.get(i + 1)).expr();
            reduce(i - 1, 3, result);

        } else if (type.equals("thriple")) {
            
            if (i - 1 < 0 || i + 1 >= length)
                throw new MissingOperandException();
            int j = getTerminalLocation(i - 1);
            if (j - 1 < 0 || i - j != 2)
                throw new MissingOperandException();
            if (stack.get(j - 1).isTermial() || stack.get(j + 1).isTermial() 
                || stack.get(i + 1).isTermial())
                throw new MissingOperandException();
            
            Token result = new ExprThriple(stack.get(j - 1), stack.get(j + 1), stack.get(i + 1)).expr();
            reduce(j - 1, i + 1 - (j - 1) + 1, result);

        } else
            throw new MissingOperatorException();

        return new ArrayList <Token>(stack);
    }
}
