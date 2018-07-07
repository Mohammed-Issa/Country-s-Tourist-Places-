
public class LinkedList {
	private Node Front; // first node
	private Node Back; // last node
	private int size; // list size

	// return the front
	public Node getFront() {
		return Front;
	}

	// front to set
	public void setFront(Node front) {
		Front = front;
	}

	// constructor
	public LinkedList() {
		Front = Back = null;
		size = 0;
	}

	// add to the first of the linked list
	public void addFirst(City element) {
		Node newNode = new Node(element);
		if (size == 0) {
			Front = Back = newNode;
		} else {
			newNode.setNext(Front);
			Front = newNode;
		}
		size++;
	}

	// return the first city
	public City getFirst() {
		if (size == 0) {
			return null;
		} else {
			return Front.getElement();
		}
	}

	// add to the last of the linked list
	public void addLast(City element) {
		Node newNode = new Node(element);
		if (size == 0) {
			Front = Back = newNode;
		} else {
			Back.setNext(newNode);
			Back = newNode;
		}
		size++;
	}

	// returns the last city
	public City getLast() {
		if (size == 0) {
			return null;
		} else {
			return Back.getElement();
		}
	}

	// find and return a city in the list
	public City get(int index) {
		if (size == 0)
			return null;
		else if (index == 0)
			return getFirst();
		else if (index == size - 1)
			return getLast();
		else if (index > 0 && index < size - 1) {
			Node current = Front;
			for (int i = 0; i < index; i++)
				current = current.getNext();
			return current.getElement();
		} else
			return null;
	}

	public int Size() {
		return size;
	}

	// insert a city to the list
	public void add(City element) {
		add(Size(), element);
	}

	// insert a city to the list
	public void add(int index, City element) {
		if (index == 0)
			addFirst(element);
		else if (index >= size)
			addLast(element);
		else {
			Node newNode = new Node(element);
			Node current = Front;
			for (int i = 0; i < index - 1; i++) {
				current = current.getNext();
			}
			newNode.setNext(current.getNext());
			current.setNext(newNode);
			size++;
		}
	}

	// remove the first node
	public boolean removeFirst() {
		if (size == 0)
			return false;
		else if (size == 1)
			Front = Back = null;
		else
			Front = Front.getNext();
		size--;
		return true;
	}

	// remove the last node
	public boolean removeLast() {
		if (size == 0)
			return false;
		else if (size == 1)
			Front = Back = null;
		else {
			Node current = Front;
			for (int i = 0; i < size - 2; i++)
				current = current.getNext();
			current.setNext(null);
			Back = current;
		}
		size--;
		return true;
	}

	// remove a node from list
	public boolean remove(int index) {
		if (size == 0)
			return false;

		else if (index == 0)
			return removeFirst();

		else if (index == size - 1)
			return removeLast();

		else if (index > 0 && index < size - 1) {
			Node current = Front;
			for (int i = 0; i < index - 1; i++)
				current = current.getNext();
			current.setNext(current.getNext().getNext());
			size--;
			return true;
		} else
			return false;

	}

	// print the list
	public void printList() {
		Node current = Front;
		while (current != null) {
			System.out.println(current.getElement().getCityN());
			current = current.getNext();
		}
	}

	public String print() {
		String s = "";
		Node current = Front;
		while (current != null) {
			s += current.getElement().getCityN();
			current = current.getNext();
		}
		return s;
	}

	// print the list recursively
	public void printList(Node current) {
		if (current != null) {
			System.out.println(current.getElement());
			printList(current.getNext());
		}

	}

	// clear the list
	public void clear() {
		Front = Back = null;
	}

	// find and return a node in the list
	public int find(Object o) {
		for (int i = 0; i < size - 1; i++) {
			if (o.equals(get(i)))
				return i;
		}
		return -1;

	}

	// remove an object
	public boolean removeObj(Object o) {

		for (int i = 0; i < size - 1; i++) {
			if (o.equals(get(i))) {
				remove(i);
				return true;
			}
		}
		return false;

	}

}

class Node {
	private City element; // the element
	private Node next; // the next node

	// constructor
	public Node(City element) {
		this(element, null);
	}

	// constructor
	public Node(City element, Node next) {
		this.element = element;
		this.next = next;
	}

	/**
	 * @return the element
	 */
	public City getElement() {
		return element;
	}

	/**
	 * @param element
	 *            the element to set
	 */
	public void setElement(City element) {
		this.element = element;
	}

	/**
	 * @return the next
	 */
	public Node getNext() {
		return next;
	}

	/**
	 * @param next
	 *            the next to set
	 */
	public void setNext(Node next) {
		this.next = next;
	}

}
