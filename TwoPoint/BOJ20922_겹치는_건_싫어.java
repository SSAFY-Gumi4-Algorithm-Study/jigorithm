import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int[] visited = new int[1000001];
    static int[] arr = new int[1000001];

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int l=0, r=0;
        visited[arr[r]]++;
        int len = 1;
        int max = -1;
        while (l <= r && r < N - 1) {
            r++;
            len++;
            visited[arr[r]]++;

            while(visited[arr[r]] > K) {
                visited[arr[l]]--;
                l++;
                len--;
            }

            max = Math.max(max, len);
        }

        System.out.println(max);

    }

}
