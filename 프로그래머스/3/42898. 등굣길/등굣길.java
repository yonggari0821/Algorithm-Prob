import java.util.*;

class Solution {
    static final int divider = 1000000007;
    
    public int solution(int m, int n, int[][] puddles) {
        /*
        1, 1 => m, n
        */
        int[][] dp = new int[n][m];

   
        for (int r = 0; r < n; r++) dp[r][0] = 1;
        for (int c = 0; c < m; c++) dp[0][c] = 1;
        for (int i = 0; i < puddles.length; i++) {
            int r = puddles[i][1] - 1;
            int c = puddles[i][0] - 1;
            dp[r][c] = Integer.MIN_VALUE;
            if (r == 0) {
                int curC = c;
                for (int j = curC; j < m; j++) dp[0][j] = Integer.MIN_VALUE;
            }
            if (c == 0) {
                int curR = r;
                for (int j = curR; j < n; j++) dp[j][0] = Integer.MIN_VALUE;
            }
        }     
        
        for (int r = 1; r < n; r++) {
            for (int c = 1; c < m; c++) {
                if (dp[r][c] == Integer.MIN_VALUE || (dp[r - 1][c] == Integer.MIN_VALUE && dp[r][c - 1] == Integer.MIN_VALUE) ) continue;
                if (dp[r - 1][c] == Integer.MIN_VALUE || dp[r][c - 1] == Integer.MIN_VALUE) 
                    dp[r][c] = Math.max(dp[r - 1][c], dp[r][c - 1]) % divider;
                else {
                    dp[r][c] = (dp[r - 1][c] % divider + dp[r][c - 1] % divider) % divider;
                }
            }
        }
        
        // System.out.println(dp[n - 1][m - 1]);
        return dp[n - 1][m - 1];
    }
}