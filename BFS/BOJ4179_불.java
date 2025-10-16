import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static char[][] map;
	static boolean[][] visited;
	static boolean[][] fireVisited;
	static int n, m;
	static int[] src = new int[2];
	static List<int[]> fireList = new ArrayList<>();
	static Queue<int[]> queue = new LinkedList<>();

	static int[] dy = { -1, 0, 0, 1 };
	static int[] dx = { 0, -1, 1, 0 };
	public static void main(String[] args) throws IOException {

		init();
		int time = 0;
		while (!queue.isEmpty()) {
			time++;
			step1();
			if (step2()) {
				System.out.println(time);
				System.exit(0);
			}
		}
		System.out.println("IMPOSSIBLE");
	}

	private static void step1() {
		List<int[]> newList = new ArrayList<>();

		for (int[] fire : fireList) {
			for(int d = 0; d < 4; d++) {
				int ny = fire[0] + dy[d];
				int nx = fire[1] + dx[d];
				if(isRange(ny, nx) && !fireVisited[ny][nx] && map[ny][nx] != '#') {
					fireVisited[ny][nx] = true;
					newList.add(new int[]{ny, nx});
				}
			}
		}
		fireList = newList;
	}

	private static boolean step2() {

		int size = queue.size();
		for (int i = 0; i < size; i++) {
			int[] poll = queue.poll();

			for(int d = 0; d < 4; d++) {
				int ny = poll[0] + dy[d];
				int nx = poll[1] + dx[d];

				if(!isRange(ny, nx)){
					return true;
				}

				if(!visited[ny][nx] && !fireVisited[ny][nx] && map[ny][nx] == '.') {
					visited[ny][nx] = true;
					queue.add(new int[] {ny, nx});
				}
			}
		}

		return false;
	}

	private static boolean isRange(int y, int x) {
		return 0<= y && y < n && 0 <= x && x < m;
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		visited = new boolean[n][m];
		fireVisited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'J') {
					src[0] = i;
					src[1] = j;
				}
				if (map[i][j] == 'F') {
					fireList.add(new int[] { i, j });
					fireVisited[i][j] = true;
				}
			}
		}

		queue.add(new int[] {src[0], src[1], 0});
		visited[src[0]][src[1]] = true;
	}
}

