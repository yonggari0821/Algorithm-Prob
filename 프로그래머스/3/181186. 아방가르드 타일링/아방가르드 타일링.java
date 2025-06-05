import java.util.*;

/*
전형적인 dp 문제

dp[n] = n일때의 가짓수

dp[1] = 1
dp[2] = 1xdp[1] + 2 = 3
dp[3] = 1xdp[2] + 2xdp[1] + 5 = 10
dp[4] = 1) 1xdp[3] = 10
        2) 2xdp[2] = 6
        3) 5xdp[1] = 5
        4!!) 특수 케이스 존재 = 2
        🟥🟦🟦🟦    🟦🟦🟦🟥
        🟥🟥🟩🟩    🟩🟩🟥🟥
        🟦🟦🟦🟩    🟩🟦🟦🟦
      = 10 + 6 + 5 + 2 = 23
dp[5] = 1) 1xdp[4] = 23
        2) 2xdp[3] = 20
        3) 5xdp[2] = 15
        4) 2xdp[1] = 2
        5!!) 특수 케이스 존재 = 2 
        🟥🟥🟦🟦🟦    🟦🟦🟦🟥🟥
        🟥🟩🟩🟩🟥    🟥🟩🟩🟩🟥
        🟦🟦🟦🟥🟥    🟥🟥🟦🟦🟦
      = 23 + 20 + 15 + 2 + 2 = 62
dp[6] = 같은 방법으로 나머지는 계산하고, 특수 케이스 4개
        🟦🟦🟦🟩🟩🟩   🟦🟦🟦🟩🟩🟩 
        🟥🟨🟨🟨🟥🟥   🟥🟥🟨🟨🟨🟥
        🟥🟥🟩🟩🟩🟥   🟥🟩🟩🟩🟥🟥
        + 맨 윗줄 맨 아래로 하는 거 2개
      = 1xdp[5] + 2xdp[4] + 5xdp[3] + 2xdp[2] + 2xdp[1] + 특수 케이스 4
      = 62 + 46 + 50 + 6 + 2 + 4 = 170
dp[7] = 같은 방법으로 계산하되, 
*/

class Solution {
    static long[] dp = new long[1000001];
    static int mod = 1000000007;

    static {
        dp[1] = 1;
        dp[2] = 1+2;
        dp[3] = 1*dp[2] + 2*dp[1] + 5;
        dp[4] = 1*dp[3] + 2*dp[2] + 5*dp[1] + 2;
        dp[5] = 1*dp[4] + 2*dp[3] + 5*dp[2] + 2*dp[1] + 2;
        dp[6] = 1*dp[5] + 2*dp[4] + 5*dp[3] + 2*dp[2] + 2*dp[1] + 4;
        
        
        // dp[7], dp[8], dp[9]는 특수 케이스 2, 2, 4개씩 늘어남 기존 특수케이스에 --- 3줄씩 추가된 게 늘어나는 느낌!
        // ex)
        /*
        🟦🟦🟦🟩🟩🟩     🟦🟦🟦🟩🟩🟩🟨🟨🟨
        🟥🟨🟨🟨🟥🟥 =>  🟥🟨🟨🟨🟦🟦🟦🟥🟥
        🟥🟥🟩🟩🟩🟥     🟥🟥🟩🟩🟩🟨🟨🟨🟥
        */
        
        // dp[i+3] - dp[i]
        /*
            = dp[i+2] + 2dp[i+1]
                + 5dp[i] + dp[i - 1] - dp[i-3]
            따라서, dp[i + 3] = dp[i+2] + 2dp[i+1] + 6dp[i] + dp[i-1] - dp[i-3]
            따라서, dp[i] = dp[i-1] + 2dp[i-2] + 6dp[i-3] + dp[i-4] - dp[i-6]
        */
    }
    public int solution(int n) {
        // System.out.println("dp[1] = " + dp[1]);
        // System.out.println("dp[2] = " + dp[2]);
        // System.out.println("dp[3] = " + dp[3]);
        // System.out.println("dp[4] = " + dp[4]);
        // System.out.println("dp[5] = " + dp[5]);
        // System.out.println("dp[6] = " + dp[6]);

        
        for (int i = 7; i <= n; i++) {
            long tmp = 
                ((dp[i - 1] + 2*dp[i - 2]) % mod)
                + (6*dp[i - 3] % mod)
                + dp[i - 4]
                - dp[i - 6] % mod + mod;
            dp[i] = tmp % mod;
        }
        return (int)(dp[n] % mod);
    }
}