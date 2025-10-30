import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int[] degree;
	static List<Integer>[] graph;
	static HashMap<String, Integer> map = new HashMap<>();
	static HashMap<Integer, String> reverseMap = new HashMap<>();
	static TreeMap<String, PriorityQueue<String>> treeMap = new TreeMap<>((o1, o2) -> o1.compareTo(o2));

	public static void main(String[] args) throws IOException {

		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		int idx = 0;
		for (int i = 0; i < N; i++) {
			String str = st.nextToken();
			treeMap.put(str, new PriorityQueue<>((o1, o2) -> {
				return o1.compareTo(o2);
			}));
			map.put(str, idx);
			reverseMap.put(idx, str);
			idx++;
		}

		degree = new int[idx];
		graph = new ArrayList[idx];
		for (int i = 0; i < idx; i++) {
			graph[i] = new ArrayList<>();
		}

		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String a = st.nextToken();
			String b = st.nextToken();
			Integer aIdx = map.get(a);
			Integer bIdx = map.get(b);
			graph[bIdx].add(aIdx);
			degree[aIdx]++;
		}

		Queue<Integer> que = new LinkedList<>();
		PriorityQueue<String> pqResult = new PriorityQueue<>((o1, o2) -> {
			return o1.compareTo(o2);
		});

		for (int i = 0; i < N; i++) {
			if (degree[i] == 0) {
				que.add(i);
				pqResult.add(reverseMap.get(i));
			}
		}

		sb.append(pqResult.size()).append("\n");

		while (!pqResult.isEmpty()) {
			sb.append(pqResult.poll() + " ");
		}
		sb.append("\n");

		while (!que.isEmpty()) {
			Integer poll = que.poll();

			for (Integer i : graph[poll]) {
				degree[i]--;
				if (degree[i] == 0) {
					que.add(i);
					pqResult.add(reverseMap.get(i));
					PriorityQueue<String> pq = treeMap.get(reverseMap.get(poll));
					pq.add(reverseMap.get(i));
				}
			}
		}

		for (String key : treeMap.keySet()) {
			PriorityQueue<String> pq = treeMap.get(key);
			sb.append(key + " " + pq.size());
			while(!pq.isEmpty()) {
				sb.append(" " + pq.poll());
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());

	}
}
