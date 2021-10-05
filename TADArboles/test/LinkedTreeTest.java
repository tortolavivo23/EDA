import junit.framework.TestCase;
import material.Position;

/**
 *
 * @author mayte
 */
public class LinkedTreeTest extends TestCase {
	private LinkedTree<String> tree = new LinkedTree<>();
	public LinkedTreeTest(){}



	public void setTree() {

		Position<String> p = tree.addRoot("A");
		tree.add("B", p);
		Position<String> p1 = tree.add("C", p);
		tree.add("D", p);

		tree.add("E", p1);
		Position<String> p2 = tree.add("F", p1);

		tree.add("G", p2);
		Position<String> p3 = tree.add("H", p2);

		tree.add("I", p3);
		tree.add("J", p3);
		tree.add("K", p3);
		tree.add("L", p3);
	}

	public void testSize() {
		Position<String> p = tree.addRoot("+");
		tree.add("2", p);
		Position<String> h = tree.add("*", p);
		tree.add("3", h);
		tree.add("5", h);
		assertEquals(tree.size(), 5);
	}

	public void testSize2() {
		this.setTree();
		assertEquals(tree.size(), 12);
	}

	public void testRoot() {
		this.setTree();
		assertEquals(tree.root().getElement(), "A");

	}

	public void testIsEmpty() {
		assertEquals(tree.isEmpty(), true);
	}

	public void testIsEmpty2() {
		Position<String> p = tree.addRoot("B");
		tree.add("C", p);
		assertEquals(tree.isEmpty(), false);
	}

	public void testParent2() {
		Position<String> p = tree.addRoot("A");
		tree.add("B", p);
		Position<String> p1 = tree.add("C", p);
		tree.add("D", p);
		tree.add("E", p1);
		Position<String> p2 = tree.add("F", p1);
		tree.add("G", p2);
		Position<String> p3 = tree.add("H", p2);
		tree.add("I", p3);
		tree.add("J", p3);
		tree.add("K", p3);
		tree.add("L", p3);
		assertEquals(p2, tree.parent(p3));
	}

	public void testParent3() {
		this.setTree();

		try {
			tree.parent(null);
		} catch (RuntimeException e) {
			assertTrue(true);
		}
	}

	public void testPositions() {
		Position<String> p = tree.addRoot("+");
		tree.add("2", p);
		tree.add("3", p);
		String salida = "";
		for (Position<String> e : tree) {
			salida += e.getElement();
		}
		assertEquals(salida, "+23");
	}

	public void testRemove() {
		Position<String> p =tree.addRoot("+");
		Position<String> q = tree.add("2", p);
		Position<String> h = tree.add("*", p);
		tree.add("3", h);
		tree.add("5", h);
		tree.remove(h);
		assertEquals(tree.size(), 2);

	}

	public void testAddN() {
		Position<String> p = tree.addRoot("R");
		tree.add("A", p);
		tree.add("C", p);
		tree.add("B", p, 1);
                Iterable<? extends Position<String>> children = tree.children(p);
		String output = "";
                for (Position<String> child : children)
                    output += child.getElement();
                assertEquals(output, "ABC");

	}

        
        public void testRemove2() {
		this.setTree();
		tree.remove(tree.root());
		assertEquals(tree.size(), 0);
	}

	public void testRemove3() {
		Position<String> p = tree.addRoot("A");
		tree.add("B", p);
		Position<String> p1 = tree.add("C", p);
		tree.add("D", p);
		tree.add("E", p1);
		Position<String> p2 = tree.add("F", p1);
		tree.add("G", p2);
		Position<String> p3 = tree.add("H", p2);
		tree.add("I", p3);
		tree.add("J", p3);
		tree.add("K", p3);
		tree.add("L", p3);

		tree.remove(p2);

		StringBuilder s = new StringBuilder();
		for (Position<String> pos : tree) {
			s.append(pos.getElement());
		}
		assertEquals(s.toString(), "ABCDE");
	}

	public void testGetUnmodifiableChildren() {
		Position<String> p = tree.addRoot("+");
		tree.add("2", p);
		tree.add("3", p);
		Iterable<? extends Position<String>> l = tree.children(p);
		try {
			l.iterator().remove();
			fail("The children collection has been modified");
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	public void testGetChildren() {
		Position<String> p = tree.addRoot("+");
		tree.add("2", p);
		tree.add("3", p);

		String salida = "";
		for (Position<String> e : tree.children(p)) {
			salida += e.getElement();
		}
		assertEquals(salida, "23");
	}

	public void testGetChildren2() {
		Position<String> p = tree.addRoot("A");
		tree.add("B", p);
		Position<String> p1 = tree.add("C", p);
		tree.add("D", p);
		tree.add("E", p1);
		Position<String> p2 = tree.add("F", p1);
		tree.add("G", p2);
		Position<String> p3 = tree.add("H", p2);
		tree.add("I", p3);
		tree.add("J", p3);
		tree.add("K", p3);
		tree.add("L", p3);

		String salida = "";
		for (Position<String> e : tree.children(p3)) {
			salida += e.getElement();
		}
		assertEquals(salida, "IJKL");
	}

	public void testIterator() {
		this.setTree();

		StringBuilder s = new StringBuilder();
		for (Position<String> pos : tree) {
			s.append(pos.getElement());
		}
		assertEquals(s.toString(), "ABCDEFGHIJKL");
	}

	public void testIsRoot() {
		this.setTree();
		assertEquals(tree.root().getElement(), "A");

	}

	public void testIsRoot2() {
		try {
			tree.isRoot(null);
		} catch (RuntimeException e) {
			assertTrue(true);
		}
	}
	
	public void testSwapElements() {
		Position<String> p = tree.addRoot("A");
		tree.add("B", p);
		Position<String> p1 = tree.add("C", p);
		tree.add("D", p);
		tree.add("E", p1);
		Position<String> p2 = tree.add("F", p1);
		tree.add("G", p2);
		Position<String> p3 = tree.add("H", p2);
		tree.add("I", p3);
		tree.add("J", p3);
		tree.add("K", p3);
		tree.add("L", p3);

		tree.swapElements(p, p1);
		tree.swapElements(p2, p3);
		
		String salida = "";
		for (Position<String> e : tree) {
			salida += e.getElement();
		}
		assertEquals(salida, "CBADEHGFIJKL");
	}

	
	public void testReplace() {
		Position<String> p = tree.addRoot("A");
		tree.add("B", p);
		Position<String> p1 = tree.add("C", p);
		tree.add("D", p);
		tree.add("E", p1);
		Position<String> p2 = tree.add("F", p1);
		tree.add("G", p2);
		Position<String> p3 = tree.add("H", p2);
		tree.add("I", p3);
		tree.add("J", p3);
		tree.add("K", p3);
		tree.add("L", p3);

		tree.replace(p, "X");
		tree.replace(p1, "Y");
		tree.replace(p2, "Z");
		tree.replace(p3, "W");
		
		String salida = "";
		for (Position<String> e : tree) {
			salida += e.getElement();
		}
		assertEquals(salida, "XBYDEZGWIJKL");
	}
}