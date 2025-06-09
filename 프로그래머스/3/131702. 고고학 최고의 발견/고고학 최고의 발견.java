import java.util.*;

/*
    하나의 시계 하나의 시곗바늘
    시곗바늘은 시계 방향으로만 90도씩 돌리기 가능
    상하좌우 인접 시계의 시곗바늘도 같이 돌아감(연쇄적으로 계속 돌아가는 것은 아니고 한 depth만!)
    모든 시곗바늘이 12시를 가리키면 잠금 풀림
    최소한의 조작으로 잠금 풀기
    
    0 -> 1
    1 -> 2
    2 -> 3
    3 -> 0
    n -> (n + 1) % 4
    
    조작하는 순서는 상관 없음!
    => 따라서 첫행을 먼저 조작해두고, 그에 따른 나머지 행을 살펴보면서 경우의 수를 줄임
*/
class Solution {
    
    private static int[][] cur;
    private static int[][] initial;
    private static int len;
    private static int tmp;
    private static int min = Integer.MAX_VALUE;
    private static int[] dr = {0, -1, 0, 1, 0};
    private static int[] dc = {0, 0, 1, 0, -1};
    private static int[] firstRow;
    
    private static boolean check() {
        for (int r = 0; r < len; r++) {
            for (int c = 0; c < len; c++) {
                if (cur[r][c] != 0) return false;
            }
        }
        return true;
    }
    
    private static void turnSurround(int r, int c, int turn) {
        for (int i = 0; i < 5; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nr >= len || nc < 0 || nc >= len) continue;
            cur[nr][nc] += turn;
            cur[nr][nc] %= 4;
        }
    }
    
    static void func(int idx) {
        
        // 탈출 조건: 한 줄의 시계를 전부 살펴본 경우
        if (idx == len) {
            // 현재 돌린 수
            tmp = 0;
            
            // 현재 상태에 원본 상태 옮기기
            for (int r = 0; r < len; r++) {
                for (int c = 0; c < len; c++) {
                    cur[r][c] = initial[r][c];
                }
            }
            
            // 첫번째 행 조작
            for (int c = 0; c < len; c++) {
                turnSurround(0, c, firstRow[c]);
                tmp += firstRow[c];
            }
            
            // 나머지 행 조작
            for (int r = 1; r < len; r++) {
                for (int c = 0; c < len; c++) {
                    int turn = (4 - cur[r-1][c]) % 4;
                    turnSurround(r, c, turn);
                    tmp += turn;
                }
            }
            
            // 모두 12시 가리킬 시 최솟값 리뉴얼
            if (check()) min = Math.min(min, tmp);
            return;
        }
        
        // idx 번째 열에 위치한 시계를 최대 4번씩 조작해기로 결정하고 들어가기
        for (int turn = 0; turn < 4; turn++) {
            firstRow[idx] = turn;
            func(idx + 1);
        }
    }
    
    public int solution(int[][] clockHands) {
        // 초기 설정
        len = clockHands.length;
        cur = new int[len][len];
        initial = new int[len][len];
        firstRow = new int[len];
        for (int r = 0; r < len; r++) {
            for (int c = 0; c < len; c++) {
                initial[r][c] = clockHands[r][c];
                cur[r][c] = initial[r][c];
            }
        }
        
        // 본 함수
        func(0);
        
        return min;
    }
}