import java.util.*;

class Solution {
    int[][][] dp;
    int n, m;
    int[][] info;
    int INF = 987654321;

    int dfs(int idx, int a, int b) {
        if (a >= n || b >= m) return INF; // 이미 제한 초과
        if (idx == info.length) return a; // 모든 물건을 훔침

        // 이미 방문했으면
        if (dp[idx][a][b] != -1) return dp[idx][a][b];

        int res = INF;
        
        // A가 훔칠 때
        res = Math.min(res, dfs(idx + 1, a + info[idx][0], b));
        
        // B가 훔칠 때
        res = Math.min(res, dfs(idx + 1, a, b + info[idx][1]));

        return dp[idx][a][b] = res;
    }

    public int solution(int[][] info, int n, int m) {
        this.n = n;
        this.m = m;
        this.info = info;
        int len = info.length;
        dp = new int[len + 1][n + 1][m + 1];
        for (int[][] arr2 : dp)
            for (int[] arr : arr2)
                Arrays.fill(arr, -1);

        int answer = dfs(0, 0, 0);
        return answer == INF ? -1 : answer;
    }
}
