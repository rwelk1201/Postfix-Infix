package project_385;

import java.util.ArrayList;

public class TreeParser {
	private ADTBinaryTree tree;
	
	public TreeParser(String input){
		tree=toTree(tokenizer(input));
	}
	
	private int precedence(String token) {
		switch (token) {
			case "*":
			case "/":
				return 2;
			case "+":
			case "-":
				return 1;
			default: // variable or number
				return -1;
		}
	}
	
	private boolean isOperator(char ch) {
		switch (ch) {
			//case '(':		test, parenths not operators
			//case ')':
			case '*':
			case '/':
			case '+':
			case '-':
				return true;
			default:
				return false;
		}
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
	
	public ADTBinaryTree<String> toTree(ADTList<String> input){
		ArrayList<String> iList=new ArrayList();
		for(int i=0;i<input.size();i++){
			iList.add(input.get(i));
		}
		ADTBinaryTree<String> tmpTree = new ADTBinaryTree<String>();
		tmpTree.setRoot(processExpression(tmpTree.getRoot(),iList));
		return(tmpTree);
	}

	private BinaryNode<String> processExpression(BinaryNode<String> curNode, ArrayList<String> input) {
		if(input.size()==1){
			curNode.setItem(input.get(0));
			return(curNode);
		} else {								//this is where it would split the expression around an argument
			for(int i=0;i<input.size();i++){
				int lowOpIndex=input.size()-1;
				if(input.get(i).equals("(")){
					int j=i+1;
					int lefts=1,rights=0;
					while(lefts!=rights && j<input.size()){
						if(input.get(j).equals("(")){
							lefts++;
						} else if(input.get(j).equals(")")){
							rights++;
						}
						j++;
					}
					if(lefts!=rights){
						return(null);
					} else {
						String quantity="";
						for(int k=i; k<=j; k++){
							quantity+=input.get(k); //really have no idea where I'm going with this at this point...
						}
					}
				}
			}
		}
		return curNode;
	}
	
}
