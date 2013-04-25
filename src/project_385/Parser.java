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
				return 3;
			case "+":
			case "-":
				return 2;
			default: // variable or number
				return -1;
		}
	}
	
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
	
	public String infix2Postfix(){
		if(tokens.size()<3){
			return(null); //invalid expression, must have at least 2 operands and 1 operator
		}		
		String lastToken="";
		ADTStack<String> stack = new ADTStack<String>();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < tokens.size(); i++) {
			if (tokens.get(i).equals("(")) {
				stack.push(tokens.get(i));
			} else if (tokens.get(i).equals(")")) {
				while (!stack.isEmpty() && !stack.peek().equals("(")) {
					buffer.append(stack.pop()).append(' ');
				}
				if (stack.peek()==null) {
					return(null);				//no closing parenth => bad expression
				} else {
					stack.pop(); // assume it's a left paren
				}
			} else if (isOperator(tokens.get(i).charAt(0))) {
				while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(tokens.get(i))) {
					buffer.append(stack.pop()).append(' ');
				}
				stack.push(tokens.get(i));
			} else {
				if(lastToken!="" && !lastToken.matches("[()\\+\\-\\*/]")){
					return(null);	//if the last token processed was also a variable/constant => bad expression
				}
				buffer.append(tokens.get(i)).append(' ');
			}
			lastToken=tokens.get(i);
		}
		while (!stack.isEmpty()) {
			if(stack.peek().equals("(")){
				return("Mismatched Parentheses");
			}
			buffer.append(stack.pop()).append(' ');
		}
		buffer.setLength(buffer.length() - 1);
		return buffer.toString();
	}
	
	public String postfix2Infix() {
		
		ADTList<String> newTokens=tokenizer(infix2Postfix());
		ADTStack<String> stack = new ADTStack<String>();
		String operand1, operand2;
		
		for (int i = 0; i < newTokens.size(); i++) {
			if (isOperator(newTokens.get(i).charAt(0))) {
				operand2 = stack.pop();
				operand1 = stack.pop();
				if (operand1 == null || operand2 == null) {
					return null;
				}
				stack.push("(" + operand1 + newTokens.get(i) + operand2 + ")");
			} else {
				stack.push(newTokens.get(i));
			}
		}
		
		return stack.pop();
	}
	
	public ADTList<String> tokenizer(String input) {
	
		ADTList<String> tokens = new ADTList<String>();
		
		for (int i = 0; i < input.length(); i++) {
			int start = i;
			char ch = input.charAt(i);
			if (isOperator(ch) || ch=='(' || ch==')') {
				tokens.add(String.valueOf(ch));
			} else if (Character.isLetter(ch)) {
				for (; i+1 < input.length() && (Character.isLetter(input.charAt(i+1)) || Character.isDigit(input.charAt(i+1))); i++);
				tokens.add(input.substring(start, i+1));
			} else if (Character.isDigit(ch)) {
				for (; i+1 < input.length() && Character.isDigit(input.charAt(i+1)); i++);
				tokens.add(input.substring(start, i+1));
			}
		}
		return tokens;
	}
	
	public boolean isValid(){
		return infix2Postfix() != null;
	}
}
