import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int[] alpha = new int[26];

		String str =  br.readLine();

		int odd = 0;
		int even = 0;

		for(int i = 0; i < str.length(); i++) {
			alpha[str.charAt(i) - 'A']++;
		}

		for(int i = 0; i < 26; i++) {
			if(alpha[i] % 2 == 0) {
				even++;
			}else{
				odd++;
			}
		}

		if(odd > 1){
			System.out.println("I'm Sorry Hansoo");
			System.exit(0);
		}

		StringBuilder sb1 =  new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		StringBuilder sb3 = new StringBuilder();

		for(int i = 0; i < 26; i++) {
			while(alpha[i] / 2 > 0) {
				sb1.append((char)(i + 'A'));
				sb3.append((char)(i + 'A'));
				alpha[i]--;
				alpha[i]--;
			}
			if(alpha[i] == 1) {
				sb2.append((char)(i + 'A'));
			}
		}

		System.out.println(sb1.toString() + sb2.toString() + sb3.reverse().toString());


	}

}

