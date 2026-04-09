import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int W, H;
    static char[][] map;
    static int[][][] dist;

    static int[] dy = {-1, 0, 0, 1};
    static int[] dx = {0, -1, 1, 0};

    static int sy, sx, ey, ex;
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new char[H][W];
        dist = new int[H][W][4];

        int cnt = 0;
        for (int i = 0; i < H; i++) {
            String str = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'C') {
                    if (cnt == 0) {
                        sy = i;
                        sx = j;
                    } else {
                        ey = i;
                        ex = j;
                    }
                    cnt++;
                }
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                Arrays.fill(dist[i][j], INF);
            }
        }

        Deque<int[]> dq = new ArrayDeque<>();

        for (int d = 0; d < 4; d++) {
            dist[sy][sx][d] = 0;
            dq.addFirst(new int[]{sy, sx, d});
        }

        while (!dq.isEmpty()) {
            int[] cur = dq.pollFirst();
            int y = cur[0];
            int x = cur[1];
            int dir = cur[2];

            for (int nd = 0; nd < 4; nd++) {
                int ny = y + dy[nd];
                int nx = x + dx[nd];

                if (!isRange(ny, nx) || map[ny][nx] == '*') continue;

                int cost = dist[y][x][dir];
                if (dir != nd) cost += 1;

                if (dist[ny][nx][nd] > cost) {
                    dist[ny][nx][nd] = cost;

                    if (dir == nd) {
                        dq.addFirst(new int[]{ny, nx, nd});
                    }
                    else {
                        dq.addLast(new int[]{ny, nx, nd});
                    }
                }
            }
        }

        int answer = INF;
        for (int d = 0; d < 4; d++) {
            answer = Math.min(answer, dist[ey][ex][d]);
        }

        System.out.println(answer);
    }

    static boolean isRange(int y, int x) {
        return 0 <= y && y < H && 0 <= x && x < W;
    }
}
