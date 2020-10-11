package hr.fer.zemris.java.hw01;

import org.junit.Assert;
import org.junit.Test;


/**
 * 
 * 
 * @author Mateo Martinjak
 */
public class UniqueNumbersTest {

	@Test
	public void treeSizeZero() {
		UniqueNumbers.TreeNode temp=null;
		int expected=0; 
		
		Assert.assertEquals(expected, UniqueNumbers.treeSize(temp));
	}
	@Test
	public void treeSizeSameNumber() {
		int expected=2; 
		UniqueNumbers.TreeNode temp=null;
		
		temp=UniqueNumbers.addNode(temp, 2);
		temp=UniqueNumbers.addNode(temp, 3);
		temp=UniqueNumbers.addNode(temp, 2);
		
		Assert.assertEquals(expected, UniqueNumbers.treeSize(temp));
	}
	@Test
	public void containsValueNonZero() {
		UniqueNumbers.TreeNode temp=null;
		boolean expected=true; 
		
		for(int i=-30 ;i<=30 ;i+=5) {
			temp=UniqueNumbers.addNode(temp, i);
		}
		
		Assert.assertEquals(expected, UniqueNumbers.containsValue(temp,25));
	}
	@Test
	public void containsValueEmpty() {
		UniqueNumbers.TreeNode temp=null;
		boolean expected=false; 
		
		Assert.assertEquals(expected, UniqueNumbers.containsValue(temp,0));
	}
	@Test	
	public void containsValueZero() {
		UniqueNumbers.TreeNode temp=null;
		boolean expected=true;
		
		temp=UniqueNumbers.addNode(temp, 0);
		
		Assert.assertEquals(expected, UniqueNumbers.containsValue(temp,0));
	}

	@Test	
	public void addNode() {
		UniqueNumbers.TreeNode tempNode=new UniqueNumbers.TreeNode();
		
		tempNode.value=5;
		tempNode.left=null;
		tempNode.right=null;
		
		UniqueNumbers.TreeNode emptyTree=null;
		emptyTree=UniqueNumbers.addNode(emptyTree, 5);
		Assert.assertEquals(UniqueNumbers.containsValue(tempNode,5),UniqueNumbers.containsValue(emptyTree,5) );
	}
	
}