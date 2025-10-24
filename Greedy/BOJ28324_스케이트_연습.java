import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		int n = Integer.parseInt(br.readLine());
		long[] arr = new long[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}

		long time = 0;
		long sum = 0;

		for (int i = n - 1; i >= 0; i--) {
			if(arr[i] > time){
				time++;
				sum += time;
			}else if(time == arr[i]){
				sum += time;
			}else{
				time = arr[i];
				sum += time;
			}
			// System.out.println(time);

		}
		System.out.println(sum);
	}

}

