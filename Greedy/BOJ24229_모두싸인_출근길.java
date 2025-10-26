import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		int N = Integer.parseInt(br.readLine());
		Node[] arr = new Node[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		Arrays.sort(arr, (o1, o2) -> {
			if (o1.s == o2.s) {
				return Integer.compare(o2.e, o1.e);
			}
			return Integer.compare(o1.s, o2.s);
		});

		List<Node> list = new ArrayList<>();
		int s = arr[0].s;
		int e = arr[0].e;

		for (int i = 1; i < N; i++) {
			if (e >= arr[i].e) {
				continue;
			}
			if (arr[i].s <= e) {
				e = arr[i].e;
				continue;
			}
			if (e < arr[i].s) {
				list.add(new Node(s, e));
				s = arr[i].s;
				e = arr[i].e;
			}
		}
		list.add(new Node(s, e));

		int[] visited = new int[list.size()];

		Queue<Integer> q = new LinkedList<>();
		q.add(0);
		visited[0] = 1;
		int max = list.get(q.peek()).e;

		while (!q.isEmpty()) {
			Integer idx = q.poll();
			int curS = list.get(idx).s;
			int curE = list.get(idx).e;
			int distance = curE - curS;
			for (int i = idx + 1; i < list.size(); i++) {
				int nextS = list.get(i).s;
				int nextE = list.get(i).e;
				if (visited[i] == 0) {
					if (nextS - curE <= distance) {
						q.add(i);
						max = Math.max(max, nextE);
						visited[i] = 1;
					}else{
						break;
					}
				}
			}
		}

		System.out.println(max);

	}

	static class Node {
		int s, e;

		public Node(int s, int e) {
			this.s = s;
			this.e = e;
		}
	}

}
