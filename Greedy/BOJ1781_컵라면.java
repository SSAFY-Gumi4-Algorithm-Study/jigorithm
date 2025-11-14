import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int[] parent;

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());
        parent = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }


        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.count == o2.count) {
                return Integer.compare(o1.deadline, o2.deadline);
            }
            return Integer.compare(o2.count, o1.count);
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            pq.add(new Node(deadline, count));
        }

        long cnt = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if(parent[node.deadline] == node.deadline) {
                cnt += node.count;
                parent[node.deadline] = node.deadline - 1;
            }else{
                int pos = dfs(node.deadline);
                if(pos == 0) continue;
                parent[pos] = pos - 1;
                cnt += node.count;
            }
        }

        System.out.println(cnt);
    }

    static private int dfs(int cur) {
        if(parent[cur] == cur){
            return cur;
        }

        return parent[cur] = dfs(parent[cur]);
    }

    static class Node {
        int deadline, count;

        public Node(int deadline, int count) {
            this.deadline = deadline;
            this.count = count;
        }
    }
}

