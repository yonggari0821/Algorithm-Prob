import java.util.*;

class Solution {
    
    private static int[][] dp;
    private static final int MAX = 3000000;
    
    public int solution(int x, int y, int n) {
        int answer = 0;
        
        dp = new int[y + 1][4];
        
        for (int r = 0; r <= y; r++) Arrays.fill(dp[r], MAX);
        
        dp[x][0] = 0;
        dp[x][1] = 0;
        dp[x][2] = 0;
        dp[x][3] = 0;
        
        for (int i = x; i <= y; i++) {
            if (i >= x + n) {
                dp[i][0] = Math.min(dp[i - n][3] + 1, dp[i][3]);
            }
            if (i >= 2 * x && i % 2 == 0) {
                dp[i][1] = Math.min(dp[i/2][3] + 1, dp[i][3]);
            }
            if (i >= 3 * x && i % 3 == 0) {
                dp[i][2] = Math.min(dp[i/3][3] + 1, dp[i][3]);
            }
            
            dp[i][3] = Math.min(
                Math.min(
                    dp[i][0]
                    , 
                    Math.min(
                        dp[i][1]
                        , 
                        dp[i][2]
                    )
                )
                ,
                dp[i][3]
            );
        }
        
        int min = MAX;
        min = Math.min(dp[y][3], min);
        return min == MAX ? -1 : min;
    }
}