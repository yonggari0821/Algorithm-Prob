import java.util.*;

class Solution {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        // 전체 시간
        int n = onboard.length;
        // -10도까지 가능한데 인덱스는 0부터 가능하므로 10만큼 실제온도 에서 떨어뜨려야 함
        int OFFSET = 10;
        // 최대 온도(원래 40인데 OFFSET 고려해서)
        int MAX_TEMP = 50;
        // 초기값(최소값을 찾아야 하므로 큰 값으로!)
        int INF = Integer.MAX_VALUE / 2;
        int[][] dp = new int[n + 1][MAX_TEMP + 1];
        for (int[] row : dp) Arrays.fill(row, INF);

        // 시작은 0원
        dp[0][temperature + OFFSET] = 0;

        for (int time = 0; time < n; time++) {
            for (int temp = 0; temp <= MAX_TEMP; temp++) {
                // 사용 불가한 경우
                if (dp[time][temp] == INF) continue;

                // 1. 에어컨 OFF: 실외온도 방향으로 1도 이동
                int nextTemp = temp;
                
                // 현재 온도가 실외온도보다
                // 1-1. 낮은 경우
                if (temp < temperature + OFFSET) nextTemp++;
                // 1-2. 높은 경우
                else if (temp > temperature + OFFSET) nextTemp--;
                
                // [중요] 승객이 탑승 중이라면 쾌적 온도(t1~t2) 범위 내인지 확인
                // 승객이 없으면 아무 온도나 허용
                if (nextTemp >= 0 && nextTemp <= MAX_TEMP) {
                    if (onboard[time] == 0 || (t1 + OFFSET <= nextTemp && nextTemp <= t2 + OFFSET)) {
                        dp[time + 1][nextTemp] = Math.min(dp[time + 1][nextTemp], dp[time][temp]);
                    }
                }

                // 2. 에어컨 ON: temp-1, temp, temp+1 모두 고려
                // (희망온도를 자유롭게 바꿀 수 있으므로)
                // (1) temp-1로 이동 (a)
                if (temp - 1 >= 0) {
                    int t = temp - 1;
                    if (onboard[time] == 0 || (t1 + OFFSET <= t && t <= t2 + OFFSET)) {
                        dp[time + 1][t] = Math.min(dp[time + 1][t], dp[time][temp] + a);
                    }
                }
                // (2) temp+1로 이동 (a)
                if (temp + 1 <= MAX_TEMP) {
                    int t = temp + 1;
                    if (onboard[time] == 0 || (t1 + OFFSET <= t && t <= t2 + OFFSET)) {
                        dp[time + 1][t] = Math.min(dp[time + 1][t], dp[time][temp] + a);
                    }
                }
                // (3) temp 그대로 유지 (b)
                int t = temp;
                if (onboard[time] == 0 || (t1 + OFFSET <= t && t <= t2 + OFFSET)) {
                    dp[time + 1][t] = Math.min(dp[time + 1][t], dp[time][temp] + b);
                }
            }
        }

        // 마지막 시간에 승객이 있으면 쾌적 범위만, 없으면 전체 중 최소
        int answer = INF;
        for (int temp = 0; temp <= MAX_TEMP; temp++) {
            if (onboard[n - 1] == 0 || (t1 + OFFSET <= temp && temp <= t2 + OFFSET)) {
                answer = Math.min(answer, dp[n][temp]);
            }
        }
        return answer;
    }
}
