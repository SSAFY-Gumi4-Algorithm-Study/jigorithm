import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n;
    static int[] parent;

    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int a = Integer.parseInt(st.nextToken());
                if (i != j && a == 0) {
                    union(i, j);
                }
            }
        }

        Map<Integer, List<Integer>> groupMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = find(i);
            groupMap.computeIfAbsent(root, k -> new ArrayList<>()).add(i + 1);
        }

        List<List<Integer>> groups = new ArrayList<>(groupMap.values());

        for (List<Integer> group : groups) {
            if (group.size() == 1) {
                System.out.println(0);
                return;
            }
            Collections.sort(group);
        }

        groups.sort((a, b) -> Integer.compare(a.get(0), b.get(0)));

        StringBuilder sb = new StringBuilder();
        sb.append(groups.size()).append('\n');

        for (List<Integer> group : groups) {
            for (int person : group) {
                sb.append(person).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa != pb) {
            parent[pb] = pa;
        }
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
}