import java.util.*;

class Solution {
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public int solution(String[] board) {
        int rowLen = board.length;
        int columnLen = board[0].length();

        int[][] visited = new int[rowLen][columnLen];

        Queue<int[]> q = new LinkedList<>();

        // 시작지점(R) 찾기
        int sr = 0, sc = 0;
        for (int r = 0; r < rowLen; r++) {
            for (int c = 0; c < columnLen; c++) {
                if (board[r].charAt(c) == 'R') {
                    sr = r; sc = c;
                }
            }
        }
        q.offer(new int[]{sr, sc, 0});
        visited[sr][sc] = 1;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int r = now[0], c = now[1], m = now[2];
            
            // 목표(G) 발견 시 바로 return [BFS]
            if (board[r].charAt(c) == 'G') return m;

            // 한 지점에서 가능한 방향 전체로 최대한 미끄러지기 => while문 이용
            for (int dir = 0; dir < 4; dir++) {
                int nr = r, nc = c;
                while (true) {
                    int tr = nr + dr[dir];
                    int tc = nc + dc[dir];
                    if (tr < 0 || tc < 0 || tr >= rowLen || tc >= columnLen) break;
                    if (board[tr].charAt(tc) == 'D') break;
                    nr = tr; nc = tc;
                }
                
                // r,c에서 출발해서 nr,nc에 멈춤 // 멈춘 곳이 한번도 방문하지 않은 곳일 때만 유효!
                if (visited[nr][nc] == 0) {
                    visited[nr][nc] = 1;
                    q.offer(new int[]{nr, nc, m + 1});
                }
            }
        }
        return -1;
    }
}
