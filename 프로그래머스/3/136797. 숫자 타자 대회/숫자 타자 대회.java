import java.util.*;

class Solution {
    static int[][] pos = {
        {}, // dummy
        {0,0}, {0,1}, {0,2}, // 1,2,3
        {1,0}, {1,1}, {1,2}, // 4,5,6
        {2,0}, {2,1}, {2,2}, // 7,8,9
        {3,0}, // * (10)
        {3,1}, // 0 (11)
        {3,2}  // # (12)
    };

    static int getW(int from, int to) {
        if (from == to) return 1;
        int[] p1 = pos[from];
        int[] p2 = pos[to];
        int dr = Math.abs(p1[0] - p2[0]);
        int dc = Math.abs(p1[1] - p2[1]);
        int diag = Math.min(dr, dc);
        int straight = Math.abs(dr - dc);
        return diag * 3 + straight * 2;
    }

    public int solution(String numbers) {
        int n = numbers.length();
        int[][][] dp = new int[n+1][13][13];
        for (int[][] arr2 : dp)
            for (int[] arr : arr2)
                Arrays.fill(arr, Integer.MAX_VALUE);

        // **시작 위치: 왼손 4, 오른손 6**
        dp[0][4][6] = 0;

        for (int i = 0; i < n; i++) {
            int num = numbers.charAt(i) - '0';
            if (num == 0) num = 11;
            for (int l = 1; l <= 12; l++) {
                for (int r = 1; r <= 12; r++) {
                    if (dp[i][l][r] == Integer.MAX_VALUE) continue;
                    // 왼손으로 누르기 (오른손과 겹치면 안 됨)
                    if (num != r) {
                        int cost = getW(l, num);
                        dp[i+1][num][r] = Math.min(dp[i+1][num][r], dp[i][l][r] + cost);
                    }
                    // 오른손으로 누르기 (왼손과 겹치면 안 됨)
                    if (l != num) {
                        int cost = getW(r, num);
                        dp[i+1][l][num] = Math.min(dp[i+1][l][num], dp[i][l][r] + cost);
                    }
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int l = 1; l <= 12; l++) {
            for (int r = 1; r <= 12; r++) {
                answer = Math.min(answer, dp[n][l][r]);
            }
        }
        return answer;
    }
}
