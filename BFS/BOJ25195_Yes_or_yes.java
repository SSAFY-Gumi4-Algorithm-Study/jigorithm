import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static List<Integer>[] graph;
    static int N, M;
    static int S;
    static boolean[] isBear;

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        isBear = new boolean[N + 1];
        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
        }

        S = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= S; i++) {
            isBear[Integer.parseInt(st.nextToken())] = true;
        }

        if (bfs(1)) {
            System.out.println("yes");
        }else{
            System.out.println("Yes");
        }

    }

    static private boolean bfs(int src) {
        boolean[] visited = new boolean[N + 1];

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{src, 0});
        visited[src] = true;

        if(isBear[src]) return false;

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int cur = poll[0];
            int bear = poll[1];

            if (graph[cur].size() == 0) {
                if (bear == 0) {
                    return true;
                }
            }

            for(int next: graph[cur]) {
                if(!visited[next] && !isBear[next]){
                    q.add(new int[]{next, 0});
                    visited[next] = true;
                }
            }
        }

        return false;
    }

}
