import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class day9 {

	public static void main(String[] args) throws Exception {

		File file = new File("C:\\Users\\itk78\\Documents\\AdventOfCode\\inputs\\input9.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		ArrayList<BigInteger> input = new ArrayList<BigInteger>();
		
		//fill input
		String line;
		while((line = br.readLine()) != null) {
			BigInteger i = new BigInteger(line.trim());
			input.add(i);
		}
		br.close();
		
		part1(input, 25);
		part2(input);
	}
	
	//used big int since I didnt really know how big the numbers were gonna be
	public static void part1(List<BigInteger> input, int preamble) {
		BigInteger target = input.get(preamble); //get element directly after preamble
		List<BigInteger> possibleValues = new ArrayList<BigInteger>(); //will be used to store our possible values to add to the target
		int firstIndex = 0;
		int lastIndex = preamble;
		boolean targetValid = true;
		
		while(lastIndex < input.size() && targetValid) {
			targetValid = false; //start believing it isnt valid
			possibleValues = input.subList(firstIndex, lastIndex);
			for(BigInteger i : possibleValues) {
				for(BigInteger j : possibleValues) {
					if(i != j && i.add(j).equals(target)) {
						targetValid = true;
						System.out.println("Target: " + target.toString() + " Values: " + i.toString() + " " + j.toString());
						break;
					}
				}
				if(targetValid) {
					break;
				}
			}
			
			if(targetValid) {
				//need to get new target and move possible values
				firstIndex++;
				lastIndex++;
				target = input.get(lastIndex);
			}
			else {
				System.out.println("TARGET NOT VALID: " + target.toString());
				return; //end 
			}
		}
		
		
		
	}
	
	
	public static void part2(List<BigInteger> input) {
		BigInteger target = new BigInteger("20874512"); //found in part1
		
		for(int i = 0; i < input.size(); i++) {
			List<BigInteger> addToTarget = new ArrayList<BigInteger>(); //will store our values, new each time we start over
			BigInteger num = input.get(i);
			BigInteger total = new BigInteger("0");
			total = total.add(num);
			addToTarget.add(num);
			boolean keepGoing = true;
			while(keepGoing) {
				for(int j = i + 1; j < input.size(); j++) {
					BigInteger num2 = input.get(j);
					total = total.add(num2);
					if(total.compareTo(target) < 0) {
						addToTarget.add(num2);
						
					}
					else if(total.compareTo(target) == 0) {
						addToTarget.add(num2);
						System.out.println("This is the sequence: " + addToTarget.toString());
						Collections.sort(addToTarget);
						System.out.println(addToTarget);
						BigInteger end = BigInteger.ZERO;
						for(BigInteger k : addToTarget) {
							end = end.add(k);
						}
						System.out.println(end); //just to make sure it acutally is the right number
						
						System.out.println("result 2 = " + addToTarget.get(0).add(addToTarget.get(addToTarget.size()-1)));
						return;
						
					}
					else {
						keepGoing = false; //went over target, need to reset
					}
				}
			}
			
			
		}
	}
	
	

}
