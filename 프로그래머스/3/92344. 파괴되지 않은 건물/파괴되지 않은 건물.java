import java.util.*;
/*
    N * M 크기의 맵
    내구도를 가진 건물이 각 칸에 위치
    내구도 0 이하가 되면 파괴됨
    내구도 회복 스킬로 회복 가능
    이미 내구도가 0 이하가 되도 -로 계속 깎임
    마찬가지로 이미 -가 되었어도 회복으로 다시 복구 가능!
*/

class Solution {
    
    // 누적합 계산
    private static void getSum() {
        // 상하
        for (int r = 1; r < N; r++) {
            for (int c = 0; c < M; c++) {
                sum[r][c] += sum[r - 1][c];
            }
        }
        // 좌우
        for (int c = 1; c < M; c++) {
            for (int r = 0; r < N; r++) {
                sum[r][c] += sum[r][c - 1];
            }
        }
    }
    
    static int[][] sum;
    static int N, M;
 
    public static int solution(int[][] board, int[][] skill) {
        N = board.length;
        M = board[0].length;
 
        // 누적합 배열 사용
        sum = new int[N + 1][M + 1];
        // 
        for (int[] s : skill) {
            int r1 = s[1], c1 = s[2];
            int r2 = s[3], c2 = s[4];
            int d = s[5] * (s[0] == 1 ? -1 : 1);
 
            // 위 시작점에 값 더해두고
            sum[r1][c1] += d;
            // 같은 줄 대척점에 값 빼두고
            sum[r1][c2 + 1] += (d * -1);
            // 아래 시작점에 값 빼두고
            sum[r2 + 1][c1] += (d * -1);
            // 아래 끝점에 값 더해둠
            sum[r2 + 1][c2 + 1] += d;
            
            // 최종적으로 마지막에 더할 때,
            // 상->하 방향 먼저 값 더한 후
            // 좌->우로 더하면 전체 부분에 알맞은 값이 더해짐
        }
        
        getSum();
 
        // 살아남은 건물 확인
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] + sum[i][j] > 0) answer++;
            }
        }
        return answer;
    }
 
}