import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {


        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int l = 0;
        int r = arr.length - 1;
        int min = Integer.MAX_VALUE;
        int result = 0;

        while (l < r) {
            int sum = Math.abs(arr[l] + arr[r]);

            if (sum < min) {
                min = sum;
                result = arr[l] + arr[r];
            }

            if (arr[l] + arr[r] < 0) {
                l++;
            }else{
                r--;
            }

        }

        System.out.println(result);

    }
}

