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
		{'-','-','x','-','-'},
		{'x','-','-','-','-'}};
		
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
		Board board= null;
		switch( choice ) 
		{
			case 1:
				adjacencyList.add( new Position(4,0));
				adjacencyValue.add(0);
				adjacencyList.add( new Position(3,2));
				adjacencyValue.add(1);
				ConstraintChecker cc1 = new ConstraintChecker( adjacencyList, adjacencyValue );
				board = new Board( puzzle1, cc1 );
				break;
			case 2:
				adjacencyList.add( new Position(5,1));
				adjacencyValue.add(0);
				adjacencyList.add( new Position(4,3));
				adjacencyValue.add(2);
				ConstraintChecker cc2 = new ConstraintChecker( adjacencyList, adjacencyValue );
				board = new Board( puzzle2, cc2 );
				break;
			case 3:
				adjacencyList.add( new Position(5,0));
				adjacencyValue.add(1);
				adjacencyList.add( new Position(3,5));
				adjacencyValue.add(2);
				adjacencyList.add( new Position(2,3));
				adjacencyValue.add(1);
				ConstraintChecker cc3 = new ConstraintChecker( adjacencyList, adjacencyValue );
				board = new Board( puzzle3, cc3 );
				break;
			case 4:
				adjacencyList.add( new Position(6,0));
				adjacencyValue.add(1);
				adjacencyList.add( new Position(5,3));
				adjacencyValue.add(0);
				adjacencyList.add( new Position(0,4));
				adjacencyValue.add(2);
				ConstraintChecker cc4 = new ConstraintChecker( adjacencyList, adjacencyValue );
				board = new Board( puzzle4, cc4 );
				break;
			default:
				break;
		
		}
		if(board!=null){
			long startTime = System.currentTimeMillis();
			board.solve();
			try{
				Node solution = board.getSolution();
				for(int i=0;i<solution.state.length;i++){
					for(int j=0;j<solution.state.length;j++)
						System.out.print(solution.state[i][j]+"\t");
					System.out.println("\n");
				}
				System.out.println("Number of partial assignments examined (including solution node): "+board.getPartialAssignmentCount());
				System.out.println("Total execution time in milli seconds: "+(System.currentTimeMillis()-startTime));
			}catch(Exception ex){
				System.out.println(ex.getMessage());
			}
			
		}
			
	}
}
