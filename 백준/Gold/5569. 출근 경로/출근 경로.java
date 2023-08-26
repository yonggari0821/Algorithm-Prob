import java.io.*;
import java.util.*;
public class Main {
    static StringBuilder ans = new StringBuilder();
    static int w, h;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken()); // 행 길이(열 갯수)
        h = Integer.parseInt(st.nextToken()); // 열 길이(행 갯수)
        // DP 배열로 진입 방향에 따른 값들을 저장해가면서 값 구하기
        int[][][] DP = new int[h+1][w+1][4]; // 마지막 칸 : 0 상상 1 상우 2 우우 3 우상
        for (int i = 2; i <= w; i++) DP[1][i][2] = 1; // 1행은 모두 우우
        for (int i = 2; i <= h; i++) DP[i][1][0] = 1; // 1열은 모두 상상
        for (int r = 2; r <= h; r++)
        {
            for (int c = 2; c <= w; c++)
            {
                DP[r][c][0] = DP[r-1][c][0] + DP[r-1][c][3]; // 상상(0) == 상상(0) or 우상(3) + 상(r-1 => r)
                if (DP[r][c][0] >= 100000) DP[r][c][0] %= 100000;
                DP[r][c][1] = DP[r][c-1][0]; // 상우(1) == 상상(0) + 우(c-1 => c) // 우상 + 우 불가!
                DP[r][c][2] = DP[r][c-1][1] + DP[r][c-1][2]; // 우우(2) == 상우(1) or 우우(2) + 우(c-1 => c)
                if (DP[r][c][2] >= 100000) DP[r][c][2] %= 100000;
                DP[r][c][3] = DP[r-1][c][2]; // 우상(3) == 우우(2) + 상(r-1 => r) // 상우 + 상 불가!
            }
        }
        ans.append((DP[h][w][0] + DP[h][w][1] + DP[h][w][2] + DP[h][w][3]) % 100000);
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}