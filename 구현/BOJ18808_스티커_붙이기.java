import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, M, K;
    static int[][] map;
    static int[][] sticker;

    public static void main(String[] args) throws IOException {


        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int k = 0; k < K; k++) {
            st = new  StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            sticker = new int[R][C];

            for (int i = 0; i < R; i++) {
                st = new  StringTokenizer(br.readLine());
                for (int j = 0; j < C; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 시계방향으로 4번 돌릴 예정
            for (int i = 0; i < 4; i++) {
                //그릴수있다면 그 다음 스티커로
                if(isCheck()){
                    break;
                }
                //못그리면 90도 시계방향으로 회전
                else{
                    sticker = rotate(sticker, sticker.length, sticker[0].length);
                }
            }
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 1) count++;
            }
        }

        System.out.println(count);
    }

    // 모든 범위 탐색 -> 그림 그릴 수 있는지 없는지 체크
    private static boolean isCheck() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                //범위 체크
                if(i + sticker.length <= N && j + sticker[0].length <= M) {
                    
                    //그릴수 있으면 그리고 끝냄
                    if(isValid(i, j, sticker.length, sticker[0].length)){
                        graw(i, j, sticker.length, sticker[0].length);
                        return true;
                    }
                }
            }
        }

        //못그림
        return false;
    }

    
    //그리기 
    private static void graw(int r, int c, int lenR, int lenC) {
        for (int i = r, a = 0; i < r + lenR; i++, a++) {
            for (int j = c, b= 0 ; j < c + lenC; j++, b++) {
                if(sticker[a][b] ==1){
                    map[i][j] = 1;
                }
            }
        }
    }

    //특정 범위에 대해서 그릴수 있는지 체크
    private static boolean isValid(int r, int c, int lenR, int lenC) {
        for (int i = r, a = 0; i < r + lenR; i++, a++) {
            for (int j = c, b= 0 ; j < c + lenC; j++, b++) {
                if(sticker[a][b] == 1){
                    if(map[i][j] == 1) return false;
                }
            }
        }
        return true;
    }


    //시계방향 90도 돌리기 
    private static int[][] rotate(int[][] arr, int r, int c) {
        int[][] temp = new int[c][r];

        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                temp[i][j] = arr[r - j - 1][i];
            }
        }

        return temp;
    }


}

