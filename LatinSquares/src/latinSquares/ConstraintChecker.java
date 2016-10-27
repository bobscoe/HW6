package latinSquares;

import java.util.ArrayList;
import java.util.Collection;

public class ConstraintChecker {
	
	ArrayList<Position> adjacencyList;
	ArrayList<Integer> adjacencyValues;

	public ConstraintChecker( ArrayList<Position> adjacencyList, ArrayList<Integer> adjacencyValues ) {
		
		this.adjacencyList = adjacencyList;
		this.adjacencyValues = adjacencyValues;
	
	}
	
	public Variable getVariableToAssign( Node node ) {
		return applyDegreeHeuristic(node);
	}
	
	private Variable applyDegreeHeuristic(Node node){
		ArrayList<Variable> variables = applyMRVHeuristic(node.variables);
		ArrayList<Boolean> involvements = getInvolvementInAdjacencyConstraint(variables);
		if(involvements.indexOf(true)>-1)
			return variables.get(involvements.indexOf(true));
		return variables.get(0);
	}
	
	private ArrayList<Boolean> getInvolvementInAdjacencyConstraint(ArrayList<Variable> variables){
		ArrayList<Boolean> involvements = new ArrayList<Boolean>();
		ArrayList<Integer> columnsInvolved = new ArrayList<Integer>();
		for(int i=0;i<adjacencyList.size();i++){
			Position position = adjacencyList.get(i);
			columnsInvolved.add(position.column-1);
			columnsInvolved.add(position.column);
			columnsInvolved.add(position.column+1);
		}
		for (int i=0;i<variables.size();i++)
		{
			Variable variable = variables.get(i);
			if(columnsInvolved.contains(variable.id))
				involvements.add(true);
			else
				involvements.add(false);
		}
		return involvements;
	}
	
	private ArrayList<Variable> applyMRVHeuristic(ArrayList<Variable> variables){
		ArrayList<Variable> unassigned = new ArrayList<Variable>();
		int minDomain = Integer.MAX_VALUE;
		for(int i=0;i<variables.size();i++)
		{
			Variable variable = variables.get(i);
			if(variable.hasValue || variable.domain.size() > minDomain)
				continue;
			if(minDomain > variable.domain.size()){
				minDomain = variable.domain.size();
				unassigned.clear();
			}
			unassigned.add(variable);
		}
		return unassigned;
	}
	
	public Node forwardCheck( Node node ) {
		
		for( Variable var : node.variables ) { 
			if( var.hasValue ) continue;
			
			Integer size = node.state.length;
			for( int index = 0; index < size; index++ ) {

				if( node.state[index][var.id] == 'A' ) {
					System.out.println("variable assigned here at [" + index + "][" + var.id + "]");
				
					//loop to remove domains horizontally from most recent assigned
					for( int i = 0; i < size; i++ ) {
						Variable nextVar = node.variables.get(i);
						Integer val = index;
						nextVar.domain.remove(val);
					}
					
					//remove domains diagonally adjacent from recent assignment index
					node = removeDiagonalAdjacentDomains( node, var.id, index, size );
					break;
				}
			}
		}
		//adjacency constraint check here
		if(isAdjacencySatisfied(node))
			return node;
		return null;
	}
	
	private Node removeDiagonalAdjacentDomains( Node node, Integer currVarID, int index, Integer size ) {
		
			Variable leftVar = node.variables.get( Math.max( 0, currVarID-1 ) );
			Variable rightVar = node.variables.get( Math.min( currVarID+1, size ) );
			Integer diagUpIndex = index - 1;
			if( diagUpIndex >= 0 )
				leftVar.domain.remove(diagUpIndex);
				rightVar.domain.remove(diagUpIndex);
			
				Integer diagDownIndex = index + 1;
			if( diagDownIndex <= size )
				leftVar.domain.remove(diagDownIndex);
				rightVar.domain.remove(diagDownIndex);
		
		return node;
	}
	
	private boolean isAdjacencySatisfied(Node node){
		for(int i=0;i<adjacencyList.size();i++){
			Position position = adjacencyList.get(i);
			int adjacentShadedSquares = getAdjacentShadedCellsCount(node,position);
			if(adjacentShadedSquares > adjacencyValues.get(i))
				return false;
		}
		return true;
	}
	
	private int getAdjacentShadedCellsCount(Node node,Position position){
		int count = 0;
		int top = Math.max(position.row - 1,0);
		int left = Math.max(position.column - 1,0);
		int right = Math.min(position.column+1,node.state.length-1);
		int bottom = Math.min(position.row+1,node.state.length-1);
		for(int i = top; i<=bottom;i++){
			for(int j=left;j<=right;j++)
				if(node.state[i][j]== 'Q')
					count++;
		}
		return count;
	}
}
