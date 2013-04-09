package project_385;

public class Parser {
	
	ADTList<String> tokens = new ADTList<String>();
	
	public Parser(String input){
		tokens = tokenizer(input);
	}
	
	public String infix2Postfix(){
		// TODO pseudocode
		return("in-2-post");
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
			if (ch == '(' || ch == ')' || ch == '*' || ch == '/' || ch == '+' || ch == '-') {
				tokens.add(String.fromValue(ch));
			} else if (Character.isLetter(ch)) {
				for (; i+1 < input.length && (Character.isLetter(input.charAt(i+1)) || Character.isDigit(input.charAt(i+1))); i++);
				tokens.add(input.substring(start, i));
			} else if (Character.isDigit(ch)) {
				for (; i+1 < input.length && Character.isDigit(input.charAt(i+1))); i++);
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
