import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Pattern;

public class day4 {

	//This was done QUITE poorly but oh well 
	public static void main(String[] args) throws Exception{
		
		HashSet<String> requiredSet = new HashSet<String>();
		requiredSet.add("byr");
		requiredSet.add("iyr");
		requiredSet.add("eyr");
		requiredSet.add("hgt");
		requiredSet.add("hcl");
		requiredSet.add("ecl");
		requiredSet.add("pid");
		// not required requiredSet.add("cid");
		
		File file = new File("C:\\Users\\itk78\\Documents\\AdventOfCode\\inputs\\input4.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		HashMap<String, String> passport = new HashMap<String, String>();
		int numValid = 0;
		int numValid2 = 0;
		
		String line;
		boolean done = false;
		while((line = br.readLine()) != null) {
			System.out.println("Line: " + line);
			if(line.trim().isEmpty()) {
				//reached the end of a passport, check if it was valid
				boolean hasAllKeys = true;
				for(String inSet : requiredSet) {
					if(!passport.containsKey(inSet)) {
						hasAllKeys = false;
					}
				}
				if(hasAllKeys) {
					numValid++;
					//do more validation here for part2
					if(verifyPassportValues(passport)) {
						numValid2++;
					}
						
				}
				passport.clear(); //new passport started
			}
			else {
				//split the line by " ", then split each entry by ":" to get the keys
				String[] lineSplitBySpace = line.split(" ");
				for(String keyValuePair : lineSplitBySpace) {
					String key = keyValuePair.split(":")[0];
					String value = keyValuePair.split(":")[1];
					passport.put(key, value);
				}
			}
			
		}
		//need to process the very last passport potentially
		if(!passport.isEmpty()) {
			boolean hasAllKeys = true;
			for(String inSet : requiredSet) {
				if(!passport.containsKey(inSet)) {
					hasAllKeys = false;
				}
				
			}
			if(hasAllKeys) {
				numValid++;
				if(verifyPassportValues(passport)) {
					numValid2++;
				}
			}
			passport.clear(); //new passport started
		}
		
		System.out.println("NumValid: " + numValid);
		System.out.println("NumValid2: " + numValid2);
		
	}
	
	//this is kinda ugly
	public static boolean verifyPassportValues(HashMap<String, String> passport) {
		boolean isValidValues = true;
		for(Map.Entry<String, String> entry : passport.entrySet()) {
			String regex = null;
			switch(entry.getKey()) {
				case "byr": 
					regex = "[0-9]{4}";
					if(entry.getValue().matches(regex)) {
						if(Integer.parseInt(entry.getValue()) < 1920 || Integer.parseInt(entry.getValue()) > 2002) {
							isValidValues = false;
						}
					}
					else {
						isValidValues = false;
					}
					
					break;
				case "iyr":
					regex = "[0-9]{4}";
					if(entry.getValue().matches(regex)) {
						if(Integer.parseInt(entry.getValue()) < 2010 || Integer.parseInt(entry.getValue()) > 2020) {
							isValidValues = false;
						}
					}
					else {
						isValidValues = false;
					}
					break;
				case "eyr":
					regex = "[0-9]{4}";
					if(entry.getValue().matches(regex)) {
						if(Integer.parseInt(entry.getValue()) < 2020 || Integer.parseInt(entry.getValue()) > 2030) {
							isValidValues = false;
						}
					}
					else {
						isValidValues = false;
					}
					break;
				case "hgt":
					regex = "[0-9]+((cm)|(in))";
					if(entry.getValue().matches(regex)) {
						String cmOrIn = entry.getValue().substring(entry.getValue().length()-2, entry.getValue().length());
						if(cmOrIn.equals("cm")) {
							String numbers = entry.getValue().split("cm")[0];
							if(Integer.parseInt(numbers) < 150 || Integer.parseInt(numbers) > 193) {
								isValidValues = false;
							}
						}
						else {
							String numbers = entry.getValue().split("in")[0];
							if(Integer.parseInt(numbers) < 59 || Integer.parseInt(numbers) > 76) {
								isValidValues = false;
							}
						}
					}
					else {
						isValidValues = false;
					}
					break;
				case "hcl":
					regex = "#[0-9a-f]{6}";
					if(!entry.getValue().matches(regex)){
						isValidValues = false;
					}
					break;
				case "ecl":
					HashSet<String> set = new HashSet<String>(Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth"));
					if(!set.contains(entry.getValue())) {
						isValidValues = false;
					}
					break;
				case "pid":
					regex = "^[0-9]{9}$";
					if(!entry.getValue().matches(regex)) {
						isValidValues = false; 
					}
					break;
			}
		}
		
		return isValidValues;
}
}
