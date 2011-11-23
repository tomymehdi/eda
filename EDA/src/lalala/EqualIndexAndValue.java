package lalala;

public class EqualIndexAndValue {

	public int equalIndexAndValue(int[] vec) {

		int left, right, m;
		left = 0;
		right = vec.length;

		while (left <= right) {
			m = left + (right - left) / 2;

			if (vec[m] == m) {
				return m;
			} else if (vec[m] < m) {
				left = m + 1;
			} else {
				right = m - 1;
			}

		}
		return -1;
	}
}
