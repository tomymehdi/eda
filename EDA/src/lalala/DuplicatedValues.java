package lalala;

public class DuplicatedValues {
	private static final int n = 1000;

	public static void main(String[] args) {
		int vec[] = new int[n];
		for (int i = 0; i < n; i++) {
			vec[i] = (int) (Math.random() * n);
		}
		System.out.println(duplicatedValues(vec));
		for (int i = 0; i < n; i++) {
			vec[i] = i;
		}
		System.out.println(duplicatedValues(vec));
		Burbujeo.sortBurbujeo(vec);
		System.out.println(duplicatedValuesSortedVec(vec));
	}

	public static boolean duplicatedValuesSortedVec(int[] vec) {
		for (int i = 0; i < vec.length - 1; i++) {
			if (vec[i] == vec[i + 1]) {
				return true;
			}
		}
		return false;
	}

	public static boolean duplicatedValues(int[] vec) {
		for (int i = 0; i < vec.length; i++) {
			for (int j = i+1; j < vec.length; j++) {
				if (vec[i] == vec[j]) {
					return true;
				}
			}
		}
		return false;
	}

}
