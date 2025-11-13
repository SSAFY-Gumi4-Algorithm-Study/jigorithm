import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;


    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (o1, o2) -> {
            return o2[1] - o1[1];
        });

        int day = arr[0][1];

        for (int i = 0; i < N; i++) {
            if (arr[i][1] <= day) {
                day = arr[i][1] - arr[i][0];
            }else{
                day -= arr[i][0];
            }
        }

        System.out.println(day);

    }
}


/**
 *  1   2   3   4   5   6   7   8   9   10  11  12  13
 *      s                       e
 *  s                                               e
 *          s                           e
 *
 *  x   x   x   x   x   1   1   3   3   3   x   x   2
 */
