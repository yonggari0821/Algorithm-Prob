import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
    Arrays.sort(targets, (a, b) -> Integer.compare(a[1], b[1]) );
        
        int cur = 0;
        for (int i = 0; i < targets.length; i++) {
            int from = targets[i][0];
            int to = targets[i][1];
            
            if (from >= cur) {
                answer++;
                cur = to;
            } else {
                continue;
            }
        }
        
        return answer;
    }
}