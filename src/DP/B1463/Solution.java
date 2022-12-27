package B1463;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

    public static int[] dp;
    public static int DP(int N)
    {
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;
        if (dp[N] != 0) return dp[N];
        for (int i = 4; i <= N; i++)
        {
            if (i % 3 == 0 && i % 2 == 0)
                dp[i] = dp[i/3] > dp[i/2] ? dp[i/2] + 1 : dp[i/3] + 1;
            else if (i % 3 == 0)
                dp[i] = dp[i/3] > dp[i - 1] ? dp[i - 1] + 1 : dp[i/3] + 1;
            else if (i % 2 == 0)
                dp[i] = dp[i/2] > dp[i - 1] ? dp[i - 1] + 1 : dp[i/2] + 1;
            else
                dp[i] = dp[i-1] + 1;
        }
        //System.out.println(Arrays.toString(dp));
        return dp[N];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];

        if (N == 1)
        {
            System.out.println(0);
            return ;
        }

        else if (N == 2)
        {
            System.out.println(1);
            return ;
        }

        System.out.println(DP(N));
        br.close();
    }
}
