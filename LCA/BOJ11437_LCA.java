import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static final int LEN = 50000;

	//depth[3][0] = 3번째 노드의 부모노드
	//depth[3][1] = 3번째 노드의 depth
	static int[][] depth = new int[LEN + 1][2];
	static List<Integer>[] graph = new List[LEN + 1];
	static int N, M;

	public static void main(String[] args) throws IOException {

		for (int i = 1; i <= LEN; i++) {
			graph[i] = new ArrayList<>();
		}

		N = Integer.parseInt(br.readLine());
		for (int i = 1; i <= N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}

		bfs();

		M = Integer.parseInt(br.readLine());
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(query(a, b)).append("\n");
		}

		System.out.println(sb.toString());

	}

	//a , b 의 최소공통조상 찾기
	private static int query(int a, int b) {

		//같은 층으로 만들기
		while (depth[a][1] < depth[b][1]) {
			b = depth[b][0];
		}

		//같은 층으로 만들기
		while (depth[a][1] > depth[b][1]) {
			a = depth[a][0];
		}

		//같은층임.
		//올라가면서 부모가 같은지 확인하기
		while (depth[a][0] != depth[b][0]) {
			a = depth[a][0];
			b = depth[b][0];
		}

		//a or b 가 그 쿼리의 공통조상일때
		if(a == b) return a;

		return depth[a][0];
	}


	//시작 정점 1에서부터 각 노드가 얼마나 떨어져 있는지 depth 구하기
	private static void bfs() {
		boolean[] visited = new boolean[LEN + 1];

		Queue<int[]> que = new LinkedList<>();
		depth[1][0] = 0;
		depth[1][1] = 1;
		visited[1] = true;
		que.offer(new int[] {1, depth[1][1]});

		while (!que.isEmpty()) {
			int[] poll = que.poll();
			int u = poll[0];
			int dep = poll[1];

			for (int v : graph[u]) {
				if (!visited[v]) {
					depth[v][0] = u;
					depth[v][1] = dep + 1;
					visited[v] = true;
					que.offer(new int[] {v, depth[v][1]});
				}
			}
		}

	}

}

