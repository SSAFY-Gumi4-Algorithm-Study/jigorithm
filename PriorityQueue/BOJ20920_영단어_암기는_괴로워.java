import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		PriorityQueue<Node> pq = new PriorityQueue<>();
		HashMap<String, Integer> map = new HashMap<>();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			if(str.length() >= M){
				map.put(str, map.getOrDefault(str, 0) + 1);
			}
		}

		for (String s : map.keySet()) {
			pq.add(new Node(s, map.get(s)));
		}

		int size = pq.size();
		for (int i = 0; i < size; i++) {
			sb.append(pq.poll().str).append("\n");
		}
		System.out.println(sb.toString());
	}

	static class Node implements Comparable<Node> {

		String str;
		int count;

		public Node(String str, int count) {
			this.str = str;
			this.count = count;
		}

		@Override
		public int compareTo(Node o) {
			if (this.count == o.count) {
				if (this.str.length() == o.str.length()) {
					return this.str.compareTo(o.str);
				}
				return Integer.compare(o.str.length(), this.str.length());
			}
			return Integer.compare(o.count, this.count);
		}

	}

}


