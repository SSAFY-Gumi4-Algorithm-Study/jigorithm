import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, M, K;
	static int[][] cost;
	static List<Integer>[] graph;
	static boolean[] visited;
	static int cnt = 0;

	public static void main(String[] args) throws Exception {

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int tempK = K;
		
		cost = new int[N + 1][2];
		visited = new boolean[N + 1];
		graph = new List[N + 1];
		for(int i=1; i<= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<= N; i++) {
			cost[i][0] = i;
			cost[i][1] = Integer.parseInt(st.nextToken());
		}
		
		cost[0][0] = Integer.MIN_VALUE;
		cost[0][1] = Integer.MIN_VALUE;
		
		for(int i=0; i< M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			graph[v].add(u);
		}
		
		Arrays.sort(cost, (o1, o2) -> {
			return Integer.compare(o1[1], o2[1]);
		});
		
		
		for(int i= 1; i<=N; i++) {
			if(!visited[cost[i][0]]) {
				if(K - cost[i][1] >= 0) {
					K -= cost[i][1];
					cnt++;
					bfs(cost[i][0]);
				}
			}
		}
		
		if(cnt == N) {
			System.out.println(tempK - K);
		}else {
			System.out.println("Oh no");
		}
		
	}
	
	private static void bfs(int src) {
		Queue<Integer> que = new LinkedList<>();
		que.add(src);
		visited[src] = true;
		
		while(!que.isEmpty()) {
			int u = que.poll();
			
			for(int v : graph[u]) {
				if(!visited[v]) {
					visited[v] = true;
					que.add(v);
					cnt++;
				}
			}
		}
		
		
	}
}
