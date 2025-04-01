class Solution {
    static String answer;
    static boolean found;
    static int n, m, r, c, k;
    static int[] dr = {1, 0, 0, -1};
    static int[] dc = {0, -1, 1, 0};
    static char[] directions = {'d', 'l', 'r', 'u'};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n;
        this.m = m;
        this.r = r;
        this.c = c;
        this.k = k;
        found = false;
        
        int minDist = Math.abs(r - x) + Math.abs(c - y);
        if (minDist > k || (k - minDist) % 2 != 0) {
            return "impossible";
        }
        
        dfs(x, y, 0, new StringBuilder());
        return found ? answer : "impossible";
    }
    
    private void dfs(int x, int y, int moves, StringBuilder path) {
        if (found) return;
        
        int remainingMoves = k - moves;
        int minDistToTarget = Math.abs(r - x) + Math.abs(c - y);
        
        if (minDistToTarget > remainingMoves || (remainingMoves - minDistToTarget) % 2 != 0) {
            return;
        }
        
        if (x == r && y == c && moves == k) {
            answer = path.toString();
            found = true;
            return;
        }
        
        if (moves == k) return;
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dr[i];
            int ny = y + dc[i];
            if (nx < 1 || ny < 1 || nx > n || ny > m) continue;
            
            path.append(directions[i]);
            dfs(nx, ny, moves + 1, path);
            path.setLength(path.length() - 1);
            
            if (found) return;
        }
    }
}