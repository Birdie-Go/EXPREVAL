package parser;

import scanner.*;
import token.*;
import exceptions.*;

import java.util.*;

/**
 * Main program of the expression based calculator ExprEval
 * 
 * @author birdie
 * @version 3.00 (Last update: 2022/5/07)
**/
public class Calculator {
	/**
	 * The main program of the parser. You should substitute the body of this method 
	 * with your experiment result. 
	 * 
	 * @param expression  user input to the calculator from GUI. 
	 * @return  if the expression is well-formed, return the evaluation result of it. 
	 * @throws ExpressionException  if the expression has error, a corresponding 
	 *                              exception will be raised. 
	**/
	public double calculate(String expression) throws ExpressionException {
		try {
			return new Parser(expression).opp();
		} catch (Exception e) {
			// e.printStackTrace();
			throw e;
		} 
	}
}
