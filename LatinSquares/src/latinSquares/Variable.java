package latinSquares;

import java.util.ArrayList;

public class Variable implements Cloneable{

	public ArrayList<Integer> domain;
	public Boolean hasValue = false;
	public Integer id;
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
