import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class day6 {
	public static void main(String[] args) throws Exception {
		File file = new File("D:\\Documents\\AdventOfCode2020Input\\input6.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		ArrayList<String> input = new ArrayList<String>();
		String line;
		while((line = br.readLine()) != null) {
			input.add(line);
		}
		
		System.out.println(helper2(input));
		
	}
	
	public static int helper1(List<String> input) {
		int result = 0;
		int test = 0;
		List<HashSet<Character>> groupAnswers = new ArrayList<HashSet<Character>>();
		int currentGroup = 0;
		HashSet<Character> group = new HashSet<Character>();
		
		//fill the group answers
		for(String s : input) {
			System.out.println(s);
			if(s.trim().isEmpty()) {
				//new line, incrememnt currentGroup
				System.out.println("Start new group");
				System.out.println("answers from previous group: " + group.toString());
				groupAnswers.add(group);
				currentGroup++;
				group = new HashSet<Character>();
				
			}
			else {
				for(char c : s.toCharArray()) {
					if(group.add(c)) test++;
				}
			}
			
		}
		groupAnswers.add(group); //straggler at the end
		
		
		for(HashSet<Character> set : groupAnswers) {
			System.out.println("the set: " + set.toString());
			result += set.size();
		}
		System.out.println(test);
		return result;
	}
	
	public static int helper2(List<String> input) {
		int result = 0;
		boolean haveAll = true;
		List<HashSet<Character>> groupAnswers = new ArrayList<HashSet<Character>>();
		HashSet<Character> individualAnswers = new HashSet<Character>();
		
		for(String s : input) {
			if(s.trim().isEmpty()) {
				//new group, add logic to check how many answers everyone had in common
				System.out.println("Checking group for matching answers");
				System.out.println("First group memember set: " + groupAnswers.get(0).toString());
				for(char c : groupAnswers.get(0)) {
					
					haveAll = true; //set to true to start
					for(HashSet<Character> set : groupAnswers) {
						if(set.contains(c) && haveAll) { //check if we are still in the clear each time
							//has a match
							//System.out.println("Found a match");
							haveAll = true; //this is a good line
						}
						else {
							//not a match
							haveAll = false; //one thing didn't match so not a good one
							
						}
					}
					//check at the end of each answer if all people had it
					if(haveAll) {
						System.out.println("Group all had " + c + " incrementing counter");
						result++;
					}
				}
				
				//create a new group
				groupAnswers = new ArrayList<HashSet<Character>>();
				
			}
			else {
				individualAnswers = new HashSet<Character>(); //each line is new person
				for(char c : s.toCharArray()) {
					individualAnswers.add(c);
				}
				System.out.println("Adding answsers to group: " + individualAnswers.toString());
				groupAnswers.add(individualAnswers);
			}
		}
		
		System.out.println("First group memember set: " + groupAnswers.get(0).toString());
		//check last group as well
		for(char c : groupAnswers.get(0)) {
			
			haveAll = true; //set to true to start
			for(HashSet<Character> set : groupAnswers) {
				if(set.contains(c)) {
					//has a match
					//System.out.println("Found a match");
					haveAll = true; //this is a good line
				}
				else {
					//not a match
					haveAll = false; //one thing didn't match so not a good one
					
				}
			}
			//check at the end of each answer if all people had it
			if(haveAll) {
				System.out.println("Group all had " + c + " incrementing counter");
				result++;
			}
		}
		System.out.println(result);
		
		return result;
	}
	
	
}
