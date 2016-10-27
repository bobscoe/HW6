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
		return null;
	}
}
