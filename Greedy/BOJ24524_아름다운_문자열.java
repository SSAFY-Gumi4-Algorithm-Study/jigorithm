import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int cnt = 0;

	public static void main(String[] args) throws Exception {

		Queue<Integer>[] que = new Queue[26];
		for(int i=0; i< 26; i++) {
			que[i] = new LinkedList<>();
		}
	
		String str1 = br.readLine();
		int len1 = str1.length();
		
		for(int i=0; i< len1; i++) {
			char c = str1.charAt(i);
			que[c - 'a'].add(i);
		}
		
		char[] str2 = br.readLine().toCharArray();
		int len2 = str2.length;
		
		out:while(true) {
			int beforeIdx = -1;
			
			con:for(int i = 0; i< len2; i++) {
				
				while(!que[str2[i] - 'a'].isEmpty()) {
					int idx = que[str2[i] - 'a'].poll();
					if(beforeIdx < idx) {
						beforeIdx = idx;
						continue con;
					}else {
						continue;
					}
				}
				
				break out;
			}
			
			cnt++;
		}
		
		System.out.println(cnt);
		
	}

}

