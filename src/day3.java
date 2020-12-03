import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;

public class day3 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		File file = new File("C:\\Users\\itk78\\Documents\\AdventOfCode\\inputs\\input3.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		//defining these so we could change the slop if we wanted to this way but probably not needed
		int over = 3;
		int down = 1;
		int numTrees = 0;
		char tree = '#';
		ArrayList<String> theHill = new ArrayList<String>();
		//NOTE the pattern repeats, so once we reach the very "end" of our horizontal array, we can just loop back to the start of that current down one
		
		//how to store our input? Store each line in a singular array? 
		//that would mean the height of our slope is the size of our array
		
		//fill the hill with initial info
		String line;
		while((line = br.readLine()) != null) {
			theHill.add(line);
			System.out.println(line);
		}
		
		System.out.println("The height of the hill: " + theHill.size());
		
	
		//part 1
		int a = helper(theHill, 3, 1);
		System.out.println("Result 1 : " + a);
		
		//part two
		int b = helper(theHill, 1, 1);
		int c = helper(theHill, 5, 1);
		int d = helper(theHill, 7, 1);
		int e = helper(theHill, 1, 2);
		
		
		//make big numbers for multiplying
		BigDecimal big = new BigDecimal(a);
		big = big.multiply(new BigDecimal(b));
		big = big.multiply(new BigDecimal(c));
		big = big.multiply(new BigDecimal(d));
		big = big.multiply(new BigDecimal(e));
		
		
		System.out.println(big.toString());
		
		//testing pushing in new repo
		
	}

	public static int helper(ArrayList<String> theHill, int over, int down) {
		//"row" meaning height
		int trees = 0;
		int currentRow = 0;
		//"col" being the left/right of the slope. Once this number is larger than the line we need to wrap it
		int currentCol = 0;
		while((currentRow + down) < theHill.size()) {
			currentRow += down; //our "down one"
			String row = theHill.get(currentRow);
			
			//so we wont go over our index. Ex, if we are at position 20 and the string has 21 values, if we add three we will be 
			//at 23 which is out of bounds, so we need to wrap back and we will end up at position 2 (index 1 really). 
			//20+3 = 23. 23 mod 21 = 2. I might need to adjust the values a bit to account for our indexes starting at 0
			currentCol = (currentCol + over) % row.length(); 
			
			if(row.charAt(currentCol) == '#') {
				trees++;
			}
		}
		System.out.println("Number of trees for " + over + " " + down + ": " + trees);
		return trees;
	}
}

