package lalala;

public class SortAscending {

	private static final int n = 1000;

	public static void main(String[] args) {
		int vec[] = new int[n];
		for (int i = 0; i < n; i++) {
			vec[i] = (int) (Math.random() * n);
		}
		for (int i : vec) {
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.println(isSorted(vec));
		Burbujeo.sortBurbujeo(vec);
		System.out.println();
		for (int i : vec) {
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.println(isSorted(vec));
	}

	private static boolean isSorted(int[] vec) {
		for (int i = 0; i < vec.length-1; i++) {
			if (vec[i] > vec[i + 1]) {
				return false;
			}
		}
		return true;
	}
}
