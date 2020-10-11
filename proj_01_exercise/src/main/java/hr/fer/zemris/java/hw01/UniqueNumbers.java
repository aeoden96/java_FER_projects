package hr.fer.zemris.java.hw01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Program se koristi za sortiranje nasumično unesenih jedinstvenih brojeva.
 * Program ne prihvaća brojeve koji već postoje. Ispisuje u konzolu uzlazno i
 * silazno sortirane nizove.
 * 
 * @author Mateo Martinjak
 * @version 1.0
 */
public class UniqueNumbers {

	static class TreeNode {
		public int value;
		public TreeNode left;
		public TreeNode right;
	};

	/**
	 * Funkcija ispisuje u konzolu silazno i uzlazno niz brojeva. Funkcija ne vraća
	 * ništa.
	 * 
	 * @param node
	 *            node je korijen stabla u kojem su pohranjeni brojevi
	 * @param sortType
	 *            sortType je true ,ukoliko je potreban uzlazan zapis, tj. false za
	 *            silazan
	 */
	public static void printSorted(TreeNode node, boolean sortType) {
		if (node == null) {
			return;
		}
		printSorted(sortType ? node.left : node.right, sortType);
		System.out.format("%d ", node.value);
		printSorted(sortType ? node.right : node.left, sortType);
	}

	/**
	 * Funkcija provjerava dali se u stablu nalazi tražen broj
	 * 
	 * @param node
	 *            je korijen stabla u kojem su pohranjeni brojevi
	 * @param value
	 *            value je broj koji tražimo
	 * @return vraća false ako broja nema u stablu,true ako ima
	 */
	public static boolean containsValue(TreeNode node, int value) {
		if (node == null) {
			return false;
		}
		if (value == node.value) {
			return true;
		}
		if (value < node.value)
			return containsValue(node.left, value);
		if (value > node.value)
			return containsValue(node.right, value);
		return false;
	}

	/**
	 * Funkcija za provjeru veličine stabla,tj. kardinalitet skupa unešenih brojeva.
	 * 
	 * @param node
	 *            node je korijen stabla u kojem su pohranjeni brojevi
	 * @return vraća broj elemenata stabla
	 */
	public static int treeSize(TreeNode node) {
		
		
		if (node == null)
			return 0;
		else {
			return 1 + treeSize(node.left) + treeSize(node.right);
		}
	}

	/**
	 * Funkcija dodaje jedan čvor u stablo ,ignorira poziv ukoliko je čvor s istom
	 * vrijednosti već dodan
	 * 
	 * @param node
	 *            node je korijen stabla u kojem su pohranjeni brojevi
	 * @param val
	 *            vrijednost koja se želi dodati
	 * @return vraća korijen novog stabla s dodanom vrijednošću
	 */
	public static TreeNode addNode(TreeNode node, int value) {
		if (node == null) {
			TreeNode tempNode = new TreeNode();
			tempNode.value = value;
			tempNode.left = null;
			tempNode.right = null;

			return tempNode;

		} else if (value < node.value) {

			node.left = addNode(node.left, value);

		} else if (value > node.value) {

			node.right = addNode(node.right, value);

		} 
		return node;
		

	}

	/**
	 * Glavna funkcija ,obavlja konzolni upis brojeva s tipkovnice, ne čita
	 * argumente
	 * 
	 * @param args
	 *            ne čita konzolni upis
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		TreeNode root = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.format("Unesite broj > ");
			int broj;
			String redak;
			redak = reader.readLine();
			if (redak == null) {
				System.exit(1);
			}
			if (redak.equals("kraj")) {
				System.out.format("Ispis od najmanjeg: ");
				printSorted(root, true);
				System.out.format("\nIspis od najveceg: ");
				printSorted(root, false);
				break;
			}
			try {
				broj = Integer.parseInt(redak);
			} catch (NumberFormatException ex) {
				System.out.println("'" + redak + "' nije cijeli broj.");
				continue;
			}
			
			if (containsValue(root, broj)) {
				System.out.println("Broj već postoji. Preskačem.");
			} else {
				root = addNode(root, broj);
				System.out.println("Dodano.");
			}

		}
		reader.close();
	}

}
