import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            if (N == 0 && K == 0) break;

            int[][] arr = new int[N + 1][2];
            int sum = 0;

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
                sum += arr[i][0] + arr[i][1];
            }

            int[][][] dp = new int[N + 1][K + 1][3];

            for (int i = 0; i <= N; i++) {
                for (int j = 0; j <= K; j++) {
                    for (int k = 0; k < 3; k++) {
                        dp[i][j][k] = INF;
                    }
                }
            }

            dp[0][0][0] = dp[0][0][1] = dp[0][0][2] = 0;

            for (int i = 1; i <= N; i++) {
                dp[i][0][2] = 0;
            }

            for (int j = 1; j <= N; j++) {
                for (int i = 0; i <= K; i++) {
                    dp[j][i][2] = Math.min(dp[j - 1][i][0], Math.min(dp[j - 1][i][1], dp[j - 1][i][2]));
                    if (i > 0) {
                        dp[j][i][0] = Math.min(dp[j - 1][i - 1][0], dp[j - 1][i - 1][2]) + arr[j][0];
                        dp[j][i][1] = Math.min(dp[j - 1][i - 1][1], dp[j - 1][i - 1][2]) + arr[j][1];
                    }
                }
            }

            int min = Math.min(dp[N][K][0], Math.min(dp[N][K][1], dp[N][K][2]));
            System.out.print(sum - min);
        }
        
    }
}
