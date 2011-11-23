package lalala;

import java.util.Arrays;

public class Intersection {
	private static final int n = 10;

	public static void main(String[] args) {
		int vec[] = new int[n];
		int vec2[] = new int[n];
		for (int i = 0; i < n; i++) {
			vec[i] = (int) (Math.random() * n);
		}
		for (int i = 0; i < n; i++) {
			vec2[i] = (int) (Math.random() * n);
		}

		for (int i : vec) {
			System.out.print(i + " ");
		}
		System.out.println();
		for (int i : vec2) {
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.println("caso1");
		int[] a = intersectionNotSortedVecs(vec, vec2);
		for (int i : a) {
			System.out.print(i + " ");
		}
		Burbujeo.sortBurbujeo(vec);
		Burbujeo.sortBurbujeo(vec2);
		System.out.println();
		System.out.println();
		for (int i : vec) {
			System.out.print(i + " ");
		}
		System.out.println();
		for (int i : vec2) {
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.println("caso2");
		a = intersectionSortedVecs(vec, vec2);
		for (int i : a) {
			System.out.print(i + " ");
		}
	}

	public static int[] intersectionNotSortedVecs(int[] vec, int[] vec2) {
		int resp[] = new int[vec.length];
		int size = 0;
		boolean flag;
		for (int i = 0; i < vec.length; i++) {
			flag = true;
			for (int j = 0; j < vec2.length && flag; j++) {
				if (vec[i] == vec2[j]) {
					resp[size] = vec[i];
					size++;
					flag = false;
				}
			}
		}
		return Arrays.copyOf(resp, size);
	}

	public static int[] intersectionSortedVecs(int[] vec, int[] vec2) {
		int[] resp = new int[vec.length];
		int size = 0;
		int i = 0, j = 0;
		while (i < vec.length && j < vec2.length) {
			if (vec[i] == vec[j]) {
				resp[size] = vec[i];
				j++;
				i++;
				size++;
			} else if (vec[i] > vec[j]) {
				j++;
			} else {
				i++;
			}
		}
		return Arrays.copyOf(resp, size);
	}
}
