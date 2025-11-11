import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int T, N, M;
    static char[][] map;
    static HashSet<Character> set;

    static int[] dy = {-1, 0, 0, 1};
    static int[] dx = {0, -1, 1, 0};
    static int count;

    public static void main(String[] args) throws IOException {

        T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            init();

            while (true) {
                if(!bfs()) break;
            }

            sb.append(count).append("\n");
        }

        System.out.println(sb.toString());

    }

    private static boolean bfs() {
        boolean is = false;

        boolean[][] visited = new boolean[N + 2][M + 2];

        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{0, 0});
        visited[0][0] = true;

        while (!que.isEmpty()) {
            int[] poll = que.poll();
            int y = poll[0];
            int x = poll[1];

            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];

                if(!isRange(ny, nx) || visited[ny][nx] || map[ny][nx] == '*') continue;

                if(map[ny][nx] == '.'){
                    visited[ny][nx] = true;
                    que.add(new int[]{ny, nx});
                }else if(Character.isUpperCase(map[ny][nx])){
                    if(set.contains(map[ny][nx])){
                        que.add(new int[]{ny, nx});
                        map[ny][nx] = '.';
                        visited[ny][nx] = true;
                        is = true;
                    }
                }else if(Character.isLowerCase(map[ny][nx])){
                    que.add(new int[]{ny, nx});
                    set.add(Character.toUpperCase(map[ny][nx]));
                    visited[ny][nx] = true;
                    map[ny][nx] = '.';
                    is = true;
                }else if(map[ny][nx] == '$'){
                    que.add(new int[]{ny, nx});
                    visited[ny][nx] = true;
                    map[ny][nx] = '.';
                    count++;
                }
            }
        }

        return is;
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N + 2 && 0 <= x && x < M + 2;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N + 2][M + 2];
        set = new HashSet<>();
        count = 0;

        for (int i = 0; i < N + 2; i++) {
            Arrays.fill(map[i], '.');
        }

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i + 1][j + 1] = str.charAt(j);
            }
        }

        String keys = br.readLine();
        if (keys.charAt(0) != '0') {
            for (int i = 0; i < keys.length(); i++) {
                set.add(Character.toUpperCase(keys.charAt(i)));
            }
        }
    }
}

