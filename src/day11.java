import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class day11 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		File file = new File("C:\\Users\\itk78\\Documents\\AdventOfCode\\inputs\\input11.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		ArrayList<String> input = new ArrayList<String>();
		String line;
		while((line = br.readLine()) != null) {
			input.add(line);
		}
		int gridRowLength = input.get(0).length();
		
		//first be row, second be column
		// input.size() is how many lines, ie how many rows
		char[][] grid = new char[input.size()][gridRowLength];
		
		int j = 0; //row inde
		for(String s : input) {
			int i = 0; //first index of column
			for(char c : s.toCharArray()) {
				grid[j][i] = c;
				i++;
			}
			j++;
		}
		
		for(int i = 0; i < grid.length; i++) {
			char[] row = grid[i];
			for(char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
		
		part1(grid);
	}
	
	public static void part1(char[][] grid) {
		
		char[][] current = grid.clone(); //get the current iteration
		char[][] previous; //will be used to compare with the current
		
		do {
			System.out.println("new loop");
			previous = current.clone();
			for(int r = 0; r < current.length; r++) {
				for(int c = 0; c < current[r].length; c++) {
					System.out.println("getting new seat for " + r + "  " + c);
					current[r][c] = getSeat(previous, r, c);
				}
			}
		} while(!current.equals(previous));
		
		System.out.println("done?");
	}
	
	//i hate these problems because of edge cases
	public static char getSeat(char[][] input, int row, int col) {
		char EMPTY = 'L';
		char FLOOR = '.';
		char OCCUPIED = '#';
		//char result = input[row][col];//default to what it wa
		
		char currentSeat = input[row][col];
		int occupiedClose = 0;
		
		
		if(currentSeat == FLOOR) {
			System.out.println("passed in a floor");
			return FLOOR;
		}
		//find how many occupied seats near this seat
		else {
			//check all eight positions, which can be broken down into three categories: to the right, above/below, to the left
			int up;
			int down;
			int left;
			int right;
			up = row != 0 ? -1 : 0; //-1 is going "up" a row from current row
			down = row != input.length-1 ? 1 : 0;
			left = col != 0 ? -1 : 0;
			right = col != input[row].length-1 ? 1 : 0;
			
			System.out.println("UP DOWN LEFT RIGHT : " + up + " " + down + "  " + left + " " + right);
			
			char one; //directly up
			char two; //directly down
			char three; //directly right
			char four; //directly left
			
			char five; //up/right
			char six; //up/left
			char seven; //down/right
			char eight; //down/left
			
			if(up != 0) {
				
				one = input[row+up][col];
				System.out.println("checking up " + one);
				if(one == OCCUPIED) {
					occupiedClose++;
					System.out.println("occupied++");
				}
			}
			if(down != 0) {
				
				two = (char) input[row+down][col];
				System.out.println("checking down " + two);
				if(two == OCCUPIED) {
					occupiedClose++;
					System.out.println("occupied++");
				}
			}
			if(right != 0) {
				
				three = input[row][col+right];
				System.out.println("checking right " + three);
				if(three == OCCUPIED) {
					System.out.println("occupied++");
					occupiedClose++;
				}
			}
			if(left != 0) {
				
				four = input[row][col+left];
				System.out.println("checking left " + four);
				if(four == OCCUPIED) {
					System.out.println("occupied++");
					occupiedClose++;
				}
			}
			
			if(up != 0 && right != 0) {
				
				five = input[row+up][col+right];
				System.out.println("checking up/right: " + five);
				if(five == OCCUPIED) {
					occupiedClose++;
					System.out.println("occupied++");
				}
			}
			if(up != 0 && left != 0) {
				six = input[row+up][col+left];
				System.out.println("checking up/left: " + six);
				if(six == OCCUPIED) {
					occupiedClose++;
					System.out.println("occupied++");
				}
			}
			
			if(down != 0 && right != 0) {
				seven = input[row+down][col+right];
				System.out.println("checking down/right: " + seven);
				if(seven == OCCUPIED) {
					occupiedClose++;
					System.out.println("occupied++");
				}
			}
			if(down != 0 && left != 0) {
				eight = input[row+down][col+left];
				System.out.println("checking down/left: " + eight);
				if(eight == OCCUPIED) {
					occupiedClose++;
					System.out.println("occupied++");
				}
			}
			
			
		}
		System.out.println("seats occupied nearby: " + occupiedClose);
		if(currentSeat == EMPTY && occupiedClose == 0) {
			return OCCUPIED;
		}
		else if(currentSeat == OCCUPIED && occupiedClose >= 4) {
			return EMPTY;
		}
	//	System.out.println("NEW SEAT: " + result);
		return '?';
	}

}
