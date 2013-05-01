package project_385;
/**
 * Driver class
 * Instantiates the user interface
 * Controls program flow
 * @author Nathan Dean
 */

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


	/**
	 * Instantiates the user interface
	 */
	public Project_main() {
		//Sets the frame size and minimum size
		this.setSize(500,400); 
		this.setMinimumSize(new Dimension(300,100));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		txtPane = new JTextPane();
		txtPane.setText("");
		//Initialize the scrollbar
	    JScrollPane scrollPane = new JScrollPane(txtPane);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);

		//Label
		JLabel lblChooseAnInput = new JLabel("Choose an input file");
		panel.add(lblChooseAnInput);

		//Browse button
		JButton btnChooseInputFile = new JButton("Browse...");

		panel.add(btnChooseInputFile);

		lblFilename = new JLabel("");
		panel.add(lblFilename);

		//Connect to browse button being clicked
		btnChooseInputFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BrowseButtonClick();
			}
		});


	}

	/**
	 * Main class, creates the GUI and makes it visible
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame j = new Project_main();
		j.setVisible(true);
	}

	/**
	 * Instantiates the FileGetter class and sets the file label
	 * @return Contents of the selected file
	 */
	public String getFile(){
		FileGetter fileIn=new FileGetter();
		lblFilename.setText(fileIn.getFilename());
		return(fileIn.getOutput());
	}

	/**
	 * Appends a string onto the TextPane
	 * @param s String to be appended
	 */
	public void append(String s) {
		   try {
		      Document doc = txtPane.getDocument(); //Fetch the document and add the string
		      doc.insertString(doc.getLength(), s + "\n", null);
		   } catch(Exception exc) {
		      exc.printStackTrace();
		   }
		}

	/**
	 * Clears the TextPane
	 */
	public void clear()
	{
		txtPane.setText(""); //Set the textpane to an empty string
	}

	/**
	 * Fired after browse button is clicked
	 * Outputs the original expression, postfix, and infix
	 */
	public void BrowseButtonClick(){
		clear(); //Clear the text pane
		String contents = getFile(); //Fetch the file's contents
			String[] lines = contents.split("\\n");
			//Split the string up by line and parse each line
			for(String line:lines){
			Parser test=new Parser(line);
			append("Original: " + line); //Output the original line
			if (test.infix2Postfix() == null) { //Parser says invalid expression
				append("Invalid infix expression");
				append("");
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