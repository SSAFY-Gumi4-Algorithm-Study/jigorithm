import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static List<Integer>[] graph;
	static int a, b, x;
	static int[] visited;
	static int[] comp;
	static int cnt = 0;

	public static void main(String[] args) throws IOException {

		int n = Integer.parseInt(br.readLine());
		graph = new List[n + 1];
		visited = new int[n + 1];
		comp = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 1; i <= n - 1; i++) {
			st= new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}

		st =  new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());

		visited[x] = 1;

		for (int v : graph[x]) {
			if (visited[v] == 0) {
				cnt++;
				dfs(v);
			}
		}

		int compA = comp[a];
		int compB = comp[b];

		int result = 1;
		for (int i = 1; i <= n; i++) {
			if(compA != comp[i] && compB != comp[i] && x != i) {
				result++;
			}
		}

		System.out.println(result);

	}

	private static void dfs(int u) {
		visited[u] = 1;
		comp[u] = cnt;

		for(int v : graph[u]) {
			if(visited[v] == 0) {
				dfs(v);
			}
		}

	}
}

