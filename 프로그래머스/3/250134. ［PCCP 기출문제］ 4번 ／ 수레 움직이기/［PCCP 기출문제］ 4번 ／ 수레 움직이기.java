import java.util.*;

class Solution {
    // map의 크기
    static int n, m;
    // 하 우 상 좌
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    // 맵
    static int[][] map;
    static boolean[][] redVisited;
    static boolean[][] blueVisited;
    // 답
    static int answer = Integer.MAX_VALUE;
    // 각종 의미
    static final int empty = 0;
    static final int redStart = 1;
    static final int redEnd = 3;
    static final int blueStart = 2;
    static final int blueEnd = 4;
    static final int wall = 5;
    // 답 구하는 함수
    static void dfs(int cnt, int r1, int c1, int r2, int c2) {
        if (map[r1][c1] == redEnd && map[r2][c2] == blueEnd) {
            answer = Math.min(answer, cnt);
            return;
        }
        // 빨간색만 도착한 상태 => 파란색만 이동
        else if (map[r1][c1] == redEnd) {
            for (int i = 0; i < 4; i++) {
                int nr2 = r2 + dr[i];
                int nc2 = c2 + dc[i];
                if (nr2 < 0 || nc2 < 0 || nr2 >= n || nc2 >= m) continue;
                if (blueVisited[nr2][nc2]) continue;
                if (map[nr2][nc2] == redEnd || map[nr2][nc2] == wall) continue;
                blueVisited[nr2][nc2] = true;
                dfs(cnt + 1, r1, c1, nr2, nc2);
                blueVisited[nr2][nc2] = false;
            }
        }
        // 파란색만 도착한 상태 => 빨간색만 이동
        else if (map[r2][c2] == blueEnd) {
            for (int i = 0; i < 4; i++) {
                int nr1 = r1 + dr[i];
                int nc1 = c1 + dc[i];
                if (nr1 < 0 || nc1 < 0 || nr1 >= n || nc1 >= m) continue;
                if (redVisited[nr1][nc1]) continue;
                if (map[nr1][nc1] == blueEnd || map[nr1][nc1] == wall) continue;
                redVisited[nr1][nc1] = true;
                dfs(cnt + 1, nr1, nc1, r2, c2);
                redVisited[nr1][nc1] = false;
            }
        }
        // 둘 다 이동
        else {
            for (int i = 0; i < 4; i++) {
                int nr1 = r1 + dr[i];
                int nc1 = c1 + dc[i];
                if (nr1 < 0 || nc1 < 0 || nr1 >= n || nc1 >= m) continue;
                if (map[nr1][nc1] == wall) continue;
                if (redVisited[nr1][nc1]) continue;
                for (int j = 0; j < 4; j++) {
                    int nr2 = r2 + dr[j];
                    int nc2 = c2 + dc[j];
                    if (nr2 < 0 || nc2 < 0 || nr2 >= n || nc2 >= m) continue;
                    if (blueVisited[nr2][nc2]) continue;
                    if (map[nr2][nc2] == wall) continue;
                    if (nr1 == nr2 && nc1 == nc2) continue;
                    if ((nr1 == r2 && nc1 == c2) && nr2 == r1 && nc2 == c1) continue;
                    redVisited[nr1][nc1] = true;
                    blueVisited[nr2][nc2] = true;
                    dfs(cnt + 1, nr1, nc1, nr2, nc2);
                    redVisited[nr1][nc1] = false;
                    blueVisited[nr2][nc2] = false;
                }
            }
        }
    }
    
    public int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;
        int r1 = 0, c1 = 0, r2 = 0, c2 = 0;
        map = new int[n][m];
        redVisited = new boolean[n][m];
        blueVisited = new boolean[n][m];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                map[r][c] = maze[r][c];
                if (map[r][c] == redStart) {
                    r1 = r;
                    c1 = c;
                }
                if (map[r][c] == blueStart) {
                    r2 = r;
                    c2 = c;
                }
            }
        }
        
        redVisited[r1][c1] = true;
        blueVisited[r2][c2] = true;
        dfs(0, r1, c1, r2, c2);
        
        if (answer == Integer.MAX_VALUE) answer = 0;
        
        return answer;
    }
}