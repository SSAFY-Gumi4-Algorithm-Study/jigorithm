import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N, M;

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());
		int[] arr = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		SegmentTree segmentTree = new SegmentTree(N);
		segmentTree.init(arr, 1, 1, N);

		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			/**
			 * diff == 원래값 : 짝수 , 바꿀 값 : 짝수 -> 1
			 * diff == 원래값 : 홀수 , 바꿀 값 : 짝수 -> 2
			 * diff == 원래값 : 짝수 , 바꿀 값 : 홀수 -> 3
			 * diff == 원래값 : 홀수 , 바꿀 값 : 홀수 -> 4
			 */
			if (a == 1) {
				if (arr[b] % 2 == 0 && c % 2 == 0) {
					segmentTree.update(1, 1, N, b, 1);
				}else if(arr[b] % 2 == 1 && c % 2 == 0) {
					segmentTree.update(1, 1, N, b, 2);
				}else if(arr[b] % 2 == 0 && c % 2 == 1) {
					segmentTree.update(1, 1, N, b, 3);
				}else{
					segmentTree.update(1, 1, N, b, 4);
				}
				arr[b] = c;
			}
			else if (a == 2) {
				int[] query = segmentTree.query(1, 1, N, b, c);
				sb.append(query[0]).append("\n");
			}else if(a == 3) {
				int[] query = segmentTree.query(1, 1, N, b, c);
				sb.append(query[1]).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	static class SegmentTree {

		int[][] tree;

		public SegmentTree(int n) {
			tree = new int[n * 4][2];
		}

		public int[] init(int[] arr, int node, int start, int end) {
			if (start == end) {
				if (arr[start] % 2 == 0) {
					tree[node][0] = 1;
					return new int[] {1, 0};
				} else {
					tree[node][1] = 1;
					return new int[] {0, 1};
				}
			}

			int mid = (start + end) / 2;
			int[] left = init(arr, node * 2, start, mid);
			int[] right = init(arr, node * 2 + 1, mid + 1, end);

			tree[node][0] = left[0] + right[0];
			tree[node][1] = left[1] + right[1];

			return new int[] {tree[node][0], tree[node][1]};
		}

		/**
		 * diff == 원래값 : 짝수 , 바꿀 값 : 짝수 -> 1
		 * diff == 원래값 : 홀수 , 바꿀 값 : 짝수 -> 2
		 * diff == 원래값 : 짝수 , 바꿀 값 : 홀수 -> 3
		 * diff == 원래값 : 홀수 , 바꿀 값 : 홀수 -> 4
		 */
		public void update(int node, int start, int end, int idx, int diff) {
			if (idx < start || end < idx)
				return;

			if (diff == 1 || diff == 4)
				return;

			if (diff == 2) {
				tree[node][0]++;
				tree[node][1]--;
			} else if (diff == 3) {
				tree[node][0]--;
				tree[node][1]++;
			}

			if (start != end) {
				int mid = (start + end) / 2;
				update(node * 2, start, mid, idx, diff);
				update(node * 2 + 1, mid + 1, end, idx, diff);
			}

		}

		public int[] query(int node, int start, int end, int left, int right) {
			if (end < left || right < start)
				return new int[] {0, 0};
			if (left <= start && end <= right)
				return tree[node];

			int mid = (start + end) / 2;

			int[] l = query(node * 2, start, mid, left, right);
			int[] r = query(node * 2 + 1, mid + 1, end, left, right);

			return new int[] {l[0] + r[0], l[1] + r[1]};
		}

	}
}

