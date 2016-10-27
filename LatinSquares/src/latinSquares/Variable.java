package latinSquares;

import java.util.ArrayList;

public class Variable implements Cloneable{

	public ArrayList<Integer> domain;
	public Boolean hasValue = false;
	public Integer id;
	public Integer assignedValue = -1;
	
	public Variable( ArrayList<Integer> domain, Integer id ) {
		this.id = id;
		this.domain = domain;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		ArrayList<Integer> domainclone = new ArrayList<Integer>();
		for(Integer value : domain)
			domainclone.add(new Integer(value.intValue()));
		Variable var =  new Variable(domainclone,new Integer(this.id.intValue()));
		var.hasValue = this.hasValue.booleanValue();
		var.assignedValue = this.assignedValue.intValue();
		return var;
	}
}
