package scanner;

import token.*;
import exceptions.*;
import scanner.dfa.*;

import java.beans.Expression;
import java.util.*;

import javax.lang.model.util.ElementScanner14;

/**
 * Scanner.
 */
public class MyScanner {
    /**
     * expression need to scan.
     */
    private String expression;
    /**
     * The scanner result, list of tokens.
     */
    private ArrayList <Token> tokens;
    /**
     * The scanner DFA.
     * It is static, because only the same DFA.
     */
    private static DFA dfa = new DFA();

    /**
     * Empyt constructor.
     */
    public MyScanner() {
        expression = new String("");
        tokens = new ArrayList<Token>();
    }

    /**
     * Constructor with a expression.
     * @param _expression expression need to scan.
     */
    public MyScanner(String _expression) {
        expression = new String(_expression.toLowerCase());
        tokens = new ArrayList<Token>();
    }

    /**
     * Check if is unary negative or not.
     * @param now the token String.
     * @return yes or not.
     */
    private boolean checkUnary(String now) {
        if (now.equals("!"))
            return true;
        if (now.equals("-")) {
            int tokenCount = tokens.size();
            if (tokenCount > 0) {
                Token last = tokens.get(tokenCount - 1);
                if (last.getType().equals("decimal") 
                    || last.getValue().equals(")")
                    || last.getType().equals("boolean"))
                    return false;
                else
                    return true;
            } else
                return true;
        }
        return false;
    }

    /**
     * Scan the expression.
     * Let the expression go on the DFA.
     * @return The token list.
     * @throws ExpressionException IllegalIdentifier or EmptyExpression if it is empty.
     */
    public ArrayList <Token> scan() throws ExpressionException {
        int expressionLength = expression.length();
        int index = 0;

        String nowToken = new String();
        dfa.reset();
        boolean startWithLetter = false;
        boolean startWithDigit = false;
        while (index < expressionLength) {
            char now = expression.charAt(index);
            char lookahead = (index + 1 < expressionLength) ? expression.charAt(index + 1) : '$';
            if (now == ' ') {
                index++;
                continue;
            }

            if (dfa.isStart()) {
                if (Character.isLetter(now))
                    startWithLetter = true;
                else if (Character.isDigit(now) || now == '.')
                    startWithDigit = true;
            }
            
            nowToken += now;
            String tokenType = dfa.nextState(now, lookahead);
            if (tokenType.equals("notYet")) {
                index++;
                continue;
            }
            else if (tokenType.equals("error")) {

                if (startWithLetter)
                    throw new IllegalIdentifierException();
                else if (startWithDigit)
                    throw new IllegalDecimalException();
                else
                    throw new IllegalSymbolException();

            } else {

                tokens.add(dfa.getToken(nowToken, checkUnary(nowToken)));
                dfa.reset();
                nowToken = "";
                startWithLetter = false;
                startWithDigit = false;

            }
            index++;
        }

        if (tokens.isEmpty())
            throw new EmptyExpressionException();
        return tokens;
    }
}
