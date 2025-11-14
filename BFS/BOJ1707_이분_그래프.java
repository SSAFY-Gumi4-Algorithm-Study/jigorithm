import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int T, N, M;

    static List<Integer>[] graph;
    static int[] visited;

    public static void main(String[] args) throws IOException {

        T = Integer.parseInt(br.readLine());

        out:
        for (int test_case = 0; test_case < T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            visited = new int[N + 1];
            Arrays.fill(visited, -1);

            graph = new List[N + 1];
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph[u].add(v);
                graph[v].add(u);
            }

            for (int i = 1; i <= N; i++) {
                if (visited[i] == -1) {
                    if (!bfs(i)) {
                        sb.append("NO").append("\n");
                        continue out;
                    }
                }
            }

            sb.append("YES").append("\n");

        }
        System.out.println(sb.toString());

    }


    private static boolean bfs(int start) {

        Queue<Integer> que = new LinkedList<>();
        que.offer(start);
        visited[start] = 0;

        while (!que.isEmpty()) {
            int u = que.poll();
            for (int v : graph[u]) {
                if (visited[v] == -1) {
                    visited[v] = visited[u] ^ 1;
                    que.offer(v);
                } else {
                    if (visited[v] != (visited[u] ^ 1)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}

