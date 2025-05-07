import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        int[][] matrix = new int[n + 1][n + 1];
        int a1 = 0, b1 = 0;
        for (int i = 0; i < results.length; i++) {
            a1 = results[i][0];
            b1 = results[i][1];
            matrix[a1][b1] = 1;
            matrix[b1][a1] = -1;
        }
        
        for (int a = 1; a <= n; a++) {
            for (int b = 1; b <= n; b++) {
                for (int mid = 1; mid <= n; mid++) {
                    // 이긴 경우
                    if (matrix[a][mid] == 1 && matrix[mid][b] == 1) {
                        matrix[a][b] = 1;
                        matrix[b][a] = -1;
                    }
                    
                    // 진 경우
                    if (matrix[a][mid] == -1 && matrix[mid][b] == -1) {
                        matrix[a][b] = -1;
                        matrix[b][a] = 1;
                    }
                }
            }
        }
        
        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            
            // 이기고 지는 경우 모두 포함 해서
            for (int j = 1; j <= n; j++) {
                if (matrix[i][j] != 0) cnt++;
            }
            
            // 자기 빼고 n-1
            if (cnt == n - 1) answer++;
        }
        
        return answer;
    }
}