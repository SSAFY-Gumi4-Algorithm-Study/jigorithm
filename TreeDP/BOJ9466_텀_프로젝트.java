import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9466_텀_프로젝트 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int[] graph;
	static int[] visited;
	static int[] dp;
	static int n;
	static int count;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			init();
			count = 0;

			for (int i = 1; i <= n; i++) {
				if (visited[i] == 0) {
					dfs(i);
				}
			}

			sb.append(n - count).append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int node) {
		visited[node] = 1;
		int next = graph[node];

		if (visited[next] == 1) {
			count++;
			for (int i = next; i != node; i = graph[i]) {
				count++;
			}
		}

		if (visited[next] == 0) {
			dfs(next);
		}

		visited[node] = -1;
	}

	private static void init() throws IOException {
		n = Integer.parseInt(br.readLine());
		graph = new int[n + 1];
		visited = new int[n + 1];
		dp = new int[n + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			graph[i] = Integer.parseInt(st.nextToken());
		}
	}
}
