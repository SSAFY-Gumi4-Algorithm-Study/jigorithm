import java.util.*;
import java.io.*;

class Main {

	static class Edge{
		int y, x, cost;
		public Edge(int y, int x, int cost) {
			this.y = y;
			this.x = x;
			this.cost = cost;
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static final int INF = 1_000_000_000;

	static int N, M, T, D;
	static int[][] map;
	static int[][][][] dist = new int[26][26][26][26];
	static int[] dy = {-1, 0, 0, 1};
	static int[] dx = {0, -1, 1, 0};

	public static void main(String[] args) throws IOException {

		init();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dijkstra(i, j);
			}
		}

		int max = -1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(dist[i][j][0][0] == INF || dist[0][0][i][j] == INF) continue;
				if(dist[i][j][0][0] + dist[0][0][i][j] <= D){
					max = Math.max(max, map[i][j]);
				}
			}
		}

		System.out.println(max);
	}

	private static void dijkstra(int sy, int sx) {

		PriorityQueue<Edge> pq = new PriorityQueue<>(((o1, o2) -> Integer.compare(o1.cost, o2.cost)));
		pq.add(new Edge(sy, sx, 0));
		dist[sy][sx][sy][sx] = 0;

		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			int y =  e.y;
			int x = e.x;
			int cost = e.cost;

			if(dist[y][x][sy][sx] < cost) continue;

			for (int k = 0; k < 4; k++) {
				int ny = y + dy[k];
				int nx = x + dx[k];

				if(!isRange(ny, nx)) continue;
				int height = getHeight(y, x, ny, nx);

				if(height <= T){
					int getDist = getDist(y, x, ny, nx);
					if(cost + getDist <= D && dist[ny][nx][sy][sx] > cost + getDist){
						dist[ny][nx][sy][sx] = cost + getDist;
						pq.add(new Edge(ny, nx, getDist + cost));
					}
				}
			}
		}
	}

	private static int getDist(int y, int x, int ny, int nx){
		if(map[y][x] >= map[ny][nx]){
			return 1;
		}else{
			return Math.abs(map[y][x] - map[ny][nx]) * Math.abs(map[y][x] - map[ny][nx]);
		}
	}


	private static int getHeight(int y, int x, int ny, int nx){
		return Math.abs(map[y][x] - map[ny][nx]);
	}

	private static boolean isRange(int y, int x){
		return 0<= y && y < N && 0<= x && x < M;
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				char c =  s.charAt(j);
				if(Character.isUpperCase(c)){
					map[i][j] = c - 'A';
				}else {
					map[i][j] = c - 'a' + 26;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < N; k++) {
					for (int l = 0; l < M; l++) {
						dist[i][j][k][l] = INF;
					}
				}
			}
		}

	}
}
