import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int[] cost;
    static List<Integer>[] tree;
    static int[][] dp;
    static boolean[] visited;
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        cost = new int[N + 1];
        dp = new int[N + 1][2];
        tree = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        dfs(1);

        int chooseRoot = (dp[1][1] > dp[1][0]) ? 1 : 0;

        trace(1, chooseRoot);

        Collections.sort(result);

        System.out.println(Math.max(dp[1][0], dp[1][1]));

        for (int x : result) {
            System.out.print(x + " ");
        }
    }

    static void dfs(int u) {
        visited[u] = true;
        
        dp[u][1] = cost[u];
        dp[u][0] = 0;

        for (int v : tree[u]) {
            if (!visited[v]) {
                dfs(v);
                dp[u][0] += Math.max(dp[v][0], dp[v][1]);
                dp[u][1] += dp[v][0];
            }
        }
    }

    static void trace(int u, int taken) {
        visited[u] = false;

        if (taken == 1) {
            result.add(u);

            for (int v : tree[u]) {
                if (visited[v]) {
                    trace(v, 0);
                }
            }

        } else {
            for (int v : tree[u]) {
                if (visited[v]) {
                    if (dp[v][1] > dp[v][0]) trace(v, 1);
                    else trace(v, 0);
                }
            }
        }
    }
}

