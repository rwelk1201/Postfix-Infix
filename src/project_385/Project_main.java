package project_385;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.text.Document;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Project_main extends JFrame{
	JLabel lblFilename;
	JTextPane txtPane;
	public Project_main() {
		this.setSize(400,300);
		txtPane = new JTextPane();
		txtPane.setText("");
		getContentPane().add(txtPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		

		JLabel lblChooseAnInput = new JLabel("Choose an input file");
		panel.add(lblChooseAnInput);
		
		JButton btnChooseInputFile = new JButton("Browse...");

		panel.add(btnChooseInputFile);
		
		lblFilename = new JLabel("");
		panel.add(lblFilename);
		
		btnChooseInputFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BrowseButtonClick();
			}
		});
		
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("RUNNING");
		JFrame j = new Project_main();
		j.setVisible(true);
	}
	
	public String getFile(){
		FileGetter fileIn=new FileGetter();
		System.out.println("84 : " + fileIn.getFilename());
		lblFilename.setText(fileIn.getFilename());
		return(fileIn.getOutput());
	}
	
	public String getText(){
		return("test text");
	}
	
	public void append(String s) {
		   try {
		      Document doc = txtPane.getDocument();
		      doc.insertString(doc.getLength(), s + "\n", null);
		   } catch(Exception exc) {
		      exc.printStackTrace();
		   }
		}
	
	public void clear()
	{
		txtPane.setText("");
	}
	
	public void BrowseButtonClick(){
		clear();
		String contents = getFile();
		 //txtPane.setText(contents);
			String[] lines = contents.split("\\n");
			for(String line:lines){
			Parser test=new Parser(line);
			append("Original: " + line);
			append("Postfix : " + test.infix2Postfix());
			append("Infix : " + test.postfix2Infix());
			append("");
			}
	}
}
