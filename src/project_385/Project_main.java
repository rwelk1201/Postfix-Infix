package project_385;

import javax.swing.*;

public class Project_main extends JFrame{
	private static final long serialVersionUID = 5107341313018538968L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] lines = getFile().split("\\n");
		for(String line:lines){
			Parser test=new Parser(line);
			System.out.println("Original: " + line);
			System.out.println("Postfix : " + test.infix2Postfix());
			System.out.println("Infix   : " + test.postfix2Infix());
			System.out.println();
		}
	}
	
	public static String getFile(){
		FileGetter fileIn=new FileGetter();
		return(fileIn.getOutput());
	}
	
	public String getText(){
		return("test text");
	}
}
