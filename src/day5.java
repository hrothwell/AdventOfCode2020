import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class day5 {
	public static void main(String[] args) throws Exception {
		
		File file = new File("D:\\Documents\\AdventOfCode2020Input\\input4.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		ArrayList<String> passes = new ArrayList<String>();
		HashMap<Integer, HashSet<Integer>> foundRows = new HashMap<Integer, HashSet<Integer>>();
		String line;
		while((line = br.readLine()) != null) {
			passes.add(line);
		}
		int maxId = 0;
		
		List<Object> results;
		for(String str : passes) {
			results = helper1(str, foundRows);
			int x = (Integer) results.get(0);
			foundRows = (HashMap<Integer, HashSet<Integer>>) results.get(1);
			System.out.println("X: " + x);
			if(x > maxId) {
				maxId = x;
				System.out.println("max id: " + maxId);
			}
			
		}
		int ourCol = 0;
		int ourRow = 0;
		for(Map.Entry<Integer, HashSet<Integer>> entry : foundRows.entrySet()) {
			System.out.println("KEY: " + entry.getKey());
			System.out.println("VALUE: " + entry.getValue());
			int prev = -1;
			for(Integer i : entry.getValue()) {
				if(prev == -1) {
					prev = i;
				}
				else {
					if(i - prev != 1) {
						//our seat is between these two
						ourCol = i -1;
						ourRow = entry.getKey();
						System.out.println("Our col/row: " + ourRow + " " + ourCol);
						break;
					}
					else {
						prev = i;
					}
				}
				
			}
		}
		
		int ourSeatId = (ourRow * 8) + ourCol;
		System.out.println("max id: " + maxId);
		System.out.println("our seat ID: " + ourSeatId);
	}
	
	public static List<Object> helper1(String s, HashMap<Integer, HashSet<Integer>> map) {
		List<Object> results = new ArrayList<Object>(); //just doing this for part two, its not very cool but eh
		int firstResult = 0;
		char F = 'F';
		char B = 'B';
		char L = 'L';
		char R = 'R';
		int rowHigh = 127;
		int rowLow = 0;
		int rowMidPoint = (rowHigh + rowLow) / 2;
		int rowMidPoint2 = rowMidPoint + 1;
		int finalRow = 0;
		
		int colHigh = 7;
		int colLow = 0;
		int colMid = (colHigh + colLow) / 2; //will floor down to lower number
		int colMid2 = colMid + 1;
		int finalCol = 0;
		
		System.out.println(s);
		String rowMoves = s.substring(0, 7);
		String colMoves = s.substring(7, s.length());
		
		for(char c : rowMoves.toCharArray()) {
			if(c == F) {
				//drop top (higher) half
				rowHigh = rowMidPoint;
				rowMidPoint = (rowHigh+rowLow) / 2;
				rowMidPoint2 = rowMidPoint + 1;
			}
			else if(c == B) {
				rowLow = rowMidPoint2;
				rowMidPoint = (rowHigh+rowLow) / 2;
				rowMidPoint2 = rowMidPoint + 1;
			}
			
			
		}
		
		finalRow = rowLow; //should be the same at this point
		
		for(char c : colMoves.toCharArray()) {
			if(c == L) {
				colHigh = colMid;
				colMid = (colHigh+colLow) / 2;
				colMid2 = colMid + 1;
			}
			else if(c == R) {
				colLow = colMid2;
				colMid = (colHigh+colLow) / 2;
				colMid2 = colMid + 1;
			}
		}
		finalCol = colLow;
		firstResult = finalRow * 8 + finalCol;
		
		//part two stuff. add the row/col to the map
		if(map.containsKey(finalRow)) {
			map.get(finalRow).add(finalCol);
		}
		else {
			HashSet<Integer> set = new HashSet<Integer>();
			set.add(finalCol);
			map.put(finalRow, set);
		}
		
		results.add(firstResult);
		results.add(map);
		return results;
	}
	
}
	
