package blackjack2;

import java.util.ArrayList;
import java.util.Arrays;

public class test {

	public static void main(String[] args) {	
		ArrayList<String> list = new ArrayList( Arrays.asList("A", "B", "C", "D"));
		list.forEach(o -> System.out.println(o));
		System.out.println("________________________");

		list.remove(0);
		list.forEach(o -> System.out.println(o));
		System.out.println(list.size());
		
		list.remove("B");
		list.forEach(o -> System.out.println(o));
	}
}
