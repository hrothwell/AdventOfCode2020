import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import helperClasses.Command;

public class day8 {

	public static void main(String[] args) throws Exception {
		
		File file = new File("C:\\Users\\itk78\\Documents\\AdventOfCode\\inputs\\input8.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		
		ArrayList<Command> commandsList = new ArrayList<Command>();
		
		//fill input
		String line;
		while((line = br.readLine()) != null) {
			Command c = new Command();
			String[] split = line.split(" ");
			c.setName(split[0].trim());
			c.setNumber(Integer.parseInt(split[1]));
			commandsList.add(c);
		}
		br.close();
		
		System.out.println(commandsList.size());
		
		System.out.println("Accumulator size part one: " + part1(commandsList));
		
		System.out.println("Part two brute force: " + part2(commandsList));
	}
	
	public static int part1(List<Command> commandList) {
		HashSet<Integer> seenIndexes = new HashSet<Integer>();
		int accumulator = 0;
		int currentIndex = 0;
		
		while(!seenIndexes.contains(currentIndex)) {
			Command c = commandList.get(currentIndex);
			seenIndexes.add(currentIndex);
			switch(c.getName()) {
				case "acc":
					accumulator += c.getNumber();
					currentIndex++;
					break;
				case "jmp":
					currentIndex += c.getNumber();
					break;
				case "nop":
					currentIndex++;
					break;
				default:
					break;
			
			}
		}
		
		System.out.println("Index that broke the loop: " + currentIndex);
		return accumulator;
	}
	
	//only idea I have is brute force, not good probably but it works
	public static int part2(List<Command> commandList) {
		
		int accumulator = 0;
		HashSet<Integer> seenIndexes = new HashSet<Integer>();
		HashSet<Integer> jmpIndexes = new HashSet<Integer>(); //store a set of all known jmpIndexes for brute force method
		HashSet<Integer> nopIndexes = new HashSet<Integer>(); //store a set of all known nopIndexes for brute force method
		
		//fill our known jmp and nop indexes 
		for(int i = 0; i < commandList.size(); i++) {
			Command c = commandList.get(i);
			if(c.getName().equals("jmp")) {
				jmpIndexes.add(i);
			}
			else if(c.getName().equals("nop")) {
				nopIndexes.add(i);
			}
		}
		
		//que the brute force...
		for(Integer i : jmpIndexes) {
			//change the command at this index to have a nop now instead of jmp, store the original so we can change it back after
			String jmp = commandList.get(i).getName();
			commandList.get(i).setName("nop");
			
			int currentIndex = 0;
			while(!seenIndexes.contains(currentIndex)) {
				Command c = commandList.get(currentIndex);
				seenIndexes.add(currentIndex);
				switch(c.getName()) {
					case "acc":
						accumulator += c.getNumber();
						currentIndex++;
						break;
					case "jmp":
						currentIndex += c.getNumber();
						break;
					case "nop":
						currentIndex++;
						break;
					default:
						break;
				}
				if(currentIndex == commandList.size()-1) {
					//reached the last command, good thing
					System.out.println("returning after flipping index " + i);
					return accumulator;
				}
			}
			
			commandList.get(i).setName(jmp); //set the name back to jmp
			//reset for next trial
			accumulator = 0; 
			seenIndexes = new HashSet<Integer>();
		}
		
		
		for(Integer i : nopIndexes) {
			//change the command at this index to have a nop now instead of jmp, store the original so we can change it back after
			String nop = commandList.get(i).getName();
			commandList.get(i).setName("jmp");
			
			int currentIndex = 0;
			while(!seenIndexes.contains(currentIndex)) {
				Command c = commandList.get(currentIndex);
				seenIndexes.add(currentIndex);
				switch(c.getName()) {
					case "acc":
						System.out.println("increase accumulator");
						accumulator += c.getNumber();
						currentIndex++;
						break;
					case "jmp":
						currentIndex += c.getNumber();
						break;
					case "nop":
						currentIndex++;
						break;
					default:
						break;
				}
				if(currentIndex == commandList.size()-1) {
					//reached the last command, good thing
					System.out.println("returning after flipping index " + i);
					return accumulator;
				}
			}
			
			commandList.get(i).setName(nop); //set the name back to jmp
			//reset for next trial
			accumulator = 0; 
			seenIndexes = new HashSet<Integer>();
		}
		
		
		System.out.println("returning outside of loops");
		return accumulator;
	}
	
	

}
