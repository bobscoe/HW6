package latinSquares;

import java.util.ArrayList;

public class Board {

	public char[][] board;
	ConstraintChecker cc;
	private Node solution;
	private int partialAssignments = 0;
	
	public Board( char[][] board, ConstraintChecker cc ) {
		
		this.board = board;
		this.cc = cc;
		
	}
	
	public Node getSolution() throws Exception{
		if(this.solution!=null){
			replaceXWithDigits(this.solution);
			return this.solution;
		}
		throw new Exception("Solution not found");
	}
	
	public int getPartialAssignmentCount(){
		return this.partialAssignments;
	}
	
	private void replaceXWithDigits(Node node) {
		for(int i=0;i<cc.adjacencyList.size();i++){
			Position position = cc.adjacencyList.get(i);
			node.state[position.row][position.column] =  (char) (cc.adjacencyValues.get(i)+'0');
		}
		
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
		this.solution = backTrackingSearch( rootNode );
		return this.solution!=null;
	}
	
	public Node backTrackingSearch( Node node ) {
		if(isComplete(node))
			return node;
		
		Variable variable = cc.getVariableToAssign(node);
		
		Node child = new Node(deepCopy(node.state),deepCopy(node.variables),node);
		for(int i=0; i<variable.domain.size();i++)
		{
			partialAssignments++;
			Variable var = child.variables.get(variable.id);
			var.assignedValue =  variable.domain.get(i);
			var.hasValue = true;
			child.state[var.assignedValue][var.id] = 'Q';
			Node dummy = new Node(deepCopy(child.state),deepCopy(child.variables),null);
			dummy =  cc.forwardCheck(dummy);
			if(dummy!=null && cc.hasLegalValues(dummy)){
				Node result = backTrackingSearch(dummy);
				if(result != null)
					return result;
			}
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
