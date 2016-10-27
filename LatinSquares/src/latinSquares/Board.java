package latinSquares;

import java.util.ArrayList;

public class Board {

	public char[][] board;
	ConstraintChecker cc;
	
	public Board( char[][] board, ConstraintChecker cc ) {
		
		this.board = board;
		this.cc = cc;
		
	}
	
	public boolean solve() {
		//init domain for variables
		Integer size = board.length;
		ArrayList<Variable> variables = new ArrayList<Variable>();
		for( int i = 0; i < size; i++ ) {
			ArrayList<Integer> domain = new ArrayList<Integer>();
			for( int j = 0; j < size; j++) {
				if( board[j][i] != 'x' ) {
					domain.add(j);
				}
			}
			variables.add( new Variable( domain, i ) );
		}
		
		Node rootNode = new Node( this.board, variables, null );
		Node solution = backTrackingSearch( rootNode );
		return false;
	}
	
	public Node backTrackingSearch( Node node ) {
		if(isComplete(node))
			return node;
		
		Variable variable = cc.getVariableToAssign(node);
		
		Node child = new Node(deepCopy(node.state),deepCopy(node.variables),node);
		for(int i=0; i<variable.domain.size();i++)
		{
			Variable var = child.variables.get(variable.id);
			var.assignedValue =  variable.domain.get(i);
			var.hasValue = true;
			child.state[var.assignedValue][var.id] = 'Q';
			Node result = backTrackingSearch(child);
			if(result != null)
				return result;
			child.state[var.assignedValue][var.id] = '-';
		}
		return null;
	}
	
	private boolean isComplete(Node node){
		for(int i=0;i<node.variables.size();i++){
			Variable variable= node.variables.get(i);
			if(!variable.hasValue)
				return false;
		}
		return true;
	}
	
	private ArrayList<Variable> deepCopy(ArrayList<Variable> variables){
		ArrayList<Variable> clone = new ArrayList<Variable>();
		for(Variable var:variables)
			try {
				clone.add((Variable)var.clone());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		return clone;
	}
	
	private char[][] deepCopy(char[][] state){
		int size = state.length;
		char[][] copy = new char[size][size];
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)
				copy[i][j] = state[i][j];
		return copy;
	}
	
}
