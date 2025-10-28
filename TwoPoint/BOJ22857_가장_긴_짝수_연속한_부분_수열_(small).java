import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N, M;
	static int[] arr;

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int even = 0;
		int odd = 0;
		int l = 0;
		int r = 0;
		int max = 0;

		while (l <= r && r < N) {
			if (arr[r] % 2 == 0) {
				even++;
				max = Math.max(max, even);
			} else {
				odd++;
			}

			while (odd > M) {
				if (arr[l] % 2 == 0) {
					even--;
				} else {
					odd--;
				}
				l++;
			}

			r++;
		}

		System.out.println(max);

	}
}

