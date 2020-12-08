import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		
		HashSet<String> bagsThatCanContainShinyGold = new HashSet<String>(); //trying something new, make one list at the start
		
		HashSet<String> hasNullValue = new HashSet<String>();
		
		String keyBag;
		String[] temp;
		//fill our bag rules map and direct shiny set
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
				hasNullValue.add(keyBag);
			}
			else {
				String nonKeyBagsNoPunctuation = temp[1].replaceAll("[^a-zA-Z0-9 ]", "");
				String[] valueBags = nonKeyBagsNoPunctuation.split("bags?"); //split on bags or bag (the s is optional thanks to ?)
				String numeric = "[0-9]+";
				Pattern pattern = Pattern.compile(numeric);
				Matcher matcher;
				HashMap<String, Integer> value = new HashMap<String, Integer>();
				for(String bag : valueBags) {
					bag = bag.trim();
					System.out.println("Value bag: " + bag); //want to see what this looks like
					matcher = pattern.matcher(bag);
					int numBags = -1;//default value 
					//this should always find something if it goes well, but need to call find() first anyways
					//gets number of this colored bag
					if(matcher.find()) {
						numBags = Integer.parseInt(matcher.group());
					}
					
					//leave just the color
					String bagColor = bag.replaceAll(numeric, "").trim();
					
					if(bagColor.equals("shiny gold")) {
						bagsThatCanContainShinyGold.add(keyBag);//new test
					}
					
					value.put(bagColor, numBags);
					
					System.out.println("bag color: " + bagColor+  " numBags: " + numBags);
				}
				bagRules.put(keyBag, value);
			}
		}
		
		System.out.println(bagRules);
		System.out.println("Bags that can contain shiny gold after first loop (direct relationship): " + bagsThatCanContainShinyGold);
		
		
		boolean foundNew = true;
		
		while(foundNew) {
			//idea: loop through the set/map until we no longer add anything new to the shiny gold set
			
			foundNew = false;
			//temp set to be used to add new things to shiny gold set
			HashSet<String> tempSet = new HashSet<String>();
			
			//loop through the maps and add to shiny gold set
			for(String key : bagRules.keySet()) {
				if(!hasNullValue.contains(key) || bagRules.get(key) != null) {
					for(String value : bagRules.get(key).keySet()) {
						if(bagsThatCanContainShinyGold.contains(value)) {
							tempSet.add(key);
						}
				}
			}
			
			//if we added something new, we need to loop again so set foundNew to true
			for(String s : tempSet) {
				if(bagsThatCanContainShinyGold.add(s)) {
					foundNew = true;
				}
			}
		}
	
		
		System.out.println("total bag colors: " + bagRules.size());
		
		
		System.out.println("NumBags that have no other bags: " + hasNullValue.size());
		
		
		System.out.println("New bag count (pls dont be 327): " + bagsThatCanContainShinyGold.size());
		
	//	helper2(bagRules);//call part two
		
	}
	}

	private static void helper2( HashMap<String, HashMap<String, Integer>> bagRules) {
		HashMap<String, Integer> goldBagRules = bagRules.get("shiny gold");
		System.out.println(goldBagRules);
		int result = recursion(goldBagRules, bagRules);
		
		System.out.println("result : " + result);
		
	}
	//this part was WAY more fun and easy for me :)
	private static int recursion(HashMap<String, Integer> input, HashMap<String, HashMap<String, Integer>> bagRules) {
		int totalBags = 0;
		System.out.println("New recursion instance");
		System.out.println("input map: " + input);
		if(input == null) return 0;
		for(Map.Entry<String, Integer> entry : input.entrySet()) {
			totalBags += entry.getValue();
			System.out.println("added " + entry.getValue() + " to total");
			if(bagRules.get(entry.getKey()) != null) {
				//has other bags to look into
				System.out.println("diving into map for this bag: " + entry.getKey());
				totalBags += recursion(bagRules.get(entry.getKey()), bagRules) * entry.getValue();
			}
		}
		
		return totalBags;
	}
}

