import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int[] dy = {-1, 0, 0, 1};
	static int[] dx = {0, -1, 1, 0};

	static int[][] map;
	static int N;
	static int[][] arr;
	static PriorityQueue<Node> pq;
	static int[][] sc;

	public static void main(String[] args) throws IOException {

		pq = new PriorityQueue<>((o1, o2) -> {
			if (o1.preferCount == o2.preferCount) {
				if (o1.emptyCount == o2.emptyCount) {
					if (o1.y == o2.y) {
						return Integer.compare(o1.x, o2.x);
					}
					return Integer.compare(o1.y, o2.y);
				}
				return Integer.compare(o2.emptyCount, o1.emptyCount);
			}
			return Integer.compare(o2.preferCount, o1.preferCount);
		});

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		arr = new int[N * N][4];
		sc = new int[N * N + 1][4];

		for (int i = 0; i < N * N; i++) {
			pq.clear();
			st = new StringTokenizer(br.readLine());

			int num = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 4; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				sc[num][j] = arr[i][j];
			}

			// System.out.println();
			simulation(i);
			Node poll = pq.poll();
			map[poll.y][poll.x] = num;
			// print();
		}

		System.out.println(getScore());
	}

	private static int getScore() {
		int score = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int count = getPreferCount2(i, j, map[i][j]);
				// System.out.println(map[i][j] + " " + count);
				if (count == 4)
					score += 1000;
				else if (count == 3)
					score += 100;
				else if (count == 2)
					score += 10;
				else if (count == 1)
					score += 1;
			}
		}

		return score;
	}

	private static void print() {
		for (int i = 0; i < N; i++) {
			System.out.println();
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
		}
	}

	private static void simulation(int p) {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0)
					continue;

				int preferCount = getPreferCount(i, j, p);
				int emptyCount = getEmptyCount(i, j);

				pq.add(new Node(i, j, preferCount, emptyCount));
			}
		}
	}

	private static int getEmptyCount(int y, int x) {
		int count = 0;
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if (isRange(ny, nx)) {
				if (map[ny][nx] == 0)
					count++;
			}
		}

		return count;
	}

	private static int getPreferCount2(int y, int x, int p) {
		int count = 0;
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if (isRange(ny, nx)) {
				if (map[ny][nx] == sc[p][0] ||
					map[ny][nx] == sc[p][1] ||
					map[ny][nx] == sc[p][2] ||
					map[ny][nx] == sc[p][3])
					count++;
			}
		}
		return count;
	}

	private static int getPreferCount(int y, int x, int p) {
		int count = 0;
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if (isRange(ny, nx)) {
				if (map[ny][nx] == arr[p][0] ||
					map[ny][nx] == arr[p][1] ||
					map[ny][nx] == arr[p][2] ||
					map[ny][nx] == arr[p][3])
					count++;
			}
		}
		return count;
	}

	private static boolean isRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	static class Node {
		int y, x;
		int preferCount;
		int emptyCount;

		public Node(int y, int x, int preferCount, int emptyCount) {
			this.y = y;
			this.x = x;
			this.preferCount = preferCount;
			this.emptyCount = emptyCount;
		}
	}
}
