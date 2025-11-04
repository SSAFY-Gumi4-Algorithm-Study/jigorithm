import java.util.*;
import java.io.*;


public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;


    static int N, M;
    static List<Edge>[] graph;
    static int[] arr;

    public static void main(String[] args) throws IOException {

        init();
        System.out.println(dijkstra(0, N - 1));

    }

    private static long dijkstra(int src, int dest) {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, 100_000_000_001L);
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> {
            return Long.compare(o1.cost, o2.cost);
        });
        dist[src] = 0;
        pq.add(new Edge(src, 0));

        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            int u = e.v;
            long cost = e.cost;
            if(dist[u] < cost) continue;
            if(u == dest) return cost;

            for (Edge v : graph[u]) {
                if (arr[v.v] == 0 && dist[v.v] > v.cost + cost) {
                    dist[v.v] = v.cost + cost;
                    pq.add(new Edge(v.v, dist[v.v]));
                }
            }
        }

        return -1;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        graph = new List[N];

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[N - 1] = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, cost));
            graph[v].add(new Edge(u, cost));
        }
    }

    static class Edge {
        int v;
        long cost;

        public Edge(int v, long cost) {
            this.v = v;
            this.cost = cost;
        }

    }
}
