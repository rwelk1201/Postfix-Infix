package project_385;

// parses infix expressions
public class Parser {

	ADTList<String> tokens = new ADTList<String>();

	/**
	 * Instantiates a new parser object with a given input string
	 * 
	 * @param input
	 *            An infix expression
	 */
	public Parser(String input) {
		tokens = tokenizer(input);
	}

	/**
	 * Returns the precedence of a given string
	 * 
	 * @param token
	 *            the string
	 * @return precedence of string
	 */
	private int precedence(String token) {
		switch (token) {
		case "(":
		case ")":
			return 1;
		case "*":
		case "/":
			return 3;
		case "+":
		case "-":
			return 2;
		default: // variable or number
			return -1;
		}
	}

	/**
	 * Returns whether or not a Character is an operator
	 * 
	 * @param ch
	 *            the character
	 * @return a boolean value indicating whether or not the Character is an
	 *         operator
	 */
	private boolean isOperator(char ch) {
		switch (ch) {
		case '*':
		case '/':
		case '+':
		case '-':
			return true;
		default:
			return false;
		}
	}

	/**
	 * Returns the postfix representation of the expression If there are fewer
	 * than 3 tokens, return null. Otherwise, loop through each token. If the
	 * token is a left parenthesis, push the token onto the stack. If the token
	 * is a right parenthesis, pop tokens off the stack until you reach a left
	 * parentheses or the stack is empty. If the stack is empty, return null. If
	 * the token is an operator, pop all tokens until you reach a parenthesis,
	 * appending the values to the string buffer. Otherwise, if the token is an
	 * operator, pop the stack until the stack is empty or the precedence of the
	 * next token is greater than the current one. Trim the string buffer by one
	 * character and return the concatenation of the strings.
	 * 
	 * @return A postfix string
	 **/
	public String infix2Postfix() {
		if (tokens.size() < 3) {
			return (null); // invalid expression, must have at least 2 operands
							// and 1 operator
		}

		String lastToken = "";
		ADTStack<String> stack = new ADTStack<String>();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < tokens.size(); i++) { // iterate through all tokens
			if (tokens.get(i).equals("(")) {
				stack.push(tokens.get(i));
			} else if (tokens.get(i).equals(")")) { // if the token is a right
													// paren, iterate until a
													// right paren is found or
													// the stack is empty
				while (!stack.isEmpty() && !stack.peek().equals("(")) {
					buffer.append(stack.pop()).append(' ');
				}
				if (stack.peek() == null) {
					return (null); // no closing paren => bad expression
				} else {
					stack.pop(); // assume it's a left paren
				}
			} else if (isOperator(tokens.get(i).charAt(0))) { // handle 8*2+4
				while (!stack.isEmpty()
						&& precedence(stack.peek()) >= precedence(tokens.get(i))) {
					buffer.append(stack.pop()).append(' ');
				}
				stack.push(tokens.get(i));
			} else {
				if (lastToken != "" && !lastToken.matches("[()\\+\\-\\*/]")) {
					return (null); // if the last token processed was also a
									// variable/constant => bad expression
				}
				buffer.append(tokens.get(i)).append(' ');
			}
			lastToken = tokens.get(i);
		}
		while (!stack.isEmpty()) {
			if (stack.peek().equals("(")) {
				return null; // mismatched parens
			}
			buffer.append(stack.pop()).append(' ');
		}
		buffer.setLength(buffer.length() - 1); // trim the extra space
		return buffer.toString();
	}

	/**
	 * Returns the infix representation of the expression. To generate an infix
	 * expression to convert, call infix2Postfix. Call tokenizer on the result
	 * to generate a list of tokens. Instantiate an empty stack. Loop through
	 * each token in the list. If the token isn't an operator, push it to the
	 * stack. Otherwise, pop the next two tokens off the stack. Return null if
	 * either token is null. Push the concatenation of a left parenthesis, the
	 * first operand, the operator, the last operand, and a right parenthesis
	 * onto the stack. When iteration is finished, pop the stack and return the
	 * value.
	 * 
	 * @return An infix string
	 */
	public String postfix2Infix() {

		ADTList<String> newTokens = tokenizer(infix2Postfix());
		ADTStack<String> stack = new ADTStack<String>();
		String operand1, operand2;

		// if you find an operator, assert that next two tokens are operands
		for (int i = 0; i < newTokens.size(); i++) {
			if (isOperator(newTokens.get(i).charAt(0))) {
				operand2 = stack.pop();
				operand1 = stack.pop();
				if (operand1 == null || operand2 == null) {
					return null;
				}
				stack.push("(" + operand1 + newTokens.get(i) + operand2 + ")");
				// explicit parens unnecessary sometimes
			} else {
				stack.push(newTokens.get(i));
			}
		}

		return stack.pop();
	}

	/**
	 * Returns a list of tokens from a given string. Algorithm: Loop through all
	 * characters in the input string. If the character is an operator or a
	 * parenthesis, append the character to the token list. Otherwise if the
	 * character is a letter, create a string of characters from the current
	 * character to the next non-alphanumeric character and append that to the
	 * token list. Otherwise if the character is a digit, create a string of
	 * letters starting at the current character and extending to the next
	 * non-digit character, and append that to the token list. All other
	 * characters should be ignored.
	 * 
	 * @param input
	 *            A string representing an expression
	 * @return A list of generated tokens
	 */
	public ADTList<String> tokenizer(String input) {

		ADTList<String> tokens = new ADTList<String>();

		for (int i = 0; i < input.length(); i++) {
			int start = i;
			char ch = input.charAt(i);
			if (isOperator(ch) || ch == '(' || ch == ')') {
				// it's an operator
				tokens.add(String.valueOf(ch));
			} else if (Character.isLetter(ch)) {
				// must be a variable identifier
				for (; i + 1 < input.length()
						&& (Character.isLetter(input.charAt(i + 1)) || Character
								.isDigit(input.charAt(i + 1))); i++)
					;
				tokens.add(input.substring(start, i + 1));
			} else if (Character.isDigit(ch)) {
				// look until the last digit which is the end of the number
				for (; i + 1 < input.length()
						&& Character.isDigit(input.charAt(i + 1)); i++)
					;
				tokens.add(input.substring(start, i + 1));
			} else {
				// we don't add invalid tokens
			}
		}
		return tokens;
	}

	/**
	 * Returns whether or not the expression is valid
	 * 
	 * @return A boolean value indicating whether or not the expression is valid
	 */
	public boolean isValid() {
		// if infix2Postfix() returns null, the expression is invalid
		return infix2Postfix() != null;
	}
}
