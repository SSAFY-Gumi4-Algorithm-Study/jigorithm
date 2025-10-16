import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static List<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		int n =  Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());

		int len = 0;
		for (int i = 0; i < n; i++) {
			int tar = Integer.parseInt(st.nextToken());
			int idx = lowerBound(tar, len);
			if(idx == len){
				list.add(tar);
				len++;
			}else{
				list.set(idx, tar);
			}
		}

		System.out.println(Arrays.toString(list.toArray()));
		System.out.println(n - list.size());

	}

	private static int lowerBound(int tar, int len) {

		int l = 0;
		int r = len;

		while (l < r) {
			int mid = (l + r ) / 2;

			if(tar <= list.get(mid)) {
				r = mid;
			}else{
				l = mid + 1;
			}
		}

		return l;

	}

}

