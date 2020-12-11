import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class day10 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		File file = new File("C:\\Users\\itk78\\Documents\\AdventOfCode\\inputs\\input10.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		ArrayList<Integer> input = new ArrayList<Integer>();
		
		
		//fill input
		String line;
		while((line = br.readLine()) != null) {
			Integer i = new Integer(line.trim());
			input.add(i);
		}
		br.close();
		
		input.add(0);//adding the outlet
		
		Collections.sort(input); //if this is sorted that should be the order of the jolts or whatever
		Integer myAdapter = input.get(input.size()-1) + 3;
		input.add(myAdapter); //should be added at the end
		System.out.println(input.toString());
		part1(input);
		List<Integer> nextPossible = new ArrayList<Integer>();
		Integer start = input.get(0);
		Integer three = new Integer("3");
		input.remove(0); //clear first one
		for(Integer i : input) {
			if(i - start <= 3) {
				nextPossible.add(i);
			}
		}
		
		//BigInteger result = part2(input, start, nextPossible, myAdapter);
		//System.out.println("finished part 2 " + result);
		part2Again(input);
	}
	
	public static void part1(List<Integer> input) {
		int firstIndex = 0;
		int secondIndex = 1;
		HashMap<Integer, Integer> differences = new HashMap<Integer, Integer>(); //should only have 1,2,3 as keys?
		
		while(secondIndex < input.size()) {
			Integer i = input.get(secondIndex) - (input.get(firstIndex));
			if(differences.containsKey(i.intValue())) {
				differences.put(i.intValue(), differences.get(i.intValue())+1);
			}
			else {
				differences.put(i.intValue(), 1);//first time
			}
			
			firstIndex++;
			secondIndex++;
		}
		
		System.out.println(differences);
	}
	
	//idea to use recursion to go down each "branch"
	public static BigInteger part2(List<Integer> input, Integer start, List<Integer> nextPossible, Integer myAdapter) {
		BigInteger result = BigInteger.ZERO;
		Integer three = new Integer("3");
		List<Integer> thisList = new ArrayList<Integer>(); //create a clone? 
		thisList.addAll(input);
		System.out.println("starting new recursion, nextPossible set: " + nextPossible);
		if(nextPossible != null && nextPossible.contains(myAdapter)) {
			System.out.println("reached the end of one branch");
			return result = result.add(BigInteger.valueOf(nextPossible.size())); //has size() amount of ways to finish, ie size() possibilities. Shouldnt be a very big number
		}
		
			//should already be a sorted list so I am not going to start by passing in the first outlet...
			for(Integer i : nextPossible) {
				System.out.println("looking down path if next choice was: " + i);
				List<Integer> set = new ArrayList<Integer>();
				System.out.println("removing from thisInput list: " + i);
				thisList.remove(i); //no longer use this in the input
				for(Integer x : thisList) {
					if(x - i <= 3) {
						System.out.println("adding " + x);
						set.add(x);
					}
				}
				result = result.add(part2(thisList, i, set, myAdapter));
				
			}
			
		System.out.println("finished seeing the future on " + start);
		
		
		System.out.println("HAIDEN RETURNING: " + result);
		
		return result;
	}
	
	public static void part2Again(List<Integer> input) {
		final long[] sums = new long[input.get(input.size()-1)+1];
		
		sums[0] = 1;
		for(int i = 0; i < input.size(); i++) {
			final long x = input.get(i) >= 3 ? sums[input.get(i) - 3] : 0;
			final long y = input.get(i) >= 2 ? sums[input.get(i) - 2] : 0;
			final long z = input.get(i) >= 1 ? sums[input.get(i) - 1] : 0;
			sums[input.get(i)] = x + y + z;
		}
		
		System.out.println(sums[sums.length - 1]);
	}

}
