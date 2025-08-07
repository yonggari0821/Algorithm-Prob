import java.util.*;

class Node {
    int r;
    int c;
    
    Node (int r, int c) {
        this.r = r;
        this.c = c;
    }
}

class Solution {
    
    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, 1, 0, -1};
    private static ArrayList<Integer> list;
    private static boolean[][] visited;
    private static int islandCnt = 0, row, col;
    
    
    public int[] solution(String[] maps) {
        
        row = maps.length;
        col = maps[0].length();
        Queue<Node> q;
        list = new ArrayList<Integer>();
        visited = new boolean[row][col];
        
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                // 방문한 곳이면 pass
                if (visited[r][c]) continue;
                // 방문처리 먼저!
                visited[r][c] = true;
                
                // x 또는 숫자
                char xOrNum = maps[r].charAt(c);
                // System.out.println(xOrNum);
                
                // X가 아닌 경우만 살펴보면 됨! (X면 그냥 방문처리만하고 pass!)
                if (xOrNum != 'X') {
                    // System.out.println("[from " + xOrNum + "]");
                    q = new LinkedList<Node>();
                    q.offer(new Node(r, c));
                    int tmp = 0;
                    while (!q.isEmpty()) {
                        Node cur = q.poll();
                        tmp += maps[cur.r].charAt(cur.c) - '0';
                        // System.out.println("-> " + (maps[cur.r].charAt(cur.c) - '0'));
                        for (int i = 0; i < 4; i++) {
                            int nr = cur.r + dr[i];
                            int nc = cur.c + dc[i];
                            if (nr < 0 || nc < 0 || nr >= row || nc >= col || visited[nr][nc] || maps[nr].charAt(nc) == 'X') continue;
                            visited[nr][nc] = true;
                            q.offer(new Node(nr, nc));
                        }
                    }
                    
                    islandCnt++;
                    // System.out.println("[sum " + tmp + "]");
                    list.add(tmp);
                }
            }
        }
        
        Collections.sort(list);
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) answer[i] = list.get(i);
        if (answer.length == 0) answer = new int[] {-1};
        return answer;
    }
}