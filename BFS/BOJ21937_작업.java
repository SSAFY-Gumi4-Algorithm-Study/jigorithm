import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static List<Integer>[] graph;
    static boolean[] visited;
    static int N, M, X, result = 0;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[b].add(a);
        }

        X = Integer.parseInt(br.readLine());

        Queue<Integer> que = new LinkedList<>();
        que.offer(X);

        while (!que.isEmpty()) {
            int cur = que.poll();

            for (int v : graph[cur]) {
                if(!visited[v]) {
                    visited[v] = true;
                    que.offer(v);
                    result++;
                }
            }
        }

        System.out.println(result);

    }

}
