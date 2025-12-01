import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws Exception {

        int i = 1;
        while(true){
            String str = br.readLine();
            if(str.charAt(0) == '-') break;
            sb.append(i++).append(". ").append(stack(str)).append("\n");
        }

        System.out.println(sb.toString());
    }


    private static int stack(String str) {
        int cnt = 0;
        Stack<Character> stack1 = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            if(stack1.isEmpty()) {
                stack1.push(str.charAt(i));
                continue;
            }

            if (str.charAt(i) == '{') {
                stack1.push(str.charAt(i));
            } else {
                if (stack1.peek() == '}') {
                    stack1.push(str.charAt(i));
                } else {
                    stack1.pop();
                }
            }
        }

        while (!stack1.isEmpty()) {
            char c1 = stack1.pop();
            char c2 = stack1.pop();

            cnt += isCnt(c1, c2);
        }

        return cnt;
    }


    private static int isCnt(char c1, char c2) {
        if(c1 == '{' && c2 == '{') return 1;
        if(c1 == '}' && c2 == '}') return 1;
        if(c1 == '{' && c2 == '}') return 2;

        return 0;
    }

}
/**
 * }{{{
 * ((((
 * ))))
 * )(
 *
 */
