package latinSquares;

import java.util.ArrayList;

public class Variable implements Cloneable{

	ArrayList<Integer> domain;
	Boolean hasValue = false;
	Integer id;
	public int assignedValue = -1;
	
	public Variable( ArrayList<Integer> domain, Integer id ) {
		this.id = id;
		this.domain = domain;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
}
