import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static boolean[] arr = new boolean[41];

	public static void main(String[] args) throws IOException {

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			arr[Integer.parseInt(br.readLine())] = true;
		}

		int[] dp = new int[41];
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;
		for (int i = 3; i <= 40; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}

		int prev = 0;
		int cnt = 1;
		for (int i = 1; i <= N; i++) {
			if (arr[i]) {
				int len = (i - 1) - prev;
				cnt *= dp[len];
				prev = i;
			}
		}

		int len = N - prev;
		cnt *= dp[len];

		System.out.println(cnt);
	}
}


