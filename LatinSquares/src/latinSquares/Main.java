package latinSquares;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	public static char[][] puzzle1 = 
		{{'-','-','-','-','-'},
		{'-','-','-','-','-'},
		{'-','-','-','-','-'},
		{'-','-','1','-','-'},
		{'0','-','-','-','-'}};
		
	public static char[][] puzzle2 =
		{{'-','-','-','-','-','-'},
		{'-','-','-','-','-','-'},
		{'-','-','-','-','-','-'},
		{'-','-','-','-','-','-'},
		{'-','-','-','x','-','-'},
		{'-','x','-','-','-','-'}};
			
	public static char[][] puzzle3 =
		{{'-','-','-','-','-','-'},
		{'-','-','-','-','-','-'},
		{'-','-','-','x','-','-'},
		{'-','-','-','-','-','x'},
		{'-','-','-','-','-','-'},
		{'x','-','-','-','-','-'}};
	
	public static char[][] puzzle4 =
		{{'-','-','-','-','x','-','-'},
		{'-','-','-','-','-','-','-'},
		{'-','-','-','-','-','-','-'},
		{'-','-','-','-','-','-','-'},
		{'-','-','-','-','-','-','-'},
		{'-','-','-','x','-','-','-'},
		{'x','-','-','-','-','-','-'}};
	
	public static ArrayList<Position> adjacencyList = new ArrayList<Position>();
	public static ArrayList<Integer> adjacencyValue = new ArrayList<Integer>();
	
	public static void main(String []args) throws NumberFormatException, IOException {
		
		System.out.println("Please choose a puzzle 1-4:");
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
		Integer choice = Integer.parseInt( br.readLine() ); 
		switch( choice ) 
		{
			case 1:
				adjacencyList.add( new Position(4,0));
				adjacencyValue.add(0);
				adjacencyList.add( new Position(3,2));
				adjacencyValue.add(1);
				ConstraintChecker cc1 = new ConstraintChecker( adjacencyList, adjacencyValue );
				Board board1 = new Board( puzzle1, cc1 );
				board1.solve();
				break;
			case 2:
				adjacencyList.add( new Position(5,1));
				adjacencyValue.add(0);
				adjacencyList.add( new Position(4,3));
				adjacencyValue.add(2);
				ConstraintChecker cc2 = new ConstraintChecker( adjacencyList, adjacencyValue );
				Board board2 = new Board( puzzle2, cc2 );
				board2.solve();
				break;
			case 3:
				adjacencyList.add( new Position(5,0));
				adjacencyValue.add(1);
				adjacencyList.add( new Position(3,5));
				adjacencyValue.add(2);
				adjacencyList.add( new Position(2,3));
				adjacencyValue.add(1);
				ConstraintChecker cc3 = new ConstraintChecker( adjacencyList, adjacencyValue );
				Board board3 = new Board( puzzle3, cc3 );
				board3.solve();
				break;
			case 4:
				adjacencyList.add( new Position(6,0));
				adjacencyValue.add(1);
				adjacencyList.add( new Position(5,3));
				adjacencyValue.add(0);
				adjacencyList.add( new Position(0,4));
				adjacencyValue.add(2);
				ConstraintChecker cc4 = new ConstraintChecker( adjacencyList, adjacencyValue );
				Board board4 = new Board( puzzle4, cc4 );
				board4.solve();
				break;
			default:
				break;
		
		}
	}
}
