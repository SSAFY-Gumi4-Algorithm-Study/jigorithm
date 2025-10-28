import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static boolean[] visited = new boolean[100001];

	public static void main(String[] args) throws IOException {

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int l = 0;
		int r = 0;
		int size = 0;
		long cnt = 0;
		while (l <= r && r < N) {
			if (!visited[arr[r]]) {
				visited[arr[r]] = true;
				size++;
			}else{
				while(visited[arr[r]]){
					visited[arr[l]] = false;
					l++;
					size--;
				}
				visited[arr[r]] = true;
				size++;
			}
			r++;
			cnt += size;
		}

		// System.out.println("l = " + l + " r = "  + r);

		System.out.println(cnt);
	}
}

