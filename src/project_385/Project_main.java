package project_385;

import javax.swing.*;

public class Project_main extends JFrame{
	private static final long serialVersionUID = 5107341313018538968L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}
	
	public static String getFile(){
		FileGetter fileIn=new FileGetter();
		return(fileIn.getOutput());
	}
	
	public String getText(){
		return("test text");
	}
}
