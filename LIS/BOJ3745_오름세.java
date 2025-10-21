import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		String line = "";
		while ((line = br.readLine()) != null) {
			line = line.trim();
			if(line.isEmpty()) break;
			int n = Integer.parseInt(line);
			int[] arr = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			sb.append(lis(arr)).append("\n");
		}

		System.out.println(sb.toString());
	}

	private static int lis(int[] arr) {
		int[] lis = new int[arr.length];
		int len = 0;

		for (int i = 0; i < arr.length; i++) {
			int idx = lowerBound(lis, arr[i], len);
			lis[idx] = arr[i];
			if(idx == len) len++;
		}

		return len;
	}

	private static int lowerBound(int[] arr, int x, int len) {
		int l = 0;
		int r = len;

		while (l < r) {
			int mid = (l + r) / 2;

			if (arr[mid] < x) {
				l = mid + 1;
			} else {
				r = mid;
			}
		}

		return l;
	}
}

