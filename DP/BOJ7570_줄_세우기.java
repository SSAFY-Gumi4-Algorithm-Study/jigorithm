import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		int N = Integer.parseInt(br.readLine());
		int[] pos = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int num = Integer.parseInt(st.nextToken());
			pos[num] = i;
		}

		// System.out.println(Arrays.toString(pos));

		int[] dp = new int[N + 1];
		dp[1] = 1;
		int max = 0;
		for (int i = 1; i <= N; i++) {
			if (pos[i - 1] < pos[i]) dp[i] = dp[i - 1] + 1;
			else dp[i] = 1;
			// System.out.println(Arrays.toString(dp));
			max = Math.max(max, dp[i]);
		}

		System.out.println(N - max);
	}

}

