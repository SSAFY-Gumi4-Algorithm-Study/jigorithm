import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static HashMap<Character, Integer> map = new HashMap<>();
    static int[] alpha = new int[500];
    static char[] arr;
    static int N, M;
    static int cnt = 0;

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        String str = br.readLine();
        for (int i=0; i< N; i++){
            char c = str.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }


        arr = br.readLine().toCharArray();
        for (int i=0; i< N; i++){
            alpha[arr[i]]++;
        }

        if(isValid()){
            cnt++;
        }

        int l = 0;
        for (int r = N; r < M; r++){
            alpha[arr[l++]]--;
            alpha[arr[r]]++;
            if(isValid()){
                cnt++;
            }
        }

        System.out.println(cnt);

    }

    private static boolean isValid(){
        for (Character c : map.keySet()) {
            int cnt = map.get(c);
            if(cnt != alpha[c]) return false;
        }

        return true;
    }
}
