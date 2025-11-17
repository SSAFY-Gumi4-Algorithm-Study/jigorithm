import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int[] dx = {-1, 1};
    static long result = 0;


    static HashSet<Integer> set = new HashSet<>();

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Queue<int[]> que = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            int value = Integer.parseInt(st.nextToken());
            set.add(value);
            que.add(new int[]{value, 1});
        }


        out:while (!que.isEmpty()) {
            int[] poll = que.poll();
            int x = poll[0];
            int depth = poll[1];

            for (int d = 0; d < 2; d++) {
                int nx = x + dx[d];
                if(!set.contains(nx)) {
                    result += depth;
                    set.add(nx);
                    que.add(new int[]{nx, depth + 1});
                    K--;

                    if(K == 0) break out;
                }
            }
        }

        System.out.println(result);

    }

}
