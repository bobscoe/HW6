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
			variables.add( new Variable( domain ) );
		}
		
		Node rootNode = new Node( this.board, variables, null );
		Node solution = backTrackingSearch( rootNode );
		
		
		return false;
	}
	
	public Node backTrackingSearch( Node root ) {
		
		
		return null;
	}
}
