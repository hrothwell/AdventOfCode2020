import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class day2 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		File file = new File("C:\\Users\\itk78\\Documents\\AdventOfCode\\inputs\\input2.txt");
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		//first password policy
		int numValid1 = 0;
		//second policy
		int numValid2 = 0;
		
		//ex: 1-3 a: _____ says a needs to appear 1 to 3 times
		int low = 0;
		int high = 0;
		
		char find;
		String password = "";
		String beforeColon;
		String lowHigh;
		String line;
		
		//example input: "1-3 a: password" 
		while((line = br.readLine()) != null) {
			password = line.split(":")[1]; //first cell after the colon
			System.out.println(password);
			beforeColon = line.split(":")[0];
			System.out.println(beforeColon);
			
			find = line.split(" ")[1].charAt(0); //the letter, removing colon
			System.out.println(find);
			lowHigh = line.split(" ")[0];
			System.out.println(lowHigh);
			low = Integer.parseInt(lowHigh.split("-")[0]);
			high = Integer.parseInt(lowHigh.split("-")[1]);
			
			int occurrences = 0;
			for(int i = 0; i < password.length(); i++) {
				if(find == password.charAt(i)) {
					occurrences++;
				}
			}
			
			if(occurrences >= low && occurrences <= high) {
				numValid1++;
			}
			
			char atIndex1 = password.charAt(low);
			char atIndex2 = password.charAt(high);
			
			if((find == atIndex1 || find == atIndex2) && !(find == atIndex1 && find == atIndex2)) {
				numValid2++;
			}
			
		}
		
		System.out.println(numValid1);
		System.out.println(numValid2);
	}

}
