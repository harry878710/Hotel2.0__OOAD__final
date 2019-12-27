package testing;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TestSet {

	public static void main(String[] args) {
		Set<Integer> set=new HashSet<Integer>(); 
		set.add(2);
		set.add(1);
		set.add(2);
		set.add(3);
		Iterator<Integer> it= set.iterator();
		  
		while(it.hasNext()) {  
		   Integer i = it.next();  
		   System.out.println(i);
		}    

	}

}
