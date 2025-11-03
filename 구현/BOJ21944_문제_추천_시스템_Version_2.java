import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static TreeSet<Integer>[][] problem = new TreeSet[101][101];    //난이도, 알고리즘 분류
	static HashMap<Integer, int[]> map = new HashMap<>();

	public static void main(String[] args) throws IOException {

		for (int i = 1; i <= 100; i++) {
			for (int j = 1; j <= 100; j++) {
				problem[i][j] = new TreeSet<>();
			}
		}

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			add(p, l, g);
		}

		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();

			if (str.equals("recommend")) {
				int G = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				sb.append(recommend(G, x)).append("\n");
			} else if (str.equals("recommend2")) {
				int x = Integer.parseInt(st.nextToken());
				sb.append(recommend2(x)).append("\n");
			} else if (str.equals("recommend3")) {
				int x = Integer.parseInt(st.nextToken());
				int L = Integer.parseInt(st.nextToken());
				int value = recommend3(L, x);
				if (value == -1 || value == 1000000) {
					sb.append(-1).append("\n");
				} else {
					sb.append(value).append("\n");
				}
			} else if (str.equals("add")) {
				int P = Integer.parseInt(st.nextToken());
				int L = Integer.parseInt(st.nextToken());
				int G = Integer.parseInt(st.nextToken());
				add(P, L, G);
			} else if (str.equals("solved")) {
				int P = Integer.parseInt(st.nextToken());
				solved(P);
			}
		}
		System.out.println(sb.toString());

	}

	private static int recommend(int G, int x) {
		int num = -1;

		if (x == 1) {
			for (int i = 1; i <= 100; i++) {
				if (!problem[i][G].isEmpty()) {
					num = problem[i][G].last();
				}
			}
		} else {
			for (int i = 100; i >= 1; i--) {
				if (!problem[i][G].isEmpty()) {
					num = problem[i][G].first();
				}
			}
		}

		return num;
	}

	private static int recommend2(int x) {
		int[] num;

		if (x == 1) {
			num = new int[] {-1, -1};
			for (int i = 1; i <= 100; i++) {
				for (int j = 1; j <= 100; j++) {
					if (!problem[i][j].isEmpty()) {
						if(num[0] < i){
							num[0] = i;
							num[1] = problem[i][j].last();
						}
						else if (num[0] == i && num[1] < problem[i][j].last()) {
							num[1] = problem[i][j].last();
						}
					}
				}
			}
		} else {
			num = new int[] {1000000, 1000000};
			for (int i = 100; i >= 1; i--) {
				for (int j = 1; j <= 100; j++) {
					if (!problem[i][j].isEmpty()) {
						if(num[0] > i){
							num[0] = i;
							num[1] = problem[i][j].first();
						}
						else if (num[0] == i && num[1] > problem[i][j].first()) {
							num[1] = problem[i][j].first();
						}
					}
				}
			}
		}

		return num[1];
	}

	private static int recommend3(int L, int x) {
		int[] num;

		if (x == 1) {
			num = new int[] {1000000, 1000000};
			for (int i = 100; i >= L; i--) {
				for (int j = 1; j <= 100; j++) {
					if (!problem[i][j].isEmpty()) {
						if(num[0] > i){
							num[0] = i;
							num[1] = problem[i][j].first();
						}
						else if (num[0] == i && num[1] > problem[i][j].first()) {
							num[1] = problem[i][j].first();
						}
					}
				}
			}
		} else {
			num = new int[] {-1, -1};
			for (int i = 1; i <= L - 1; i++) {
				for (int j = 1; j <= 100; j++) {
					if (!problem[i][j].isEmpty()) {
						if(num[0] < i){
							num[0] = i;
							num[1] = problem[i][j].last();
						}
						if (num[0] == i && num[1] < problem[i][j].last()) {
							num[1] = problem[i][j].last();
						}
					}
				}
			}
		}

		return num[1];
	}

	private static void add(int P, int L, int G) {
		problem[L][G].add(P);
		map.put(P, new int[] {L, G});
	}

	private static void solved(int P) {
		int[] pos = map.get(P);
		map.remove(P);

		problem[pos[0]][pos[1]].remove(P);
	}

}

