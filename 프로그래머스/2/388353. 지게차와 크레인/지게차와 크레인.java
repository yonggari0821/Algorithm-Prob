import java.util.*;

class Node {
    int r;
    int c;
    
    public Node (int r, int c) {
        this.r = r;
        this.c = c;
    }
}

class Solution {
    
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    
    static int answer;
    static int row;
    static int col;
    
    static int[][] map;
    static boolean[][] visited;
    
    static void useCrane(int t) {
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                // 1 ~ 26
                if (map[r][c] == t) {
                    answer--;
                    map[r][c] = 0;
                }
            }
        }
    }
    
    static void useFolkLift(int t) {
        visited = new boolean[row][col];
        
        for (int i = 0; i < row; i++) {
            if (!visited[i][0]) dfs(i, 0, t);
            if (!visited[i][col - 1]) dfs(i, col - 1, t);
        }
        for (int i = 0; i < col; i++) {
            if (!visited[0][i]) dfs(0, i, t);
            if (!visited[row - 1][i]) dfs(row - 1, i, t);
        }
    }
    
    static void dfs(int r, int c, int t) {
        visited[r][c] = true;
        // 접근 가능하면 더 들어감
        if (map[r][c] == 0) {
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nr < 0 || nc < 0 || nr >= row || nc >= col) continue;
                if (visited[nr][nc]) continue;
                dfs(nr, nc, t);
            }
        }
        // 접근 불가 시 스탑
        if (map[r][c] == t) {
            answer--;
            map[r][c] = 0;
        }
    }
    
    public int solution(String[] storage, String[] requests) {
        // System.out.println((int)'A'); // 65
        // System.out.println((int)'Z'); // 90
        
        row = storage.length;
        col = storage[0].length();
        answer = row * col;
        
        map = new int[row][col];
        List<Integer>[] where = new ArrayList[27];
        for (int i = 0; i < 27 ; i++) where[i] = new ArrayList<Integer>();
        
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                // 1 ~ 26
                map[r][c] = storage[r].charAt(c) - 64;
                where[map[r][c]].add(r * col + col);
            }
        }
        
        for (int i = 0; i < requests.length; i++) {
            int target = requests[i].charAt(0) - 64;
            
            // 크레인
            if (requests[i].length() == 2) {
                useCrane(target);
            }
            // 지게차
            else {
                useFolkLift(target);
            }
        }
        
        return answer;
    }
}