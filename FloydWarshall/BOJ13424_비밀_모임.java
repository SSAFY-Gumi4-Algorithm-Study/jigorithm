import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T;
    static int N, M, K;
    static int[][] map;
    static final int INF = 1_000_000_000;
    static int[] pos;

    public static void main(String[] args) throws Exception {

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new int[N + 1][N + 1];

            for (int i = 1; i <= N; i++) {
                Arrays.fill(map[i], INF);
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                map[a][b] = c;
                map[b][a] = c;
            }
            for (int i = 1; i <= N; i++) {
                map[i][i] = 0;
            }

            K = Integer.parseInt(br.readLine());
            pos = new int[K];
            st =  new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                pos[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    for (int k = 1; k <= N; k++) {
                        map[j][k] = Math.min(map[j][k], map[j][i] + map[i][k]);
                    }
                }
            }

            List<Node> list = new ArrayList<>();
            for (int i = 1; i <= N; i++) {
                int sum = 0;
                for (int k = 0; k < K; k++) {
                    sum += map[pos[k]][i];
                }
                list.add(new Node(i, sum));
            }

            list.sort((o1, o2) -> {
                if (o1.sum == o2.sum) {
                    return Integer.compare(o1.idx, o2.idx);
                }
                return Integer.compare(o1.sum, o2.sum);
            });


            sb.append(list.get(0).idx).append("\n");
        }

        System.out.println(sb);

    }

    static class Node{
        int idx, sum;
        public Node(int idx, int sum){
            this.idx = idx;
            this.sum = sum;
        }
    }
}
