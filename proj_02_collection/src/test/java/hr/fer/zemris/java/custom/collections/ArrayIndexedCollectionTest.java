package hr.fer.zemris.java.custom.collections;

import org.junit.Assert;
import org.junit.Test;

public class ArrayIndexedCollectionTest {



		@Test(expected=NullPointerException.class)
		public void add() {
			ArrayIndexedCollection array= new ArrayIndexedCollection();
			array.add(null);
		}
		
		@Test(expected=IndexOutOfBoundsException.class)
		public void get() {
			ArrayIndexedCollection array= new ArrayIndexedCollection();
			array.get(-6);
		}
		
		@Test 
		public void contains() {
			ArrayIndexedCollection array= new ArrayIndexedCollection();
			Assert.assertEquals(false, array.contains(null));
		}
		
		@Test 
		public void indexOf() {
			ArrayIndexedCollection array= new ArrayIndexedCollection();
			array.add("OK");
			Assert.assertEquals(0, array.indexOf("OK"));
		}

	

}
