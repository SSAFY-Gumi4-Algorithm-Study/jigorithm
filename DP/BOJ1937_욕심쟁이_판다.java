import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] dy = {-1 ,0, 0, 1};
    static int[] dx = {0, -1, 1, 0};

    static int N;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        map =new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j< N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i=0; i < N; i++){
            for(int j=0; j< N; j++){
                dfs(i,j);
            }
        }

        int max = 0;

        for (int i=0; i < N; i++){
            for(int j=0; j< N; j++){
                max = Math.max(max, dp[i][j]);
            }
        }

        System.out.println(max);
    }

    private static int dfs(int y,int x){
        if(dp[y][x] != 0) return dp[y][x];
        dp[y][x] = 1;

        for(int d = 0; d < 4; d++){
            int ny = y + dy[d];
            int nx = x + dx[d];
            if(isRange(ny, nx) && map[y][x] < map[ny][nx]){
                dp[y][x] = Math.max(dp[y][x], dfs(ny, nx) + 1);
            }
        }
        return dp[y][x];
    }

    private static boolean isRange(int y, int x){
        return 0 <= y && y < N && 0<= x && x < N;
    }
}
