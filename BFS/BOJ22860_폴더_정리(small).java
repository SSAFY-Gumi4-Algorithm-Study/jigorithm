import java.util.*;
import java.io.*;

public class Main {

	static class Node {
		List<String> forder = new ArrayList<>();
		List<String> files = new ArrayList<>();
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N, M, Q;

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		HashMap<String, Node> map = new HashMap<>();

		for (int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());
			String key = st.nextToken();
			String value = st.nextToken();
			int is = Integer.parseInt(st.nextToken());
			if(!map.containsKey(key)) {
				map.put(key, new Node());
			}
			if (is == 0) {
				map.get(key).files.add(value);
			} else {
				map.get(key).forder.add(value);

				if(!map.containsKey(value)) {
					map.put(value, new Node());
				}
			}
		}

		Q = Integer.parseInt(br.readLine());
		for (int i = 0; i < Q; i++) {
			HashSet<String> set = new HashSet<>();
			int size = 0;
			String[] str = br.readLine().split("/");

			Queue<Node> queue = new LinkedList<>();
			queue.offer(map.get(str[str.length - 1]));

			while (!queue.isEmpty()) {
				Node node = queue.poll();
				for(String forder : node.forder) {
					if(map.containsKey(forder)) {
						queue.offer(map.get(forder));
					}
				}

				for(String file : node.files) {
					set.add(file);
					size++;
				}
			}
			sb.append(set.size()).append(" ").append(size).append("\n");
		}

		System.out.println(sb);

	}

}


