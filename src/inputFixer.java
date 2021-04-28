import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class inputFixer {

	public static void main(String[] args) throws IOException {
		File file = new File("C:\\Users\\itk78\\Documents\\AdventOfCode\\inputs\\dumbInput.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		ArrayList<String> input = new ArrayList<String>();
		while((line = br.readLine()) != null) {
			input.add(line);
		}
		 try {
		      FileWriter myWriter = new FileWriter("C:\\Users\\itk78\\Documents\\AdventOfCode\\inputsdumbOutput.txt");
		      for(String s : input) {
		    	  myWriter.write("'"+s+"',");
		    	  
		      }
		      myWriter.flush();
		      myWriter.close();
		    } 
		 catch(Exception e) {
			 //eat
		 }
	}
}
