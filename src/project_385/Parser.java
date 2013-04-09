package project_385;

public class Parser {
	
	ADTList<String> tokens = new ADTList<String>();
	
	public Parser(String input){
		tokens = tokenizer(input);
	}
	
	private int precedence(String token) {
		switch (token) {
			case "(":
			case ")":
				return 1;
			case "*":
			case "/":
				return 2;
			case "+":
			case "-":
				return 3;
			default:
				return -1;
		}
	}
	
	private boolean isOperator(char ch) {
		switch (ch) {
			case '(':
			case ')':
			case '*':
			case '/':
			case '+':
			case '-':
				return true;
			default:
				return false;
		}
	}
	
	private 
	
	public String infix2Postfix(){
		
		ADTStack<String> stack = new ADTStack<String>();
		StringBuffer buffer = new StringBuffer();
		
		for (int i = 0; i < tokens.length(); i++) {
			if (tokens[i] == "(") {
				stack.push(tokens[i]);
			} else if (tokens[i] == ")") {
				while (!stack.isEmpty() && stack[0] != "(") {
					buffer.append(stack.pop());
				}
				stack.pop(); // assume it's a left paren
			} else if (isOperator(tokens[i].charAt(0)) {
				while (!stack.isEmpty() > 0 && precedence(stack[0]) > precedence(stack[i])) {
					buffer.append(stack.pop());
				}
			} else {
				buffer.append(tokens[i]);
			}
		}
		while (!stack.isEmpty()) {
			buffer.append(stack.pop());
		}
		return buffer.toString();
	}
	
	public String postfix2Infix(){
		// TODO pseudocode
		return("post-2-in");
	}
	
	public ADTList<String> tokenizer(String input) {
	
		ADTList<String> tokens = new ADTList<String>();
		
		for (int i = 0; i < input.length; i++) {
			int start = i;
			char ch = input.charAt(i);
			if (isOperator(ch)) {
				tokens.add(String.valueOf(ch));
			} else if (Character.isLetter(ch)) {
				for (; i+1 < input.length() && (Character.isLetter(input.charAt(i+1)) || Character.isDigit(input.charAt(i+1))); i++);
				tokens.add(input.substring(start, i));
			} else if (Character.isDigit(ch)) {
				for (; i+1 < input.length() && Character.isDigit(input.charAt(i+1))); i++);
				tokens.add(input.substring(start, i));
			}
		}

		return tokens;
	}
	
	public boolean isValid(){
		//TODO
		return(false);
	}
}
