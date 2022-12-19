package B14852IDPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static long[][] dp;

    public static long get_tile(int n) {
        if (n == 0) return 1;

        dp[0][0] = 0;
        dp[1][0] = 2;
        dp[2][0] = 7;
        dp[2][1] = 1;

        if (dp[n][0] != 0) return dp[n][0];

        for (int i = 3; i <= n; i++)
        {
            dp[i][1] = (dp[i - 1][1] + dp[i - 3][0]) % 1000000007;
            //
            dp[i][0] = (3 * dp[i - 2][0] + 2 * dp[i - 1][0] + 2 * dp[i][1]) % 100000007;
            // (i - 1 번째까지의 경우의수 x 2) + (i - 2 번째까지의 경우의 수 x 3)
            // + (i - 3 번째부터 마지막까지의 경우의 수들의 합) * 2 <= 이 부분이 dp[i][1]
        }

        return dp[n][0];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        if (N == 0)
        {
            System.out.println(0);
            return ;
        }
        else if (N == 1)
        {
            System.out.println(2);
        }
        else {
            dp = new long[N + 1][2];

            System.out.println(get_tile(N));
        }
    }
}
