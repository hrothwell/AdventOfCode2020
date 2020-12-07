import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class day7 {
	public static void main(String[] args) throws Exception {
	
		File file = new File("D:\\Documents\\AdventOfCode2020Input\\input7.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		ArrayList<String> inputLines = new ArrayList<String>();
		
		//fill inputLines with input
		String line;
		while((line = br.readLine()) != null) {
			inputLines.add(line);
		}

		helper1(inputLines);
		//useful words to split input lines by: "contains" "(numeric regex)" "," "bag(s)" etc?
		//will need to make sure to remove all "," and "." so things add to map consistently. could replace all non-alphanumerics
		//no matter where in the order they appear. Need to determine what would be the best to split by
		//Also need to think about how to handle bags that contain no other bags
		
		
		
		
	}
	
	private static void helper1(List<String> inputLines) {
		//the ruls for the bags. Key is a color, value map: key is color, value is how many of that bag
		HashMap<String, HashMap<String, Integer>> bagRules = new HashMap<String, HashMap<String, Integer>>();
		
		//if a bag is listed as "contains ? shiny gold bags"
		//if in the bagRules map a key's value contains "shiny gold" it should appear in here
		HashSet<String> bagsThatDirectlyContainShinyGold = new HashSet<String>();
		
		//if the bag contains a bag that exists in "bags that directlyContainShinyGold"
		//issue: how do we handle indirect-indirect? loop through it multiple times? could just loop through it like 100 times lol
		//OR: keep looping until nothing new was added. That seems like the best idea
		HashSet<String> bagsThatIndirectlyContainShinyGold = new HashSet<String>();
		String keyBag;
		String[] temp;
		for(String s : inputLines) {
			System.out.println(s);
			temp = s.split("contain"); //split to get the key bag and its values first
			keyBag = temp[0].split("bags")[0].trim(); //should keep just the "color" string
			System.out.println("Key bag: " + keyBag);
			System.out.println("temp value: " + temp[1]);
			//contains no other bags
			if(temp[1].contains("no")) {
				System.out.println("no other bags");
				bagRules.put(keyBag, null); //using null for a bag that contains no other bags
			}
			else {
				String nonKeyBagsNoPunctuation = temp[1].replaceAll("[^a-zA-Z0-9 ]", "");
				String[] valueBags = nonKeyBagsNoPunctuation.split("((bags)|(bag))"); //split on bags or bag? will it split on all of them then?
				HashMap<String, Integer> value = new HashMap<String, Integer>();
				for(String bag : valueBags) {
					bag = bag.trim();
					System.out.println("Value bag: " + bag); //want to see what this looks like
					//one last test to make sure this is working correctly now 
				}
			}
		}
	}

}
