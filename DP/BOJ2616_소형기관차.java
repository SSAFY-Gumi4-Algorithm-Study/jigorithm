import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 2];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int K = Integer.parseInt(br.readLine());

        int[] prefix = new int[N + 2];
        for (int i = 1; i <= N; i++) {
            prefix[i] = prefix[i - 1] + arr[i];
        }

        int[] rangeSum = new int[N + 2];
        for (int i = 1; i <= N - K + 1; i++) {
            rangeSum[i] = prefix[i + K - 1] - prefix[i - 1];
        }

        int[] maxDP1 = new int[N + 3];
        int[] maxDP2 = new int[N + 3];
        int[] maxDP3 = new int[N + 3];

        for (int i = N - K + 1; i >= 1; i--) {
            maxDP1[i] = Math.max(maxDP1[i + 1], rangeSum[i]);

            if (i <= N - (2 * K) + 1)
                maxDP2[i] = Math.max(maxDP2[i + 1], rangeSum[i] + maxDP1[i + K]);

            if (i <= N - (3 * K) + 1)
                maxDP3[i] = Math.max(maxDP3[i + 1], rangeSum[i] + maxDP2[i + K]);
        }

        System.out.println(maxDP3[1]);

    }
}


