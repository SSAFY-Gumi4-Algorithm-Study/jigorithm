import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static Node[] list = new Node[9];
    static int N;

    static final int multi = 10;

    static HashMap<Integer, Boolean> map = new HashMap<>();
    static int size = 0;
    static boolean isPoint = false;
    static int point = 0;

    public static void main(String[] args) throws Exception {

        for(int i=0; i< 9; i++){
            st = new StringTokenizer(br.readLine());
            String str1 = st.nextToken();
            String str2 = st.nextToken();
            String str3 = st.nextToken();
            list[i] = new Node(s(str1), c(str2), b(str3));
        }

        combination();
        size = map.size();

        N = Integer.parseInt(br.readLine());

        for (int i=0; i< N; i++){
            st = new StringTokenizer(br.readLine());
            String str1 = st.nextToken();
            if(str1.equals("H")){
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken()) - 1;
                int sum = (int)Math.pow(10, a) + (int)Math.pow(10, b) + (int)Math.pow(10, c);
                if(map.containsKey(sum)){
                    if(!map.get(sum)){
                        map.put(sum, true);
                        point++;
                        size--;
                    }else{
                        point--;
                    }
                }else{
                    point--;
                }
            }else{
                if(size == 0 && !isPoint){
                    isPoint = true;
                    point+=3;
                }else{
                    point--;
                }
            }
        }

        System.out.println(point);


    }

    private static void combination(){
        for (int i=0; i< 9; i++){
            for(int j= i + 1; j < 9; j++){
                for(int k = j + 1; k < 9; k++){
                    if(isValidS(i, j, k) && isValidC(i, j, k) && isValidB(i, j, k)){
                        int a = (int) Math.pow(10, i);
                        int b = (int) Math.pow(10, j);
                        int c = (int) Math.pow(10, k);
                        map.put(a + b + c, false);
//                        System.out.println("a = " + i + " B = " + j + " c = " + k);
                    }
                }
            }
        }
    }

    private static boolean isValidS(int i, int j, int k){
        if(same(list[i].s, list[j].s,list[k].s) || notSame(list[i].s, list[j].s,list[k].s)) return true;
        return false;
    }

    private static boolean isValidC(int i, int j, int k){
        if(same(list[i].c, list[j].c,list[k].c) || notSame(list[i].c, list[j].c,list[k].c)) return true;
        return false;
    }

    private static boolean isValidB(int i, int j, int k){
        if(same(list[i].b, list[j].b,list[k].b) || notSame(list[i].b, list[j].b,list[k].b)) return true;
        return false;
    }

    private static boolean same(int i, int j, int k){
        if((i == j) && (j == k) && (k == i)) return true;
        return false;
    }

    private static boolean notSame(int i, int j, int k){
        if((i != j) && (j != k) && (k != i)) return true;
        return false;
    }

    private static int s(String str){
        if(str.equals("CIRCLE")){
            return 0;
        }else if(str.equals("TRIANGLE")){
            return 1;
        }else{
            return 2;
        }
    }

    private static int c(String str){
        if(str.equals("YELLOW")){
            return 0;
        }else if(str.equals("RED")){
            return 1;
        }else{
            return 2;
        }
    }

    private static int b(String str){
        if(str.equals("GRAY")){
            return 0;
        }else if(str.equals("WHITE")){
            return 1;
        }else{
            return 2;
        }
    }

    static class Node{
        int s, c, b;
        public Node(int s, int c, int b){
            this.s = s;
            this.c = c;
            this.b = b;
        }
    }
}
