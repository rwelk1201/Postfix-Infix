package project_385;

// parses infix expressions
public class Parser {

	ADTList<String> tokens = new ADTList<String>();

	/** Instantiates a new parser object with a given input string
	 * @param input
	 *            An infix expression
	 */
	public Parser(String input) {
		tokens = tokenizer(input);
	}

	/** Computes the precedence of a given token
	 * @param token
	 *            A token string
	 * @return Precedence of @token
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

	/** Returns whether or not a character is an operator
	 * @param ch
	 *            A character
	 * @return A boolean value indicating whether or not the character is an
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
	 * Converts the expression from infix to postfix
	 * 
	 * @return A postfix expression string
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
	 * @return An infix expression if valid, otherwise null
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

	/** Returns a list of tokens from a given string
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
	 * @return Whether or not the expression is valid
	 */
	public boolean isValid() {
		return infix2Postfix() != null;
	}
}
