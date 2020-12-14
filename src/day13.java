import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class day13 {

	public static void main(String[] args) throws Exception{
		File file = new File("C:\\Users\\itk78\\Documents\\AdventOfCode\\inputs\\input13.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		Integer time = 0;
		ArrayList<String> input = new ArrayList<String>();
		
		//todays input is only two lines so just do them separate
		time = Integer.parseInt(br.readLine());
		
		String[] cycles = br.readLine().split(",");
		for(String s : cycles) {
			input.add(s);
		}
		
		System.out.println(time);
		part1(time, input);
	}
	
	public static void part1(Integer time, List<String> input) {
		int lowestResult = Integer.MAX_VALUE; 
		int id = 0;
		int waitTime = 0;
		for(String s : input) {
			
			if(!s.equals("x")) {
				int num = Integer.parseInt(s);
				Integer mod = time % num; //how many minutes BEFORE this bus could be here
				
				mod = num - mod;
				
				Integer totalTime = time + mod;
				if(totalTime < lowestResult) {
					System.out.println("new lowest time: " + totalTime);
					lowestResult = totalTime;
					waitTime = mod;
					id = Integer.parseInt(s);
				}
				
			}
		}
		System.out.println(lowestResult);
		System.out.println(id);
		System.out.println(waitTime);
		int x = time % id;
		System.out.println("time % id = " + x);
	}
}
