package lalala;

public class Paint {

	static int[][] image = { { 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0 },
			{ 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0 },
			{ 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
			{ 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0 },
			{ 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0 } };

	public static void paint(int color, int row, int col) {
		if (row > 12 || row < 0 || col > 12 || col < 0) {
			return;
		}
		if (image[row][col] == color) {
			return;
		} else {
			image[row][col] = color;
		}
		paint(color, row + 1, col);
		paint(color, row - 1, col);
		paint(color, row, col + 1);
		paint(color, row, col - 1);
	}

	public static void main(String[] args) {
		for (int[] vec : image) {
			for (int i : vec) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
		System.out.println();
		paint(1, 6, 6);
		for (int[] vec : image) {
			for (int i : vec) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
}
