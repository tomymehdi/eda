package lalala;

public class Burbujeo {
	private static final int n = 1000;

	public static void main(String[] args) {
		int vec[] = new int[n];
		for (int i = 0; i < n; i++) {
			vec[i] = (int) (Math.random() * n);
		}
		for(int i: vec){
			System.out.print(i + " ");
		}
		sortBurbujeo(vec);
		System.out.println();
		for(int i: vec){
			System.out.print(i + " ");
		}
	}

	public static void sortBurbujeo(int[] vec) {
		for(int i = 0 ; i < vec.length ; i++){
			for(int j = 0; j < vec.length -i-1 ; j++){
				if(vec[j]>vec[j+1]){
					int aux = vec[j];
					vec[j] = vec[j+1];
					vec[j+1] = aux;
				}
			}
		}
	}

}
