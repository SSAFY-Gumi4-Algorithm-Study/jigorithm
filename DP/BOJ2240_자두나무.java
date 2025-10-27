import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		int[][][] dp = new int[1001][31][3];

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[] arr = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		if(arr[1] == 1){
			dp[1][0][1] = 1;
			dp[1][1][2] = 0;
		}else{
			dp[1][0][1] = 0;
			dp[1][1][2] = 1;
		}

		for (int i = 2; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				if(arr[i] == 1){
					if(j == 0){
						dp[i][0][1] = dp[i - 1][0][1] + 1;
						dp[i][0][2] = dp[i - 1][0][2];
						continue;
					}
					dp[i][j][1] = Math.max(dp[i - 1][j - 1][2] + 1, dp[i - 1][j][1] + 1);
					dp[i][j][2] = Math.max(dp[i - 1][j][2], dp[i - 1][j - 1][1]);
				}else{
					if(j == 0){
						dp[i][0][1] = dp[i - 1][0][1];
						dp[i][0][2] = dp[i - 1][0][2] + 1;
						continue;
					}
					dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][2]);
					dp[i][j][2] = Math.max(dp[i - 1][j][2] + 1, dp[i - 1][j - 1][1] + 1);
				}
			}
		}

		int max = -1;
		for (int i = 0; i <= m; i++) {
			max = Math.max(max, dp[n][i][1]);
			max = Math.max(max, dp[n][i][2]);
		}

		System.out.println(max);

	}
}

