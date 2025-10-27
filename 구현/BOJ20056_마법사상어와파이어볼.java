import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ20056_마법사상어와파이어볼 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static Deque<Node>[][] ball;	// n번 이동 후 남아있는 볼
	static Deque<Node>[][] movedBall;	//step1 에서 이동될 공들

	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	static int N, M, K;

	public static void main(String[] args) throws IOException {

		init();
		while (K-- > 0) {
			step1();
			step2();
		}

		// 마법사 상어가 이동을 K번 명령한 후, 남아있는 파이어볼 질량의 합
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				while (!ball[i][j].isEmpty()) {
					Node node = ball[i][j].poll();
					result += node.m;
				}
			}
		}
		System.out.println(result);

	}

	//1. 모든 파이어볼이 자신의 방향 di로 속력 si칸 만큼 이동한다.
	private static void step1() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				while (!ball[i][j].isEmpty()) {
					Node poll = ball[i][j].poll();
					move(poll, i, j);
				}
			}
		}
	}
	//2. 이동이 모두 끝난 뒤, 2개 이상의 파이어볼이 있는 칸에서는 다음과 같은 일이 일어난다.
	private static void step2() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 이동 된 칸에 볼이 하나라면 안합쳐도 됨.
				if (movedBall[i][j].size() == 1) {
					ball[i][j].add(movedBall[i][j].poll());
				}
				// 2개 이상 있으면 같은 칸에 있는 파이어볼은 모두 하나로 합쳐진다.
				else if (movedBall[i][j].size() >= 2) {
					balance(i, j);
				}
			}
		}
	}


	private static void balance(int y, int x) {
		boolean odd = false;
		boolean even = false;
		int m = 0;
		int s = 0;
		int d;
		int count = movedBall[y][x].size();

		while(!movedBall[y][x].isEmpty()){
			Node node = movedBall[y][x].poll();
			if (node.d % 2 == 0) {
				even = true;
			} else {
				odd = true;
			}
			m += node.m;
			s += node.s;
		}

		int newM = m / 5;
		int newS = s / count;
		if(newM == 0) return;	//질량 0 : 사라짐

		if (odd && even) {
			d = 1;    // 1,3,5,7
		} else {
			d = -1; // 0,2,4,6
		}

		if (d == 1) {
			ball[y][x].add(new Node(newM, newS, 1));
			ball[y][x].add(new Node(newM, newS, 3));
			ball[y][x].add(new Node(newM, newS, 5));
			ball[y][x].add(new Node(newM, newS, 7));
		}else{
			ball[y][x].add(new Node(newM, newS, 0));
			ball[y][x].add(new Node(newM, newS, 2));
			ball[y][x].add(new Node(newM, newS, 4));
			ball[y][x].add(new Node(newM, newS, 6));
		}
	}

	private static void move(Node ball, int y, int x) {
		int ny = ((y + (dy[ball.d] * ball.s) % N) + N) % N;
		int nx = ((x + (dx[ball.d] * ball.s) % N) + N) % N;
		movedBall[ny][nx].add(ball);
	}


	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		ball = new Deque[N][N];
		movedBall = new Deque[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ball[i][j] = new ArrayDeque<>();
				movedBall[i][j] = new ArrayDeque<>();
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			ball[r][c].add(new Node(m, s, d));
		}
	}

	// [r][c] 에 있는 ball 정보
	static class Node {
		int m, s, d;

		public Node(int m, int s, int d) {
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

}
