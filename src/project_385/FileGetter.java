package project_385;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;

public class FileGetter extends JFileChooser {
	/**
	 * Allows the user to select a text file from Documents
	 * Encapsulates most of the file handling
	 * @author Ryan Welker
	 */
	private static final long serialVersionUID = -5737337237919191591L;
	private boolean hasError;
	private String output="";
	
	/** String to hold the file name*/
	public String fname = "";

	/**
	 * Initializes the file chooser dialog and reads the contents of the selected file
	 */
	public FileGetter(){
		//Creates file chooser dialog
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(this.getParent());

		//If a file was submitted, read the filename
        if (returnVal == JFileChooser.APPROVE_OPTION) {
			BufferedReader reader=null;
			try {
				reader = new BufferedReader(new FileReader(fc.getSelectedFile()));
				fname = fc.getSelectedFile().toString();
				fname = fname.substring(fname.lastIndexOf("\\")+1,fname.length());
			}catch(FileNotFoundException e){
				hasError=true;
			}

			String line = null;
			try {
				while ((line=reader.readLine()) != null) {
					output+=line+"\n";
				}
				reader.close();
			} catch (IOException e) {
				hasError=true;
			}
            hasError=false;
        } else {
        	hasError=true;
        }
	}

	/**
	 * @return Returns a boolean based on whether an error has occurred
	 */
	public boolean failed(){
		return(hasError);
	}

	/**
	 * @return Returns the contents of the text file, or returns null if an exception occurred
	 */
	public String getOutput(){
		if(!failed()){
			return output;
		}else{
			return(null);
		}
	}

	/**
	 * @return Returns the filename of the file that was selected
	 */
	public String getFilename(){
		if(!failed()){
			return fname;
		}else{
			return(null);
		}
	}
}