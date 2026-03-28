import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static int[] height;
	static boolean[] visited;
	static int min = Integer.MAX_VALUE;

	static List<Node> list = new ArrayList<>();

	public static void main(String[] args) throws Exception {

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		height = new int[N];

		for (int i = 0; i < N; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				list.add(new Node(height[i] + height[j], i, j));
			}
		}

		list.sort((o1, o2) -> Integer.compare(o1.sum, o2.sum));

		int size = list.size();
		
		for (int i = 0; i < size - 1; i++) {
			Node o1 = list.get(i);
			Node o2 = list.get(i + 1);
			
			if(isCompare(o1, o2)) {
				min = Math.min(min, Math.abs(o1.sum - o2.sum));
			}
		}
		
		System.out.println(min);

	}

	private static boolean isCompare(Node o1, Node o2) {
		if (o1.l == o2.l || o1.l == o2.r || o1.r == o2.l || o1.r == o2.r)
			return false;

		return true;
	}

	static class Node {
		int sum, l, r;

		public Node(int sum, int l, int r) {
			this.sum = sum;
			this.l = l;
			this.r = r;
		}
	}

}

