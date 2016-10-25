package latinSquares;

import java.util.ArrayList;

public class Node {
	
	public Node parentNode;
	public char[][] state;
	public ArrayList<Variable> variables;

	public Node( char[][] state, ArrayList<Variable> variables, Node parentNode ) {
		
		this.state = state;
		this.variables = variables;
	
	}
	

}
