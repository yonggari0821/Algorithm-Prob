import java.util.*;

class Solution {
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public int solution(String[] board) {
        int n = board.length;
        int m = board[0].length();

        int[][] visited = new int[n][m];

        Queue<int[]> q = new LinkedList<>();

        // 시작지점, 목표지점 찾기
        int stR = 0, stC = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i].charAt(j) == 'R') {
                    stR = i; stC = j;
                }
            }
        }
        q.offer(new int[]{stR, stC, 0});
        visited[stR][stC] = 1;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int r = now[0], c = now[1], cnt = now[2];
            // 목표 발견
            if (board[r].charAt(c) == 'G') return cnt;

            for (int d = 0; d < 4; d++) {
                int nr = r, nc = c;
                // 한 방향으로 계속 미끄러지기
                while (true) {
                    int tr = nr + dr[d], tc = nc + dc[d];
                    if (tr < 0 || tc < 0 || tr >= n || tc >= m) break;
                    if (board[tr].charAt(tc) == 'D') break;
                    nr = tr; nc = tc;
                }
                // (r,c)에서 출발해서 [nr,nc]에 정확히 멈춤
                if (visited[nr][nc] == 0) {
                    visited[nr][nc] = 1;
                    q.offer(new int[]{nr, nc, cnt + 1});
                }
            }
        }
        return -1;
    }
}
