import java.util.*;
/*
    알고력 / 코딩력 모두 문제가 요구하는 것보다 높아야 함
    일반적인 공부를 통해서는 둘 다 각각 1 올리는데 1시간 소요
    문제 풀어도 해당 문제가 제공하는 능력이 올라감
    문제 푸는데는 문제가 요구하는 시간이 핊요하며 같은 문제 여러번 풀어도 똑같은 시간 소요 및 똑같은 능력치 향상
    모든 문제를 풀 필요는 없음
    다만 모든 문제를 풀 수 있는 알고력과 코딩력을 구비하는데 걸리는 최단 시간을 구해야 함!
    
    필요한 알고/코딩력을 문제들 중 최대 요구 알고/코딩력으로 정해두고
    시간까지 고려해서 가장 효율적으로 문제풀이 or 학습해야 함
    => 고려해야하는 게 3개 이상으로 복잡 & problems 수가 최대 6개 밖에 안됨
    => 문제 별로 풀고 안 풀고를 dfs로 해봐도 되겠는데?
    => 아 근데 같은 문제 여러 번 풀수도 있네?
    => 문제 풀고/안풀고로 재귀 설계 시 오류 발생 가능
    
    => 벽 느낌
    => 답변 참고함
    
    => 각 알고 및 코딩력 상태별 최단 시간을 저장해두는 dp를 활용하기
*/

class Solution {
    private static int maxAlp = 0, maxCop = 0;

    public int solution(int alp, int cop, int[][] problems) {
        // 필요한 최대 알고/코딩력 계산
        for (int i = 0; i < problems.length; i++) {
            int alpReq = problems[i][0];
            int copReq = problems[i][1];
            maxAlp = Math.max(maxAlp, alpReq);
            maxCop = Math.max(maxCop, copReq);
        }

        // 이미 모든 문제 풀 수 있는 경우
        if (maxAlp <= alp && maxCop <= cop) return 0;

        // dp 배열 전체 초기화
        int[][] dp = new int[maxAlp + 2][maxCop + 2];
        for (int r = 0; r <= maxAlp; r++) {
            for (int c = 0; c <= maxCop; c++) {
                dp[r][c] = Integer.MAX_VALUE;
            }
        }

        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);
        dp[alp][cop] = 0;

        for (int r = alp; r <= maxAlp; r++) {
            for (int c = cop; c <= maxCop; c++) {
                if (dp[r][c] == Integer.MAX_VALUE) continue;

                // 학습
                int nextAlp = Math.min(r + 1, maxAlp);
                int nextCop = Math.min(c + 1, maxCop);
                dp[nextAlp][c] = Math.min(dp[nextAlp][c], dp[r][c] + 1);
                dp[r][nextCop] = Math.min(dp[r][nextCop], dp[r][c] + 1);

                // 문제 풀이 가능하면 풀기
                for (int p = 0; p < problems.length; p++) {
                    if (r >= problems[p][0] && c >= problems[p][1]) {
                        nextAlp = Math.min(r + problems[p][2], maxAlp);
                        nextCop = Math.min(c + problems[p][3], maxCop);
                        dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[r][c] + problems[p][4]);
                    }
                }
            }
        }

        return dp[maxAlp][maxCop];
    }
}
