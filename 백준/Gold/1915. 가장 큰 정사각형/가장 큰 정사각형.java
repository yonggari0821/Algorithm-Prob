import java.io.*;
import java.util.*;
public class Main {
    static StringBuilder ans = new StringBuilder();
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] grid = new int[N][M];
        int[][] DP = new int[N][M];
        int bsq = 0; // 가장 큰 정사각형의 한 변 길이 default==0
        boolean flag = false; // 1 등장하나?
        // 입력받기 (구분자 개별 숫자로 설정)
        for (int r = 0; r < N; r++)
        {
            st = new StringTokenizer(br.readLine(), "1234567890", true);
            for (int c = 0; c < M; c++)
            {
                grid[r][c] = Integer.parseInt(st.nextToken());
                if (grid[r][c] == 1) flag = true;
            }
        }
        if (N == 1 || M == 1)
        {
            if (flag) System.out.println(1);
            else System.out.println(0);
            return ;
        }
        // 입력값 배열 출력용
//        for (int r = 0; r < N; r++)
//        {
//            for (int c = 0; c < M; c++) System.out.printf("%d", grid[r][c]);
//            System.out.println("");
//        }
//        System.out.println("");
        // 맨 가장자리 줄 설정
        DP[0][0] = grid[0][0];
        bsq = bsq > DP[0][0] ? bsq : DP[0][0];
        for (int c = 1; c < M; c++)
        {
            DP[0][c] = grid[0][c];
            bsq = bsq > DP[0][c] ? bsq : DP[0][c];
        }
        for (int r = 1; r < N; r++)
        {
            DP[r][0] = grid[r][0];
            bsq = bsq > DP[r][0] ? bsq : DP[r][0];
        }

        // 나머지 설정
        for (int r = 1; r < N; r++)
        {
            for (int c = 1; c < M; c++)
            {
                if (grid[r][c] != 0)
                {
                    DP[r][c] = grid[r][c] + Math.min(DP[r - 1][c - 1], Math.min(DP[r - 1][c], DP[r][c - 1]));
                    bsq = bsq > DP[r][c] ? bsq : DP[r][c]; // 가장 큰 정사각형 한 변의 길이 리뉴얼
                }
            }
        }
        // DP 배열 출력용
//        for (int r = 0; r < N; r++)
//        {
//            for (int c = 0; c < M; c++) System.out.printf("%d", DP[r][c]);
//            System.out.println("");
//        }
        ans.append(bsq * bsq);
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}
