package project_385;

import javax.swing.*;
import javax.swing.text.Document;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Project_main extends JFrame{
	private static final long serialVersionUID = -5082312227289434961L;
	JLabel lblFilename;
	JTextPane txtPane;
	public Project_main() {
		this.setSize(500,400);
		this.setMinimumSize(new Dimension(300,100));
		txtPane = new JTextPane();
		txtPane.setText("");
	    JScrollPane scrollPane = new JScrollPane(txtPane);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
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
			if (test.infix2Postfix() == null) {
				append("Invalid infix expression");
			}
			else
			{
			append("Postfix : " + test.infix2Postfix());
			append("Infix : " + test.postfix2Infix());
			append("");
			}
			}
	}
}
