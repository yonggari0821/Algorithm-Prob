import java.io.*;
import java.util.*;
public class Main {
    static StringBuilder ans = new StringBuilder();
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        int[] stairs = new int[N+1];
        int[][] dp = new int[N+1][3];
        for (int i = 1; i <= N; i++) stairs[i] = Integer.parseInt(br.readLine());
        int tmp = 0;
        if (N < 3) for (int i = 1; i <= N; i++) tmp += stairs[i];
        else
        {
            dp[1][0] = 0;
            dp[1][1] = stairs[1];
            dp[1][2] = 0;
            for (int i = 2; i <= N; i++)
            {
                dp[i][0] = dp[i-1][2];
                dp[i][1] = Math.max(dp[i-1][0], dp[i-2][1]) + stairs[i];
                dp[i][2] = Math.max(dp[i-1][1], dp[i-2][2]) + stairs[i];
            }
            tmp = Math.max(dp[N][1], dp[N][2]);
        }
//        System.out.println(Arrays.deepToString(dp));
        ans.append(tmp);
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}

/*
연속 3칸 금지
1칸 또는 2칸씩 이동
마지막 계단은 무조건 밟아야 함

 */