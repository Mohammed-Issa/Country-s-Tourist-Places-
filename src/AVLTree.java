
public class AVLTree {
	private AVLTreeNode root;

	/**
	 * @return the root
	 */
	public AVLTreeNode getRoot() {
		return root;
	}

	/* Constructor */
	public AVLTree() {
		root = null;
	}

	/* Function to check if tree is empty */
	public boolean isEmpty() {
		return root == null;
	}

	/* Make the tree logically empty */
	public void makeEmpty() {
		root = null;
	}

	// function to get the height of the tree
	public int calculateHeight() {
		return height(this.root);
	}

	/* Function to get height of node */
	private int height(AVLTreeNode t) {
		if (t == null)
			return -1;
		return t.getHeight();
	}

	/* Function to max of left/right node */
	private int max(int lhs, int rhs) {
		if (lhs >= rhs)
			return lhs;
		return rhs;
	}

	/* Function to insert element */
	public void insert(Country element) {
		root = insert(element, root);
	}

	/* Function to insert element recursively */
	private AVLTreeNode insert(Country x, AVLTreeNode t) {
		if (t == null)
			t = new AVLTreeNode(x);
		else if (compare(x.getName(), t.getElement().getName()) < 0) {
			t.setLeft(insert(x, t.getLeft()));
			if (height(t.getLeft()) - height(t.getRight()) == 2)
				if (compare(x.getName(), t.getLeft().getElement().getName()) < 0)
					t = rotateWithLeftChild(t);
				else
					t = doubleWithLeftChild(t);
		} else if (compare(x.getName(), t.getElement().getName()) > 0) {
			t.setRight(insert(x, t.getRight()));
			if (height(t.getRight()) - height(t.getLeft()) == 2)
				if (compare(x.getName(), t.getRight().getElement().getName()) > 0)
					t = rotateWithRightChild(t);
				else
					t = doubleWithRightChild(t);
		} else
			; // Duplicate; do nothing
		t.setHeight(max(height(t.getLeft()), height(t.getRight())) + 1);
		return t;
	}

	private int compare(String s1, String s2) {
		return s1.compareToIgnoreCase(s2);
	}

	/* Rotate binary tree node with left child */
	private AVLTreeNode rotateWithLeftChild(AVLTreeNode k2) {
		AVLTreeNode k1 = k2.getLeft();
		k2.setLeft(k1.getRight());
		k1.setRight(k2);
		k2.setHeight(max(height(k2.getLeft()), height(k2.getRight())) + 1);
		k1.setHeight(max(height(k1.getLeft()), k2.getHeight()) + 1);
		return k1;
	}

	/* Rotate binary tree node with right child */
	private AVLTreeNode rotateWithRightChild(AVLTreeNode k1) {
		AVLTreeNode k2 = k1.getRight();
		k1.setRight(k2.getLeft());
		k2.setLeft(k1);
		k1.setHeight(max(height(k1.getLeft()), height(k1.getRight())) + 1);
		k2.setHeight(max(height(k2.getRight()), k1.getHeight()) + 1);
		return k2;
	}

	/**
	 * Double rotate binary tree node: first left child with its right child; then
	 * node k3 with new left child
	 */
	private AVLTreeNode doubleWithLeftChild(AVLTreeNode k3) {
		k3.setLeft(rotateWithRightChild(k3.getLeft()));
		return rotateWithLeftChild(k3);
	}

	/**
	 * Double rotate binary tree node: first right child with its left child; then
	 * node k1 with new right child
	 */
	private AVLTreeNode doubleWithRightChild(AVLTreeNode k1) {
		k1.setRight(rotateWithLeftChild(k1.getRight()));
		return rotateWithRightChild(k1);
	}

	/* Functions to count number of nodes */
	public int countNodes() {
		return countNodes(root);
	}

	private int countNodes(AVLTreeNode r) {
		if (r == null)
			return 0;
		else {
			int l = 1;
			l += countNodes(r.getLeft());
			l += countNodes(r.getRight());
			return l;
		}
	}

	/* Functions to search for an element */
	public boolean search(String val) {
		return search(root, val);
	}

	private boolean search(AVLTreeNode r, String val) {
		boolean found = false;
		while ((r != null) && !found) {
			String rval = r.getElement().getName();
			if (compare(val, rval) < 0)
				r = r.getLeft();
			else if (compare(val, rval) > 0)
				r = r.getRight();
			else {
				found = true;
				break;

			}
			found = search(r, val);
		}
		return found;
	}

	// find a Country in AVL and return it
	public Country get(String val) {
		return get(root, val);
	}

	// find a Country in AVL and return it
	private Country get(AVLTreeNode r, String val) {

		while (r != null) {
			String rval = r.getElement().getName();
			if (compare(val, rval) < 0)
				r = r.getLeft();
			else if (compare(val, rval) > 0)
				r = r.getRight();
			else {
				return r.getElement();

			}
			search(r, val);
		}

		return null;

	}

	// remove a country from AVL Tree
	public void remove(String x) {

		root = remove(x, root);

	}

	// remove a node from AVL Tree
	public AVLTreeNode remove(String x, AVLTreeNode t) {

		if (x.compareTo(t.getElement().getName()) < 0) {
			t.setLeft(remove(x, t.getLeft()));

			int l = t.getLeft() != null ? t.getLeft().getHeight() : 0;

			if ((t.getRight() != null) && (t.getRight().getHeight() - l >= 2)) {

				int rightHeight = t.getRight().getRight() != null ? t.getRight().getRight().getHeight() : 0;

				int leftHeight = t.getRight().getLeft() != null ? t.getRight().getLeft().getHeight() : 0;

				if (rightHeight >= leftHeight)

					t = rotateWithLeftChild(t);

				else

					t = doubleWithRightChild(t);

			}

		}

		else if (x.compareTo(t.getElement().getName()) > 0) {

			t.setRight(remove(x, t.getRight()));

			int r = t.getRight() != null ? t.getRight().getHeight() : 0;

			if ((t.getLeft() != null) && (t.getLeft().getHeight() - r >= 2)) {

				int leftHeight = t.getLeft().getLeft() != null ? t.getLeft().getLeft().getHeight() : 0;

				int rightHeight = t.getLeft().getRight() != null ? t.getLeft().getRight().getHeight() : 0;

				if (leftHeight >= rightHeight)

					t = rotateWithRightChild(t);

				else

					t = doubleWithLeftChild(t);

			}

		}

		else if (t.getLeft() != null) {

			t.setElement(findMax(t.getLeft()).getElement());

			remove(t.getElement().getName(), t.getLeft());

			if ((t.getRight() != null) && (t.getRight().getHeight() - t.getLeft().getHeight() >= 2)) {

				int rightHeight = t.getRight().getRight() != null ? t.getRight().getRight().getHeight() : 0;

				int leftHeight = t.getRight().getLeft() != null ? t.getRight().getLeft().getHeight() : 0;

				if (rightHeight >= leftHeight)

					t = rotateWithLeftChild(t);

				else

					t = doubleWithRightChild(t);

			}

		}

		else
			t = (t.getLeft() != null) ? t.getLeft() : t.getRight();

		if (t != null) {

			int leftHeight = t.getLeft() != null ? t.getLeft().getHeight() : 0;

			int rightHeight = t.getRight() != null ? t.getRight().getHeight() : 0;

			t.setHeight(Math.max(leftHeight, rightHeight) + 1);

		}

		return t;

	}

	/* Function for inOrder traversal */
	public void inorder() {
		inorder(root);
		System.out.print("\n");
	}

	private void inorder(AVLTreeNode r) {
		if (r != null) {
			inorder(r.getLeft());
			System.out.println(r.getElement().getName() + "( ");
			r.getElement().printCities();
			System.out.println(" )");
			inorder(r.getRight());
		}
	}

	public String PrintInOrder(AVLTreeNode r) {
		String s = "";
		if (r != null) {
			inorder(r.getLeft());
			s += r.getElement().getName() + "( ";
			r.getElement().citiesPrint();
			s += " )";
			inorder(r.getRight());
		}
		return s;
	}

	// return the node with the max value
	public AVLTreeNode findMax(AVLTreeNode node) {
		AVLTreeNode current = node;

		/* loop down to find the right Most leaf */
		while (current.getRight() != null)
			current = current.getRight();

		return current;
	}

	/* Function for preOrder traversal */
	public void preorder() {
		preorder(root);
		System.out.print("\n");
	}

	private void preorder(AVLTreeNode r) {
		if (r != null) {
			System.out.println(r.getElement().getName() + "( ");
			r.getElement().getCities().printList();
			System.out.println(" )");
			preorder(r.getLeft());
			preorder(r.getRight());
		}
	}

	/* Function for postOrder traversal */
	public void postorder() {
		postorder(root);
		System.out.print("\n");
	}

	private void postorder(AVLTreeNode r) {
		if (r != null) {
			postorder(r.getLeft());
			postorder(r.getRight());
			System.out.println(r.getElement().getName() + "( ");
			r.getElement().getCities().printList();
			System.out.println(" )");
		}
	}
}

// Class Node for the AVL Tree
class AVLTreeNode {
	private Country element; // store data
	private AVLTreeNode left; // left child
	private AVLTreeNode right; // right child
	private int height; // Height

	// returns element
	public Country getElement() {
		return element;
	}

	// element to set
	public void setElement(Country element) {
		this.element = element;
	}

	// return left
	public AVLTreeNode getLeft() {
		return left;
	}

	// left to set
	public void setLeft(AVLTreeNode left) {
		this.left = left;
	}

	// return height
	public AVLTreeNode getRight() {
		return right;
	}

	// right to set
	public void setRight(AVLTreeNode right) {
		this.right = right;
	}

	// return height
	public int getHeight() {
		return height;
	}

	// height to set
	public void setHeight(int height) {
		this.height = height;
	}

	// constructor
	public AVLTreeNode(Country element) {
		this(element, null, null);
	}

	// constructor
	public AVLTreeNode(Country element, AVLTreeNode left, AVLTreeNode right) {
		this.element = element;
		this.left = left;
		this.right = right;
		this.height = 0;
	}

}
