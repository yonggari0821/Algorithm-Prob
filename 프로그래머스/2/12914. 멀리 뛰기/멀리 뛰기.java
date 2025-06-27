import java.util.*;

/*
    기본적인 dp 문제
    
    dp[1] = 1;
    dp[2] = 2;
    dp[3] = 3;
    dp[4] = 5;
    dp[5] = 8;
    .
    .
    .
    dp[n] = dp[n - 1] + dp[n - 2];
*/

class Solution {
    public long solution(int n) {
        long answer = 0;
        
        long[] dp = new long[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
            dp[i] %= 1234567;
        }
        return dp[n];
    }
}