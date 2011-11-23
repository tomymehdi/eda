package lalala;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BSTImp<T> implements BST<T> {

	Node<T> root;
	Comparator<T> cmp;

	public BSTImp(Comparator<T> cmp) {
		this.cmp = cmp;
	}

	@Override
	public void add(T key) {
		root = add(root, key);
	}

	private Node<T> add(Node<T> current, T key) {
		if (current == null) {
			return new Node<T>(key);
		}
		int aux = cmp.compare(current.getData(), key);
		if (aux > 0) {
			current.left = add(current.left, key);
		} else if (aux < 0) {
			current.right = add(current.right, key);
		}
		return current;
	}

	@Override
	public boolean contains(T key) {
		return contains(root, key);
	}

	private boolean contains(Node<T> current, T key) {
		if (current == null) {
			return false;
		}
		int aux = cmp.compare(current.getData(), key);
		if (aux == 0) {
			return true;
		} else if (aux > 0) {
			return contains(current.getLeft(), key);
		} else {
			return contains(current.getRight(), key);
		}
	}

	@Override
	public void remove(T key) {
		root = remove(root, key);
	}

	private Node<T> remove(Node<T> current, T key) {
		if (current == null) {
			return null;
		}
		int aux = cmp.compare(current.getData(), key);
		if (aux == 0) {
			if (current.left == null) {
				return current.right;
			} else if (current.right == null) {
				return current.left;
			} else {
				/*
				 * reemplazo el nodo con el mayor de los hijos de la rama
				 * izquierda, tambien podria hacer reemplazo el nodo con el
				 * menor de los hijos de la rama derecha
				 */
				current.left = replaceWithInorderPredecessor(current.left,
						current);
				return current;
			}
		} else if (aux > 0) {
			current.left = remove(current.getLeft(), key);
		} else {
			current.right = remove(current.getRight(), key);
		}

		return current;

	}

	private Node<T> replaceWithInorderPredecessor(Node<T> node, Node<T> toRemove) {
		if (node.right == null) {
			toRemove.data = node.data;
			return node.left;
		}
		node.right = replaceWithInorderPredecessor(node.right, toRemove);
		return node;
	}

	@Override
	public int size() {
		return size(root);
	}

	private int size(Node<T> current) {
		if (current == null) {
			return 0;
		}

		return 1 + size(current.getLeft()) + size(current.getRight());
	}

	public int levelOfNode(T key) {
		return levelOfNode(root, key);
	}

	/* el nivel del root es 0 */
	private int levelOfNode(Node<T> current, T key) {
		if (current == null) {
			return -1;
		}
		int aux = cmp.compare(current.getData(), key);
		if (aux == 0) {
			return 0;
		}

		int level = aux > 0 ? levelOfNode(current.left, key) : levelOfNode(
				current.right, key);

		return level == -1 ? -1 : 1 + level;
	}

	public int leaves() {
		return leaves(root);
	}

	private int leaves(Node<T> current) {
		if (current == null) {
			return 0;
		}
		if (current.getLeft() == null && current.getRight() == null) {
			return 1;
		}
		return leaves(current.left) + leaves(current.right);
	}

	public T maxValue() {
		/* iterativo */
		if (root == null) {
			return null;
		}
		Node<T> node = root;
		while (node.getRight() != null) {
			node = node.getRight();
		}
		return node.getData();
		/* recursivo */
		// return maxValue(root, null);
	}

	private T maxValue(Node<T> current, T max) {
		if (current == null) {
			return max;
		}
		return maxValue(current.right, current.getData());
	}

	public void printAsestors(T key) {
		printAnsestors(root, key);
	}

	private boolean printAnsestors(Node<T> current, T key) {
		if (current == null) {
			return false;
		}
		int aux = cmp.compare(current.getData(), key);
		boolean flag;
		if (aux == 0) {
			return true;
		} else if (aux > 0) {
			flag = printAnsestors(current.getLeft(), key);
		} else {
			flag = printAnsestors(current.getRight(), key);
		}
		if (flag) {
			System.out.println(current.getData());
		}
		return flag;
	}

	public void printDescendant(T key) {
		printDescendant(root, key);
	}

	private void printDescendant(Node<T> current, T key) {
		if (current == null) {
			return;
		}
		int aux = cmp.compare(current.getData(), key);
		if (aux == 0) {
			printNodes(current.left);
			printNodes(current.right);
		} else if (aux > 0) {
			printDescendant(current.getLeft(), key);
		} else {
			printDescendant(current.getRight(), key);
		}

	}

	private void printNodes(Node<T> node) {
		if (node == null) {
			return;
		}
		System.out.println(node.getData());
		printNodes(node.getLeft());
		printNodes(node.getRight());

	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BSTImp) {
			BSTImp<T> bst = (BSTImp) obj;
			return equals(root, bst.root);
		}
		return false;
	}

	private boolean equals(Node<T> current, Node<T> current2) {
		if (current == null && current2 == null) {
			return true;
		}
		if ((current == null && current2 != null)
				|| (current2 == null && current != null)) {
			return false;
		}
		if (cmp.compare(current.getData(), current2.getData()) != 0) {
			return false;
		}

		return equals(current.getLeft(), current2.getLeft())
				&& equals(current.getRight(), current2.getRight());
	}

	@Override
	public int hashCode() {
		return (int) Math.pow(size(), cmp.compare(root.getData(), root
				.getRight().getData()));
	}

	public void printPreOrder() {
		/* iterativo */
		if (root == null) {
			return;
		}
		Deque<Node<T>> stack = new LinkedList<Node<T>>();
		stack.push(root);
		while (!stack.isEmpty()) {
			Node<T> node = stack.pop();
			Node<T> r = node.getRight();
			Node<T> l = node.getLeft();
			System.out.println(node.getData());
			if (r != null) {
				stack.push(r);
			}
			if (l != null) {
				stack.push(l);
			}
		}
		/* recursivo */
		// printPreOrder(root);
	}

	private void printPreOrder(Node<T> current) {
		if (current == null) {
			return;
		}
		System.out.println(current.getData());
		printPreOrder(current.left);
		printPreOrder(current.right);
	}

	public void printInOrder() {
		/* iterativo */
		if (root == null) {
			return;
		}
		Deque<Node<T>> stack = new LinkedList<Node<T>>();
		Node<T> node, node2;
		node = root;
		stack.push(node);
		while (!stack.isEmpty()) {
			if (node != null) {
				node = node.getLeft();
				if (node != null) {
					stack.push(node);
				}
			} else {
				node2 = stack.pop();
				System.out.println(node2.getData());
				Node<T> r = node2.getRight();
				node = r;
				if (r != null) {
					stack.push(r);
				}
			}
		}
		/* recursivo */
		// printInOrder(root);
	}

	private void printInOrder(Node<T> current) {
		if (current == null) {
			return;
		}
		printInOrder(current.left);
		System.out.println(current.getData());
		printInOrder(current.right);
	}

	public void printPostOrder() {
		printPostOrder(root);
	}

	private void printPostOrder(Node<T> current) {
		if (current == null) {
			return;
		}
		printPostOrder(current.left);
		printPostOrder(current.right);
		System.out.println(current.getData());

	}

	public Iterator<T> preOrderIterator() {
		return new PreOrderIterator<T>(root);
	}

	private class PreOrderIterator<T> implements Iterator<T> {

		Deque<Node<T>> stack = new LinkedList<Node<T>>();

		public PreOrderIterator(Node<T> current) {
			stack.push(current);
		}

		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public T next() {
			Node<T> node = null;
			if (hasNext()) {
				node = stack.pop();
				Node<T> r = node.getRight();
				Node<T> l = node.getLeft();
				if (r != null) {
					stack.push(r);
				}
				if (l != null) {
					stack.push(l);
				}
			}
			return node.getData();
		}

		@Override
		public void remove() {
			throw new RuntimeException();
		}

	}

	private static class Node<T> {
		T data;
		Node<T> left, right;

		public Node(T data, Node<T> left, Node<T> right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		public Node(T data) {
			this.data = data;
		}

		public T getData() {
			return data;
		}

		public Node<T> getLeft() {
			return left;
		}

		public Node<T> getRight() {
			return right;
		}

		@Override
		public String toString() {
			return data.toString();
		}
	}

	private Iterator<T> inOrderIterator() {
		return new InOrderIterator<T>(root);
	}

	private class InOrderIterator<T> implements Iterator<T> {

		Deque<Node<T>> stack = new LinkedList<Node<T>>();
		boolean flag = true;

		public InOrderIterator(Node<T> current) {
			stack.push(current);
		}

		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public T next() {
			if (hasNext()) {
				Node<T> node = stack.peek(), node2;
				while (true) {
					if (node != null && flag) {
						node = node.getLeft();
						if (node != null) {
							stack.push(node);
						}
					} else {
						flag = false;
						node2 = stack.pop();
						Node<T> r = node2.getRight();
						node = r;
						if (r != null) {
							flag = true;
							stack.push(r);
						}
						return node2.getData();
					}
				}
			}
			return null;
		}

		@Override
		public void remove() {
			throw new RuntimeException();
		}

	}

	private Iterator<T> postOrderIterator() {
		return new PostOrderIterator<T>(root);
	}

	private class PostOrderIterator<T> implements Iterator<T> {

		Deque<Node<T>> stack = new LinkedList<Node<T>>();

		public PostOrderIterator(Node<T> node) {
			pushLeftBranch(node);
		}

		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public T next() {
			if (hasNext()) {
				Node<T> node = stack.pop();
				Node<T> parent = stack.peek();
				if (parent != null && node == parent.left) {
					pushLeftBranch(parent.right);
				}
				return node.data;

			}
			return null;
		}

		private void pushLeftBranch(Node<T> node) {
			if (node == null) {
				return;
			}
			stack.push(node);
			if (node.left == null) {
				pushLeftBranch(node.right);
			} else {
				pushLeftBranch(node.left);
			}
		}

		@Override
		public void remove() {
			throw new RuntimeException();
		}

	}

	public List<T> rangeSearch(T min, T max) {
		List<T> list = new ArrayList<T>();
		rangeSearch(root, min, max, list);
		return list;
	}

	private void rangeSearch(Node<T> node, T min, T max, List<T> list) {
		if (node == null) {
			return;
		}

		int aux = cmp.compare(node.data, min);
		int aux2 = cmp.compare(node.data, max);

		if (aux > 0) {
			rangeSearch(node.left, min, max, list);
		}
		if (aux >= 0 && aux2 <= 0) {
			list.add(node.data);
		}
		if (aux2 < 0) {
			rangeSearch(node.right, min, max, list);
		}
	}

	public static void main(String[] args) {
		BSTImp<String> arbol = new BSTImp<String>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}

		});
		arbol.add("B");
		arbol.add("C");
		arbol.add("F");
		arbol.add("E");
		arbol.add("A");
		arbol.add("D");
		arbol.printPostOrder();
		System.out.println();
		List<String> list = new LinkedList();
		list.add("A");
		list.add("D");
		list.add("E");
		list.add("F");
		// list.add("C");
		// list.add("Z");
		// list.add("j");
		System.out.println(BSTImp.checkPostOrder(arbol.root, list));
	}

	private static <T> boolean checkPostOrder(Node<T> root, List<T> list) {
		Iterator<T> it = list.iterator();
		return BSTImp.checkPostOrder(root, it) && !it.hasNext();

	}

	private static <T> boolean checkPostOrder(Node<T> node, Iterator<T> it) {
		if (node == null) {
			return true;
		}
		List<String> a = new ArrayList<String>();
		if(!checkPostOrder(node.left, it) || !checkPostOrder(node.right, it)){
			return false;
		}
		if (it.hasNext()) {
			return it.next().equals(node.data);
		}
		return false;
	}

}
