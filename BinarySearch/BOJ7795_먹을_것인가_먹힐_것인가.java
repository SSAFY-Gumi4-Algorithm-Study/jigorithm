import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int[] arr1 = new int[A];
			int[] arr2 = new int[B];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < A; i++) {
				arr1[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < B; i++) {
				arr2[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(arr2);

			int count = 0;

			for (int i = 0; i < A; i++) {
				int idx = lowerBound(arr2, arr1[i]);
				count += idx;
			}
			sb.append(count).append("\n");
		}

		System.out.println(sb);

	}

	//	1	2	2	3	3	3	4	7	7
	private static int lowerBound(int[] arr, int tar) {
		int l = 0;
		int r = arr.length;

		while (l < r) {
			int mid = (l + r) / 2;

			if (arr[mid] < tar) {
				l = mid + 1;
			} else {
				r = mid;
			}
		}

		return l;

	}

}

