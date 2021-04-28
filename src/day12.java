

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class day12 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		File file = new File("C:\\Users\\itk78\\Documents\\AdventOfCode\\inputs\\input12.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		ArrayList<String> input = new ArrayList<String>();
		
		String line;
		while((line = br.readLine()) != null) {
			input.add(line);
		}
		part1(input);
		part2(input);
	}
	
	public static void part1(List<String> input) {
		int currentDirection = 1; //index in our direction list
		char[] directions = {'N', 'E', 'S', 'W'}; 
		int eastWest = 0; //east is positive, west is negative
		int northSouth = 0;//north is positive, south is negative
		int turns = 0; //gonna be used  to see how many times to turn
		
		for(String s : input) {
			char c = s.charAt(0);
			int num = Integer.parseInt(s.substring(1));
			
			switch(c) {
			case 'F':
				if(currentDirection == 1) {
					eastWest += num;
				}
				else if(currentDirection == 3) {
					eastWest -= num;
				}
				else if(currentDirection == 0) {
					northSouth += num;
				}
				else {
					northSouth -= num;
				}
				break;
			case 'E':
				eastWest += num;
				break;
			case 'W':
				eastWest -= num;
				break;
			case 'N':
				northSouth += num;
				break;
			case 'S':
				northSouth -= num;
				break;
			case 'L':
				turns = num / 90;
				currentDirection -= turns;
				if(currentDirection < 0) {
					currentDirection = directions.length + currentDirection;
				}
				currentDirection = currentDirection % directions.length;
				break;
			case 'R':
				turns = num / 90;
				currentDirection += turns;
				currentDirection = currentDirection % directions.length;
				break;
			default:
				break;
				
			}
		}
		
		System.out.println("EastWest: " + eastWest);
		System.out.println("NorthSouth: " + northSouth);
	}
	
	//not working yet
	public static void part2(List<String> input) {
		
		int shipEastWest = 0; //east is positive, west is negative
		int shipNorthSouth = 0;//north is positive, south is negative
		
		//these are relative to the ship. The ship can be thought of as the origin (0,0)
		double wayPointNorthSouth = 1;
		double wayPointEastWest = 10; 
		
		//used for when we calculate the new waypoint coordinates
		double newWayPointNorthSouth = 0;
		double newWayPointEastWest = 0;
		
		//x' = xcos(theta) - y sin(theta)
		//y' = ycos(theta) + xsin(theta)
		//if going Left, use given angle. if going right, use 360-angle
		
		for(String s : input) {
			char c = s.charAt(0);
			int num = Integer.parseInt(s.substring(1));
			
			switch(c) {
			//move to the waypoint num times. 
			case 'F':
				shipNorthSouth += (wayPointNorthSouth * num);
				shipEastWest += (wayPointEastWest * num);
				break;
			case 'E':
				wayPointEastWest += num;
				break;
			case 'W':
				wayPointEastWest -= num;
				break;
			case 'N':
				wayPointNorthSouth += num;
				break;
			case 'S':
				wayPointNorthSouth -= num;
				break;
			case 'L':
				newWayPointNorthSouth = Math.rint((wayPointNorthSouth * Math.cos(Math.toRadians(num))) + (wayPointEastWest * Math.sin(Math.toRadians(num))));
				newWayPointEastWest = Math.rint((wayPointEastWest * Math.cos(Math.toRadians(num))) - (wayPointNorthSouth * Math.sin(Math.toRadians(num))));
				wayPointNorthSouth = newWayPointNorthSouth;
				wayPointEastWest = newWayPointEastWest;
				System.out.println("New north/south waypoint: " + newWayPointNorthSouth);
				System.out.println("New east/west waypoint: " + newWayPointEastWest);
				break;
			case 'R':
				newWayPointNorthSouth = Math.rint((wayPointNorthSouth * Math.cos(Math.toRadians(360-num))) + (wayPointEastWest * Math.sin(Math.toRadians(360-num))));
				newWayPointEastWest = Math.rint((wayPointEastWest * Math.cos(Math.toRadians(360-num))) - (wayPointNorthSouth * Math.sin(Math.toRadians(360-num))));
				wayPointNorthSouth = newWayPointNorthSouth;
				wayPointEastWest = newWayPointEastWest;
				System.out.println("New north/south waypoint: " + newWayPointNorthSouth);
				System.out.println("New east/west waypoint: " + newWayPointEastWest);
				break;
			default:
				break;
				
			}
		}
		
		System.out.println("EastWest: " + shipEastWest);
		System.out.println("NorthSouth: " + shipNorthSouth);
		shipEastWest = Math.abs(shipEastWest);
		shipNorthSouth = Math.abs(shipNorthSouth);
		int result = shipEastWest + shipNorthSouth;
		System.out.println("Manhattan distance: " + result);
	}

}
