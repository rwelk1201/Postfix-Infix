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
			default: // variable or number
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
	
	public String infix2Postfix(){
		
		ADTStack<String> stack = new ADTStack<String>();
		StringBuffer buffer = new StringBuffer();
		
		for (int i = 0; i < tokens.length(); i++) {
			if (tokens[i] == "(") {
				stack.push(tokens[i]).append(' ');
			} else if (tokens[i] == ")") {
				while (!stack.isEmpty() && stack[0] != "(") {
					buffer.append(stack.pop()).append(' ');
				}
				stack.pop(); // assume it's a left paren
			} else if (isOperator(tokens[i].charAt(0)) {
				while (!stack.isEmpty() > 0 && precedence(stack[0]) > precedence(stack[i])) {
					buffer.append(stack.pop()).append(' ');
				}
			} else {
				buffer.append(tokens[i]).append(' ');
			}
		}
		while (!stack.isEmpty()) {
			buffer.append(stack.pop()).append(' ');
		}
		buffer.setLength(buffer.length() - 1);
		return buffer.toString();
	}
	
	public String postfix2Infix(){
		
		ADTStack<String> stack = new ADTStack<String>();
		String output, operand1, operand2 = "";
		
		for (int i = 0; i < tokens.size(); i++) {
			if (isOperator(tokens[i].charAt(0))) {
				operand2 = stack.pop();
				operand1 = stack.pop();
				if (operand1 == null || opernad2 == null) {
					return null;
				}
				output += "(" + operand1 + tokens[i] + operand2 + ")";
				stack.push(output);
			} else {
				stack.push(tokens[i]);
			}
		}
		
		output = stack.pop();
		
		if (!stack.isEmpty()) {
			return null;
		}
		
		return output;
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
