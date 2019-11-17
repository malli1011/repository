package com.demo1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class MainClass extends AbstractClass{

	public static void main(String[] args) {
		AbstractClass ac = new MainClass();
		ac.run();
		ac.check();
		List<String> list = new ArrayList<String>();
		//list = Arrays.asList("a", "b", "c");
		list.add("a");
		list.add("b");
		list.add("c");
		Collections.reverse(list);
		Collections.sort(list);
		List<String> newList = Collections.unmodifiableList(list);
		Iterator<String> itr = list.iterator();
		while(itr.hasNext()) {
			String s = itr.next();
			System.out.println(s);
			if(s.equals("b"))
				list.remove(s);
		}
		list.forEach(l->System.out.println("List Element "+l));
		//System.out.println(list);
		FunctionalInterface fi = str -> {
			System.out.println("Inside Lambada expression");
			return str.length();
		};
		System.out.println("String Lenth:: "+fi.lengthFinder("Hello"));
		
		try {
			int a = 5/0;
			System.out.println(a);
		}
		catch( ArithmeticException e) {
			System.out.println(e);
		}
		
	}

	@Override
	public void run() {
		System.out.println("Run");		
	}


}
