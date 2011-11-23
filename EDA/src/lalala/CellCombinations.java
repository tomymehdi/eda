package lalala;

public class CellCombinations {
	private static final String s[] = { "", "", "abc", "def", "ghi", "jkl",
			"mnñop", "qrst", "uvw", "xyz" };

	public static void combinations(String number) {
		String s = "asdf";
		s.length();
		combinations(number, 0, new char[number.length()]);
	}

	private static void combinations(String number, int i, char[] cs) {
		if (i == number.length()) {
			System.out.println(cs);
			return;
		} else {
			int length = number.charAt(i) - '0';
			for (int j = 0; j < s[length].length(); j++) {
				cs[i] = s[length].charAt(j);
				combinations(number, i + 1, cs);
			}
		}

	}

	public static void main(String[] args) {
		combinations("256");
	}
}
