import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(arr);
		int l = 0;
		int min = Integer.MAX_VALUE;
		for (int r = 0; r < arr.length; r++) {
			int sub = arr[r] - arr[l];
			if (sub >= M) {
				min = Math.min(min, sub);
			}

			while (l < r && sub >= M) {
				l++;
				sub = arr[r] - arr[l];
				if(sub >= M) {
					min = Math.min(min, sub);
				}else{
					break;
				}
			}
		}

		System.out.println(min);
	}
}

