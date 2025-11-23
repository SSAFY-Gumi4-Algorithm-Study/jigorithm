import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {

        String line;
        while ((line = br.readLine()) != null) {
            st = new StringTokenizer(line);

            if (st.countTokens() <= 1) break;

            int n = Integer.parseInt(st.nextToken());
            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            SegTree tree = new SegTree(arr);
            sb.append(tree.solve(0, n - 1)).append("\n");
        }

        System.out.print(sb);
    }
}

class SegTree {
    private final int size;
    private final int[] heights;
    private final int[] segTree;
    private final int OUT = 0x7fffffff;

    public SegTree(int[] arr) {
        this.size = arr.length;
        this.heights = arr.clone();
        this.segTree = new int[size << 2];

        build(0, size - 1, 1);
    }

    // 최소높이의 인덱스를 저장함.
    private int build(int left, int right, int node) {
        if (left == right) return segTree[node] = left;

        int mid = (left + right) >> 1;
        int l = build(left, mid, node << 1);
        int r = build(mid + 1, right, (node << 1) + 1);

        if (heights[l] < heights[r]) {
            segTree[node] = l;
        } else {
            segTree[node] = r;
        }

        return segTree[node];
    }

    // 구간 내에서 최소 높이를 가지는 인덱스를 반환.
    private int query(int ql, int qr, int node, int nl, int nr) {
        if (qr < nl || nr < ql) return OUT;
        if (ql <= nl && nr <= qr) return segTree[node];

        int mid = (nl + nr) >> 1;
        int l = query(ql, qr, node << 1, nl, mid);
        int r = query(ql, qr, (node << 1) + 1, mid + 1, nr);

        if (l == OUT) return r;
        if (r == OUT) return l;
        return heights[l] < heights[r] ? l : r;
    }

    //
    public long solve(int left, int right) {
        int minIdx = query(left, right, 1, 0, size - 1);
        long extent = (long) heights[minIdx] * (right - left + 1);

        if (left <= minIdx - 1) {
            extent = Math.max(extent, solve(left, minIdx - 1));
        }
        if (minIdx + 1 <= right) {
            extent = Math.max(extent, solve(minIdx + 1, right));
        }

        return extent;
    }
}

