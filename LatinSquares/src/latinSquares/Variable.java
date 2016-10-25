package latinSquares;

import java.util.ArrayList;

public class Variable {

	ArrayList<Integer> domain;
	Boolean hasValue = false;
	Integer id;
	
	public Variable( ArrayList<Integer> domain, Integer id ) {
		this.id = id;
		this.domain = domain;
	}
}
