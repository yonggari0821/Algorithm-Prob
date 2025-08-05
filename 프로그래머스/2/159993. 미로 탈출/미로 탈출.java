import java.util.*;

/*
    X 벽 - 못 지나감
    O 통로 - 지나감
    
    S 시작
    E 출구 - 레버가 열린 상태에서만 끝날 수 있음 but 그 전에도 지나갈 순 있음
    
    L 레버 - 반드시 '먼저' 들려야 함
    
    S, L, E 찾기
    S부터 BFS 하되, L 열었는 지 안열었는 지 여부 전역 boolean으로 보관
    
    visited 쓸 것인가?
    쓰긴 해야되는데 중간에 E가 있고 L을 거치는 경우 어떻게 할 것인가?
    
    그냥 BFS를 2번 하자
    1. S -> L
    2. L -> E
    
    1 할 때는 E 만나던 말던 알바가 아님
    2 할 때만 E 찾기
*/

class Node {
    int r;
    int c;
    int m;
    
    Node (int r, int c, int m) {
        this.r = r;
        this.c = c;
        this.m = m;
    }
}

class Solution {

    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, 1, 0, -1};
    
    private static int rowLen;
    private static int columnLen;
    
    private static int sr, sc;
    private static int er, ec;
    private static int lr, lc;
    private static int sToL = 0, lToE = 0;
    
    private static boolean[][] ogMap;
    
    public int solution(String[] maps) {
        int answer = 0;
        rowLen = maps.length;
        columnLen = maps[0].length();
        
        // 방문 처리용 배열
        ogMap = new boolean[rowLen][columnLen];
        
        // 위치 찾기
        for (int r = 0; r < rowLen; r++) {
            for (int c = 0; c < columnLen; c++) {
                if (maps[r].charAt(c) == 'S') {
                    sr = r;
                    sc = c;
                }
                else if (maps[r].charAt(c) == 'E') {
                    er = r;
                    ec = c; 
                }
                else if (maps[r].charAt(c) == 'L') {
                    lr = r;
                    lc = c;
                }
                else if (maps[r].charAt(c) == 'O') {
                    continue;
                }
                else {
                    ogMap[r][c] = true;   
                }
            }
        }
        
        // S -> L
        boolean[][] map = new boolean[rowLen][columnLen];
        for (int r = 0; r < rowLen; r++) {
            for (int c = 0; c < columnLen; c++) {
                map[r][c] = ogMap[r][c];       
            }
        }
        Queue<Node> slq = new LinkedList<>();
        slq.offer(new Node(sr, sc, 0));
        map[sr][sc] = true;
        while (!slq.isEmpty()) {
            Node cur = slq.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (nr < 0 || nc < 0 || nr >= rowLen || nc >= columnLen) continue;
                if (map[nr][nc]) continue;
                if (nr == lr && nc == lc) {
                    sToL = cur.m + 1;
                    slq.clear();
                    break;
                }
                map[nr][nc] = true;
                slq.offer(new Node(nr, nc, cur.m + 1));
            }
        }
        
        // L -> E
        map = new boolean[rowLen][columnLen];
        for (int r = 0; r < rowLen; r++) {
            for (int c = 0; c < columnLen; c++) {
                map[r][c] = ogMap[r][c];       
            }
        }
        Queue<Node> leq = new LinkedList<>();
        leq.offer(new Node(lr, lc, 0));
        map[lr][lc] = true;
        while (!leq.isEmpty()) {
            Node cur = leq.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (nr < 0 || nc < 0 || nr >= rowLen || nc >= columnLen) continue;
                if (map[nr][nc]) continue;
                if (nr == er && nc == ec) {
                    lToE = cur.m + 1;
                    leq.clear();
                    break;
                }
                map[nr][nc] = true;
                leq.offer(new Node(nr, nc, cur.m + 1));
            }
        }
        
        if (sToL == 0 || lToE == 0) return -1;
        
        return sToL + lToE;
    }
}