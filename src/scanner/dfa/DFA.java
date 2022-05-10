package scanner.dfa;

import token.*;

/**
 * The DFA of scanner.
 */
public class DFA {
    /** State in DFA now. */
    private int state;
    /** The points of DFA. */
    private static DFAPoint []points;

    /**
     * Constructor, init state is 0.
     */
    public DFA() {
        state = 0;
    }

    /**
     * When scan a token, return to the begin.
     */
    public void reset() {
        state = 0;
    }

    /**
     * Now in state, and want to go an edge which is now.
     * If not an edge is now, return error.
     * If lookahead is not exist or lookahead is $, accept this token.
     * And return the token type.
     * Else return notYet.
     * @param now the edge charactor
     * @param lookahead the lookahead charactor
     * @return the dealing result "error", token type or "notYet"
     */
    public String nextState(char now, char lookahead) {
        if (!points[state].getEdges().containsKey(now)) {
            return "error";
        }
        state = points[state].getEdges().get(now);
        DFAPoint tempState = points[state];
        if (!tempState.getEdges().containsKey(lookahead) && tempState.isFinish() || lookahead == '$') {
            if (!tempState.isFinish())
                return "error";
            return points[state].getType();
        }
        return "notYet";
    }

    /**
     * Return a new token on this state.
     * @param name the token name, like 'sin'
     * @param unaryFlag is or not unary
     * @return a new Token
     */
    public Token getToken(String name, boolean unaryFlag) {
        if (unaryFlag)
            return new Unary(name);
        return points[state].getToken(name);
    }

    /**
     * check state is state or not
     * @return boolean, is or not
     */
    public boolean isStart() {
        return (state == 0);
    }

    /**
     * Build the dfa.
     * It is too large, and I mark some preffix.
     */
    static {
        points = new DFAPoint[46];

        int []booleanTerminal = new int[]{4, 9};
        for (int i = 0; i < 2; i++)
            points[booleanTerminal[i]] = new DFABoolean();

        int []decimalTerminal = new int[]{10, 12, 15};
        for (int i = 0; i < 3; i++)
            points[decimalTerminal[i]] = new DFADecimal();

        int []thripleTerminal = new int[]{16, 17};
        for (int i = 0; i < 2; i++)
            points[thripleTerminal[i]] = new DFAThriple();

        int []operatorTerminal = new int[]{18, 19, 20, 21, 22};
        for (int i = 0; i < 5; i++)
            points[operatorTerminal[i]] = new DFAOperator();

        int []functionTerminal = new int[]{25, 28, 31, 33};
        for (int i = 0; i < 4; i++)
            points[functionTerminal[i]] = new DFAFunction();

        int []relationTerminal = new int[]{35, 36, 37, 38, 39, 40, 41, 42};
        for (int i = 0; i < 8; i++)
            points[relationTerminal[i]] = new DFARelation();
        points[43] = new DFAUnary();

        int []parenthesisTerminal = new int[]{44, 45};
        for (int i = 0; i < 2; i++)
            points[parenthesisTerminal[i]] = new DFAParenthesis();
        
        points[34] = new DFAComma();
        for (int i = 0; i < 46; i++)
            if (points[i] == null)
                points[i] = new DFAInner();

        points[0].addEdge('t', 1);
        points[1].addEdge('r', 2);
        points[2].addEdge('u', 3);
        points[3].addEdge('e', 4);
        points[0].addEdge('f', 5);
        points[5].addEdge('a', 6);
        points[6].addEdge('l', 7);
        points[7].addEdge('s', 8);
        points[8].addEdge('e', 9);
        for (int i = 0; i <= 9; i++) {
            points[0].addEdge((char)(i + 48), 10);
            points[10].addEdge((char)(i + 48), 10);
            points[11].addEdge((char)(i + 48), 12);
            points[12].addEdge((char)(i + 48), 12);
            points[13].addEdge((char)(i + 48), 15);
            points[14].addEdge((char)(i + 48), 15);
            points[15].addEdge((char)(i + 48), 15);
        }
        points[10].addEdge('.', 11);
        points[10].addEdge('e', 13);
        points[12].addEdge('e', 13);
        points[13].addEdge('+', 14);
        points[13].addEdge('-', 14);
        points[0].addEdge('?', 16);
        points[0].addEdge(':', 17);
        points[0].addEdge('+', 18);
        points[0].addEdge('-', 19);
        points[0].addEdge('*', 20);
        points[0].addEdge('/', 21);
        points[0].addEdge('^', 22);
        points[0].addEdge('s', 23);
        points[23].addEdge('i', 24);
        points[24].addEdge('n', 25);
        points[0].addEdge('c', 26);
        points[26].addEdge('o', 27);
        points[27].addEdge('s', 28);
        points[0].addEdge('m', 29);
        points[29].addEdge('a', 30);
        points[30].addEdge('x', 31);
        points[29].addEdge('i', 32);
        points[32].addEdge('n', 33);
        points[0].addEdge(',', 34);
        points[0].addEdge('>', 35);
        points[35].addEdge('=', 36);
        points[0].addEdge('<', 37);
        points[37].addEdge('=', 38);
        points[37].addEdge('>', 39);
        points[0].addEdge('=', 40);
        points[0].addEdge('&', 41);
        points[0].addEdge('|', 42);
        points[0].addEdge('!', 43);
        points[0].addEdge('(', 44);
        points[0].addEdge(')', 45);
    }
}
