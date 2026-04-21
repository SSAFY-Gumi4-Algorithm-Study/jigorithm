import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static List<Node> list = new ArrayList<>();
    static int[][] arr = new int[1001][367];

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());

        for (int i=0; i< N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.add(new Node(a,b));
        }

        list.sort((o1, o2) -> {
            if(o1.s == o2.s){
                return Integer.compare(o2.e, o1.e);
            }
            return Integer.compare(o1.s, o2.s);
        });


        for(Node node: list){
            int start = node.s;
            int end = node.e;
            //가장 아랫 층 찾기.
            out:for (int i=1; i<= 1000; i++){
                //가장 아랫층에서부터 end까지 데이터 넣기
                for (int j = start; j <= end; j++) {
                    if(arr[i][j] == 1) continue out;
                }
                for (int j = start; j <= end; j++) {
                    arr[i][j] = 1;
                }
                break;
            }
        }

        int w = 0, h = 0;
        int sum = 0;

        out:for (int i = 1; i <= 366; i++) {

            for (int j = 1000; j >= 0; j--) {
                if(arr[j][i] == 1){
                    h = Math.max(h, j);
                    w++;
                    continue out;
                }
            }

            sum += w * h;
            w = 0;
            h = 0;

        }

        System.out.println(sum);
    }


    static class Node{
        int s, e;

        public Node(int s, int e){
            this.s = s;
            this.e = e;
        }
    }
}
