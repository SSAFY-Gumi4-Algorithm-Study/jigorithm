import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int[] cost;
    static List<Integer>[] graph;
    static int[][] dp;        // dp[u][0], dp[u][1]
    static boolean[] visited;

    public static void main(String[] args) throws Exception {


        N = Integer.parseInt(br.readLine());
        cost = new int[N + 1];
        graph = new List[N + 1];
        dp = new int[N + 1][2];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        dfs(1);

        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    private static void dfs(int u) {
        visited[u] = true;
        dp[u][0] = cost[u];
        dp[u][1] = 0;

        for(int v : graph[u]) {
            if(!visited[v]) {
                dfs(v);
                dp[u][0] += dp[v][1];
                dp[u][1] += Math.max(dp[v][0], dp[v][1]);
            }
        }
    }
}

