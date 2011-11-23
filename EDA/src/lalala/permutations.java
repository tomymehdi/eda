package lalala;

public class permutations {
	public static void main(String[] args) {
		permutations("ABAC");
	}

	public static void permutations(String s) {
		permutations(s.toCharArray(), 0);
	}

	private static void permutations(char[] c, int n) {
		if (n == c.length) {
			System.out.println(c);
			return;
		}
		for (int i = n; i < c.length; i++) {
			if (evaluate(c, n, i)) {
				swap(c, n, i);
				permutations(c, n + 1);
				swap(c, n, i);
			} else {
				continue;
			}

		}
		return;
	}

	private static boolean evaluate(char[] c, int n, int i) {

		for (int j = n; j < i; j++) {
			if (c[j] == c[i]) {
				return false;
			}
		}
		return true;
	}

	private static void swap(char[] c, int i, int j) {
		char aux = c[i];
		c[i] = c[j];
		c[j] = aux;

	}
}
