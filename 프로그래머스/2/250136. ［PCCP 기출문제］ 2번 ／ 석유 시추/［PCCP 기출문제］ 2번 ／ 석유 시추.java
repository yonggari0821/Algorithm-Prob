import java.util.*;

class Solution {
    // 상, 하, 좌, 우
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    public int solution(int[][] land) {
        int rows = land.length;
        int cols = land[0].length;
        
        int[] sum = new int[cols];
        boolean[][] visited = new boolean[rows][cols];
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // 아직 방문하지 않은 석유 칸을 발견하면 새로운 석유 덩어리 탐색 시작(BFS)
                if (!visited[r][c] && land[r][c] == 1) {
                    
                    // 현재 탐색 석유 덩어리 크기
                    int val = 0;
                    // 현재 덩어리가 걸쳐 있는 열들의 집합(set로 중복 제거함 => ㄷ자형도 문제없이 가능)
                    Set<Integer> oilcol = new HashSet<>();
                    
                    Queue<int[]> queue = new LinkedList<>();
                    
                    // 탐색 시작점
                    queue.offer(new int[]{r, c});
                    visited[r][c] = true;
                    
                    // BFS를 진행하며 덩어리 크기와 위치 정보를 수집
                    while (!queue.isEmpty()) {
                        int[] currentPos = queue.poll();
                        int currentR = currentPos[0];
                        int currentC = currentPos[1];
                        
                        val++;
                        oilcol.add(currentC);
                        
                        for (int i = 0; i < 4; i++) {
                            int nextR = currentR + dr[i];
                            int nextC = currentC + dc[i];
                            
                            if (nextR < 0 || nextC < 0 || nextR >= rows || nextC >= cols 
                                || visited[nextR][nextC] || land[nextR][nextC] == 0) {
                                continue;
                            }
                            
                            visited[nextR][nextC] = true;
                            queue.offer(new int[]{nextR, nextC});
                        }
                    }
                    
                    // 한 덩어리 탐색 완료 후, 해당 덩어리가 걸친 모든 열에 크기를 더해줌
                    for (int colIndex : oilcol) {
                        sum[colIndex] += val;
                    }
                }
            }
        }
        
        // 모든 열의 석유량 중 최대값을 찾음
        int maxOil = 0;
        for (int amount : sum) {
            if (amount > maxOil) {
                maxOil = amount;
            }
        }
        
        return maxOil;
    }
}
