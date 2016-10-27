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
			
			if( !var.hasValue ) continue;
			
			for(Variable v:node.variables){
				if(v.hasValue)
					continue;
				v.domain.remove(var.assignedValue);
			}
			
			removeDiagonalAdjacentDomains(node, var);
		}
		
		if(isAdjacencySatisfied(node))
			return node;
		return null;
	}
	
	private void removeDiagonalAdjacentDomains( Node node,Variable currentVar ) {
			int size = node.state.length;
			Variable leftVar = node.variables.get( Math.max( 0, currentVar.id-1 ) );
			Variable rightVar = node.variables.get( Math.min( currentVar.id+1, size-1 ) );
			Integer diagUpIndex = new Integer(currentVar.assignedValue - 1);
			Integer diagDownIndex = new Integer(currentVar.assignedValue + 1);
			
			if(!leftVar.hasValue){
				leftVar.domain.remove(diagUpIndex);
				leftVar.domain.remove(diagDownIndex);
			}
			
			if(!rightVar.hasValue){
				rightVar.domain.remove(diagUpIndex);
				rightVar.domain.remove(diagDownIndex);
			}
	}
	
	private boolean isAdjacencySatisfied(Node node){
		for(int i=0;i<adjacencyList.size();i++){
			Position position = adjacencyList.get(i);
			int adjacentShadedSquares = getAdjacentShadedCellsCount(node,position);
			if(adjacentShadedSquares > adjacencyValues.get(i))
				return false;
			if(adjacentShadedSquares < adjacencyValues.get(i)){
				for(int j=position.column-1;j<=position.column+1;j++){
					if(j<0 || j>node.state.length-1)
						continue;
					Variable adjacentVar = node.variables.get(j);
					if(adjacentVar!=null && !adjacentVar.hasValue)
						return true;
				}
				return false;
			}else
				reduceDomainThroughAdjacency(node,position);
		}
		return true;
	}
	
	private void reduceDomainThroughAdjacency(Node node, Position position) {
		int top = Math.max(position.row - 1,0);
		int left = Math.max(position.column - 1,0);
		int right = Math.min(position.column+1,node.state.length-1);
		int bottom = Math.min(position.row+1,node.state.length-1);

		for(int j=left;j<=right;j++){
			Variable var = node.variables.get(j);
			if(!var.hasValue){
				for(int i = top; i<=bottom;i++){
					var.domain.remove(new Integer(i));
				}
			}
		}
		
	}
	
	public boolean hasLegalValues(Node node){
		for(Variable var:node.variables)
			if(!var.hasValue && var.domain.size() == 0)
				return false;
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
