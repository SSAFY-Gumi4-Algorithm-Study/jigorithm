import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static List<Integer>[] graph;
    static int[][] arr;
    static int[][] dp;
    static int[] choice;

    public static void main(String[] args) throws Exception {

        init();
        dfs(1, 0);

        int bestColor = 0;
        for (int c = 1; c < 3; c++) {
            if (dp[1][c] > dp[1][bestColor]) bestColor = c;
        }

        trace(1, 0, bestColor);

        sb.append(dp[1][bestColor]).append("\n");
        for (int i = 1; i <= N; i++) {
        	if(choice[i] == 0) {
        		sb.append("R");
        	}else if(choice[i] == 1) {
        		sb.append("G");
        	}else {
        		sb.append("B");
        	}
        }
        
        System.out.println(sb);
    }


    private static int dfs(int cur, int parent) {
        for (int color = 0; color < 3; color++) {
            if (dp[cur][color] != -1) continue;

            int sum = arr[cur][color];

            for (int nxt : graph[cur]) {
                if (nxt == parent) continue;

                int best = 0;
                for (int nc = 0; nc < 3; nc++) {
                    if (nc == color) continue;
                    dfs(nxt, cur);
                    best = Math.max(best, dp[nxt][nc]);
                }

                sum += best;
            }

            dp[cur][color] = sum;
        }

        return Math.max(dp[cur][0], Math.max(dp[cur][1], dp[cur][2]));
    }

    // 역추적
    private static void trace(int cur, int parent, int color) {

        choice[cur] = color;

        for (int nxt : graph[cur]) {
            if (nxt == parent) continue;

            int bestColor = -1;
            int bestVal = -1;

            for (int nc = 0; nc < 3; nc++) {
                if (nc == color) continue;

                if (dp[nxt][nc] > bestVal) {
                    bestVal = dp[nxt][nc];
                    bestColor = nc;
                }
            }

            trace(nxt, cur, bestColor);
        }
    }

    private static void init() throws Exception {
        N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        arr = new int[N + 1][3];
        dp = new int[N + 1][3];
        choice = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }
    }
}

