import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int A, B, N;

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            if(o1.num == o2.num){
                return Integer.compare(o1.person, o2.person);
            }
            return Integer.compare(o1.num, o2.num);
        });

        int tkdalsLast = 0;
        int tnwlLast = 0;

        for (int i=0; i< N; i++){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            int m = Integer.parseInt(st.nextToken());
            if(c == 'B'){
                if(tkdalsLast <= t){
                    int last = 0;
                    for (int j= 0; j< m; j++){
                        last = t + A * j;
                        pq.add(new Node(0, last));
                    }
                    tkdalsLast = last + A;
                }else{
                    int last = 0;
                    for (int j= 0; j< m; j++){
                        last = tkdalsLast + A * j;
                        pq.add(new Node(0, last));
                    }
                    tkdalsLast = last + A;
                }
            }else{
                if(tnwlLast <= t){
                    int last = 0;
                    for (int j= 0; j< m; j++){
                        last = t + B * j;
                        pq.add(new Node(1, last));
                    }
                    tnwlLast = last + B;
                }else{
                    int last = 0;
                    for (int j= 0; j< m; j++){
                        last = tnwlLast + B * j;
                        pq.add(new Node(1, last));
                    }
                    tnwlLast = last + B;
                }
            }
        }

        int tkdalsCnt = 0;
        int wltnCnt = 0;
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int number = 1;

        while(!pq.isEmpty()){
            Node n = pq.poll();
            if(n.person == 0){
                tkdalsCnt++;
                sb1.append(number++).append(" ");
            }else{
                wltnCnt++;
                sb2.append(number++).append(" ");
            }
        }

        System.out.println(tkdalsCnt);
        System.out.println(sb1.toString());
        System.out.println(wltnCnt);
        System.out.println(sb2.toString());

    }

    static class Node{
        int person;
        int num;

        public Node(int person, int num){
            this.person = person;
            this.num = num;
        }
    }

}
