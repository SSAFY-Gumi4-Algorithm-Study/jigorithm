import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static char[] str;

    public static void main(String[] args) throws Exception {

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            str = br.readLine().toCharArray();

            sb.append(dfs(0, str.length - 1, false)).append("\n");
        }

        System.out.println(sb.toString());

    }


    private static int dfs(int l, int r, boolean is) {
        int ans = 2;

        if(l >= r && is){
            return 1;
        }else if(l >= r && !is){
            return 0;
        }

        if(is && str[l] != str[r]) return 2;

        if(str[l] == str[r]){
            ans = Math.min(ans, dfs(l + 1, r - 1, is));
        }else{
            if(!is){
                ans = Math.min(ans, dfs(l + 1, r, true));
                ans = Math.min(ans, dfs(l, r - 1, true));
            }
        }

        return ans;
    }
}

