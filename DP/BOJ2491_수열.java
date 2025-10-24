import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		int n =  Integer.parseInt(br.readLine());
		int[] arr = new int[n + 1];
		st =  new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp1 =  new int[n + 1];
		int[] dp2 = new int[n + 1];

		for (int i = 2; i <= n; i++) {
			if(arr[i - 1] <= arr[i]){
				dp1[i] = dp1[i - 1] + 1;
			}
			else{
				dp1[i] = 0;
			}

			if(arr[i - 1] >= arr[i]){
				dp2[i] = dp2[i - 1] + 1;
			}else{
				dp2[i] = 0;
			}
		}

		int max = -1;
		for (int i = 1; i <= n; i++) {
			max = Math.max(max, Math.max(dp1[i], dp2[i]));
		}

		System.out.println(max + 1);

		// System.out.println(Arrays.toString(dp1));
		// System.out.println(Arrays.toString(dp2));

	}

}

