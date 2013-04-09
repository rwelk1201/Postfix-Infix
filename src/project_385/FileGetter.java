package project_385;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;

public class FileGetter extends JFileChooser {
	boolean hasError;
	String output="";
	
	public FileGetter(){
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(this.getParent());

        if (returnVal == JFileChooser.APPROVE_OPTION) {
			BufferedReader reader=null;
			try {
				reader = new BufferedReader(new FileReader(fc.getSelectedFile()));
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
	
	public boolean failed(){
		return(hasError);
	}
	
	public String getOutput(){
		if(!failed()){
			return(output);
		}else{
			return(null);
		}
	}
}
