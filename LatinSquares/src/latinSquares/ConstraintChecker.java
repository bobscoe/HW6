package latinSquares;

import java.util.ArrayList;

public class ConstraintChecker {
	
	ArrayList<Position> adjacencyList;
	ArrayList<Integer> adjacencyValues;

	public ConstraintChecker( ArrayList<Position> adjacencyList, ArrayList<Integer> adjacencyValues ) {
		
		this.adjacencyList = adjacencyList;
		this.adjacencyValues = adjacencyValues;
	
	}
	
	//uses mrv and degree heuristic to pick variable
	public Variable getVariableToAssign( Node node ) {
		
		
		return null;
	}
	
	public Node forwardCheck( Node node ) {
		
		return null;
	}
}
