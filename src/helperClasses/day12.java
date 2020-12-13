package helperClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class day12 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		File file = new File("D:\\Documents\\AdventOfCode2020Input\\input12.txt");
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
		int shipCurrentDirection = 1; //index in our direction list
		int wayPointDirection = 1; //position relative to ship
		char[] directions = {'N', 'E', 'S', 'W'}; 
		int shipEastWest = 0; //east is positive, west is negative
		int shipNorthSouth = 0;//north is positive, south is negative
		int turns = 0; //gonna be used  to see how many times to turn
		
		//these are relative to the ship
		double wayPointNorthSouth = 1;
		double wayPointEastWest = 10; 
		
		double newWayPointNorthSouth = 0;
		double newWayPointEastWest = 0;
		
		//x' = xcos(theta) - y sin(theta)
		//y' = ycos(theta) + xsin(theta)
		//if going Left, use given angle. if going right negate
		
		for(String s : input) {
			char c = s.charAt(0);
			int num = Integer.parseInt(s.substring(1));
			
			switch(c) {
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
				newWayPointNorthSouth = (wayPointNorthSouth * Math.cos(Math.toRadians(num))) + (wayPointEastWest * Math.sin(Math.toRadians(num)));
				newWayPointEastWest = (wayPointEastWest * Math.cos(Math.toRadians(num))) - (wayPointNorthSouth * Math.sin(Math.toRadians(num)));
				wayPointNorthSouth = newWayPointNorthSouth;
				wayPointEastWest = newWayPointEastWest;
				break;
			case 'R':
				newWayPointNorthSouth = (wayPointNorthSouth * Math.cos(Math.toRadians(360-num))) + (wayPointEastWest * Math.sin(Math.toRadians(360-num)));
				newWayPointEastWest = (wayPointEastWest * Math.cos(Math.toRadians(360-num))) - (wayPointNorthSouth * Math.sin(Math.toRadians(360-num)));
				wayPointNorthSouth = newWayPointNorthSouth;
				wayPointEastWest = newWayPointEastWest;
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
