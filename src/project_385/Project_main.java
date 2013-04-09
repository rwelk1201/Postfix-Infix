package project_385;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

public class Project_main extends JFrame{

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
