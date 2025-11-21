import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int[] parent;
    static int N, M;
    static int cnt = 0;

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            int value = Integer.parseInt(br.readLine());

            if(find(value) == value){
                parent[value] = parent[value - 1];
                cnt++;
            }else{
                int x = find(value);
                if(x != 0){
                    parent[x] = parent[x - 1];
                    cnt++;
                }else{
                    break;
                }
            }
        }

        System.out.println(cnt);

    }

    static int find(int x){
        if(parent[x] == x){
            return x;
        }
        return parent[x] = find(parent[x]);
    }
}

