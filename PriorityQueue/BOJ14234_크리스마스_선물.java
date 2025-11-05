import java.util.*;
import java.io.*;


public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int iter = Integer.parseInt(st.nextToken());
            if(iter == 0){
                if(pq.isEmpty()){
                    sb.append(-1).append("\n");
                }else{
                    sb.append(pq.poll()).append("\n");
                }
            }else{
                while (st.hasMoreTokens()) {
                    int value = Integer.parseInt(st.nextToken());
                    pq.add(value);
                }
            }
        }

        System.out.println(sb.toString());
    }
}
