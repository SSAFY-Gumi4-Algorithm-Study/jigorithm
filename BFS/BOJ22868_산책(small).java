import java.util.*;
import java.io.*;


public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static List<Integer>[] graph;
    static int src, dest;
    static int result = 0;

    public static void main(String[] args) throws IOException {

        init();

        // src -> dest   bfs
        int[] visited1 = new int[N + 1];
        Arrays.fill(visited1, -1);
        Queue<int[]> que1 = new LinkedList<>();
        visited1[src] = 0;
        que1.offer(new int[]{src, 0});

        while(!que1.isEmpty()) {
            int[] poll = que1.poll();
            int u = poll[0];
            int cost = poll[1];

            if(u == dest){
                break;
            }

            for (int v : graph[u]) {
                if(visited1[v] == -1){
                    visited1[v] = u;
                    que1.offer(new int[]{v, cost + 1});
                }
            }
        }

        int reverseSearchIdx = dest;


        int[] visited2 = new int[N + 1];
        Arrays.fill(visited2, -1);

        while(visited1[reverseSearchIdx] != 0){
            result++;
            visited2[reverseSearchIdx] = -2;    //방문처리
//            System.out.println(reverseSearchIdx);
            reverseSearchIdx = visited1[reverseSearchIdx];
        }
//        result++;
        visited2[reverseSearchIdx] = -2;
//        System.out.println(reverseSearchIdx);


        // dest -> src
        Queue<int[]> que2 = new LinkedList<>();
        visited2[src] = -1;
        visited2[dest] = 0;

        que2.offer(new int[]{dest, 0});
        while(!que2.isEmpty()) {
            int[] poll = que2.poll();
            int u = poll[0];
            int cost = poll[1];

            if(u == src){
                break;
            }

            for (int v : graph[u]) {
                if(visited2[v] == -1){
                    visited2[v] = u;
                    que2.offer(new int[]{v, cost + 1});
                }
            }
        }

        reverseSearchIdx = src;
        while(visited2[reverseSearchIdx] != 0){
            result++;
//            System.out.println(reverseSearchIdx);
            reverseSearchIdx = visited2[reverseSearchIdx];
        }
//        result++;
//        System.out.println(reverseSearchIdx);

        System.out.println(result);


    }


    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

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
            Collections.sort(graph[i]);
        }

        st = new StringTokenizer(br.readLine());
        src = Integer.parseInt(st.nextToken());
        dest = Integer.parseInt(st.nextToken());
    }
}
