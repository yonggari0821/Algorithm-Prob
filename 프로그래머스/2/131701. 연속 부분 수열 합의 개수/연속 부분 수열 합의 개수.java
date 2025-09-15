import java.util.*;

/*
    최대 1000개
    최대 1000
    1000 x 1000 = 1000000
    그냥 int로 해도 무방
*/

class Solution {
    
    private static Set<Integer> set = new HashSet<>();
    private static int[][] dp;
    
    public int solution(int[] elements) {
        int cnt = elements.length;

        dp = new int[cnt][cnt + 1]; // 부분합 배열 // dp[a][b] a번째 숫자부터 b개만큼 고른 부분 합
        int sum = 0;
        for (int i = 0; i < cnt; i++) {
            set.add(elements[i]);
            sum += elements[i];
        }
        for (int i = 0; i < cnt; i++) dp[i][cnt] = sum;
        set.add(sum);
        
        // c: 고르는 갯수 0 ~ 전체 갯수의 반까지 (5개면 2개(0,1)까지 / 6개면 3(0,1,2)개까지)
        for (int c = 0; c < cnt/2; c++) {
            int rc = c + 1; // 실제 고르는 갯수
            // 전체 원소를 한번씩 출발점으로 놓기
            for (int i = 0; i < cnt; i++) {
                dp[i][rc] = dp[i][c] + elements[(i + c) % cnt];
                dp[i][cnt - c - 1] = dp[i][cnt - c] - elements[(i + c) % cnt];
                set.add(dp[i][c + 1]);
                set.add(dp[i][cnt - c - 1]);
            }
        }
        
        return set.size();
    }
}